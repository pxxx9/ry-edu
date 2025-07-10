package org.dromara.edu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.constant.CacheNames;
import org.dromara.common.core.domain.dto.OssDTO;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.service.OssService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.SpringUtils;
import org.dromara.common.core.utils.StreamUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.utils.file.FileUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.oss.core.OssClient;
import org.dromara.common.oss.entity.UploadResult;
import org.dromara.common.oss.enums.AccessPolicyType;
import org.dromara.common.oss.factory.OssFactory;
import org.dromara.edu.domain.Document;
import org.dromara.edu.domain.bo.DocumentBo;
import org.dromara.edu.domain.vo.DocumentVo;
import org.dromara.edu.mapper.DocumentMapper;
import org.dromara.edu.service.IDocumentService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 文档Service业务层实现
 *
 * @author Pyx
 * @date 2025-07-09
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DocumentServiceImpl implements IDocumentService {

    private final DocumentMapper baseMapper;


    @Override
    public TableDataInfo<DocumentVo> queryPageList(DocumentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Document> lqw = buildQueryWrapper(bo);
        Page<DocumentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        List<DocumentVo> filterResult = StreamUtils.toList(result.getRecords(), this::matchingUrl);
        result.setRecords(filterResult);
        return TableDataInfo.build(result);
    }

    @Override
    public List<DocumentVo> listByIds(Collection<Long> DocumentIds) {
        List<DocumentVo> list = new ArrayList<>();
        DocumentServiceImpl DocumentService = SpringUtils.getAopProxy(this);
        for (Long id : DocumentIds) {
            DocumentVo vo = DocumentService.getById(id);
            if (ObjectUtil.isNotNull(vo)) {
                try {
                    list.add(this.matchingUrl(vo));
                } catch (Exception e) {
                    log.error("获取文档列表异常", e);
                    list.add(vo);
                }
            }
        }
        return list;
    }

    @Cacheable(cacheNames = CacheNames.SYS_OSS, key = "#DocumentId")
    @Override
    public DocumentVo getById(Long DocumentId) {
        return baseMapper.selectVoById(DocumentId);
    }

    @Override
    public DocumentVo upload(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String suffix = StringUtils.substring(originalFileName, originalFileName.lastIndexOf("."), originalFileName.length());
        OssClient storage = OssFactory.instance("doc-bucket");
        UploadResult uploadResult;
        try {
            uploadResult = storage.uploadSuffix(file.getBytes(), suffix, file.getContentType());
        } catch (IOException e) {
            throw new ServiceException("文档上传失败: " + e.getMessage());
        }
        return buildResultEntity(originalFileName, suffix, storage.getConfigKey(), uploadResult);
    }

    @Override
    public DocumentVo upload(File file) {
        String originalFileName = file.getName();
        String suffix = StringUtils.substring(originalFileName, originalFileName.lastIndexOf("."), originalFileName.length());
        OssClient storage = OssFactory.instance();
        UploadResult uploadResult = storage.uploadSuffix(file, suffix);
        return buildResultEntity(originalFileName, suffix, storage.getConfigKey(), uploadResult);
    }

    @Override
    public void download(Long DocumentId, HttpServletResponse response) throws IOException {
        log.info("需要下载的文档id:{}", DocumentId);
        //DocumentVo DocumentVo = SpringUtils.getAopProxy(this).getById(DocumentId);

        DocumentVo DocumentVo = this.getById(DocumentId);
        if (ObjectUtil.isNull(DocumentVo)) {
            throw new ServiceException("文档数据不存在!");
        }
        FileUtils.setAttachmentResponseHeader(response, DocumentVo.getOriginalName());
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8");
        OssClient storage = OssFactory.instance("doc-bucket");
        storage.download(DocumentVo.getDocumentName(), response.getOutputStream(), response::setContentLengthLong);
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 业务校验逻辑
        }
        List<Document> list = baseMapper.selectBatchIds(ids);
        for (Document Document : list) {
            OssClient storage = OssFactory.instance("doc-bucket");
            storage.delete(Document.getUrl());
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    private LambdaQueryWrapper<Document> buildQueryWrapper(DocumentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Document> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getDocumentName()), Document::getDocumentName, bo.getDocumentName());
        lqw.like(StringUtils.isNotBlank(bo.getOriginalName()), Document::getOriginalName, bo.getOriginalName());
        lqw.eq(StringUtils.isNotBlank(bo.getDocumentSuffix()), Document::getDocumentSuffix, bo.getDocumentSuffix());
        lqw.eq(StringUtils.isNotBlank(bo.getUrl()), Document::getUrl, bo.getUrl());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            Document::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.eq(ObjectUtil.isNotNull(bo.getCreateBy()), Document::getCreateBy, bo.getCreateBy());
        lqw.orderByAsc(Document::getDocumentId);
        return lqw;
    }

    private DocumentVo buildResultEntity(String originalFileName, String suffix, String configKey, UploadResult uploadResult) {
        Document Document = new Document();
        Document.setUrl(uploadResult.getUrl());
        Document.setDocumentSuffix(suffix);
        Document.setDocumentName(uploadResult.getFilename());
        Document.setOriginalName(originalFileName);
        Document.setService(configKey);
        baseMapper.insert(Document);
        DocumentVo DocumentVo = MapstructUtils.convert(Document, DocumentVo.class);
        return this.matchingUrl(DocumentVo);
    }

    private DocumentVo matchingUrl(DocumentVo Document) {
        OssClient storage = OssFactory.instance("doc-bucket");
        if (AccessPolicyType.PRIVATE == storage.getAccessPolicy()) {
            Document.setUrl(storage.getPrivateUrl(Document.getDocumentName(), Duration.ofSeconds(120)));
        }
        return Document;
    }
}
