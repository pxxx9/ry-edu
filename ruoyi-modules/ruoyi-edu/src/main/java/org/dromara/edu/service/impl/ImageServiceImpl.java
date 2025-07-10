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
import org.dromara.edu.domain.Image;
import org.dromara.edu.domain.bo.ImageBo;
import org.dromara.edu.domain.vo.ImageVo;
import org.dromara.edu.mapper.ImageMapper;
import org.dromara.edu.service.IImageService;
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
 * 图片Service业务层实现
 *
 * @author Pyx
 * @date 2025-07-09
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements IImageService {

    private final ImageMapper baseMapper;


    @Override
    public TableDataInfo<ImageVo> queryPageList(ImageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Image> lqw = buildQueryWrapper(bo);
        Page<ImageVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        List<ImageVo> filterResult = StreamUtils.toList(result.getRecords(), this::matchingUrl);
        result.setRecords(filterResult);
        return TableDataInfo.build(result);
    }

    @Override
    public List<ImageVo> listByIds(Collection<Long> imageIds) {
        List<ImageVo> list = new ArrayList<>();
        ImageServiceImpl imageService = SpringUtils.getAopProxy(this);
        for (Long id : imageIds) {
            ImageVo vo = imageService.getById(id);
            if (ObjectUtil.isNotNull(vo)) {
                try {
                    list.add(this.matchingUrl(vo));
                } catch (Exception e) {
                    log.error("获取图片列表异常", e);
                    list.add(vo);
                }
            }
        }
        return list;
    }

    @Cacheable(cacheNames = CacheNames.SYS_OSS, key = "#imageId")
    @Override
    public ImageVo getById(Long imageId) {
        return baseMapper.selectVoById(imageId);
    }

    @Override
    public ImageVo upload(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String suffix = StringUtils.substring(originalFileName, originalFileName.lastIndexOf("."), originalFileName.length());
        OssClient storage = OssFactory.instance("image-bucket");
        UploadResult uploadResult;
        try {
            uploadResult = storage.uploadSuffix(file.getBytes(), suffix, file.getContentType());
        } catch (IOException e) {
            throw new ServiceException("图片上传失败: " + e.getMessage());
        }
        return buildResultEntity(originalFileName, suffix, storage.getConfigKey(), uploadResult);
    }

    @Override
    public ImageVo upload(File file) {
        String originalFileName = file.getName();
        String suffix = StringUtils.substring(originalFileName, originalFileName.lastIndexOf("."), originalFileName.length());
        OssClient storage = OssFactory.instance();
        UploadResult uploadResult = storage.uploadSuffix(file, suffix);
        return buildResultEntity(originalFileName, suffix, storage.getConfigKey(), uploadResult);
    }

    @Override
    public void download(Long imageId, HttpServletResponse response) throws IOException {
        ImageVo imageVo = SpringUtils.getAopProxy(this).getById(imageId);
        if (ObjectUtil.isNull(imageVo)) {
            throw new ServiceException("图片数据不存在!");
        }
        FileUtils.setAttachmentResponseHeader(response, imageVo.getOriginalName());
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8");
        OssClient storage = OssFactory.instance("image-bucket");
        storage.download(imageVo.getImageName(), response.getOutputStream(), response::setContentLengthLong);
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 业务校验逻辑
        }
        List<Image> list = baseMapper.selectBatchIds(ids);
        for (Image image : list) {
            OssClient storage = OssFactory.instance("image-bucket");
            storage.delete(image.getUrl());
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    private LambdaQueryWrapper<Image> buildQueryWrapper(ImageBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Image> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getImageName()), Image::getImageName, bo.getImageName());
        lqw.like(StringUtils.isNotBlank(bo.getOriginalName()), Image::getOriginalName, bo.getOriginalName());
        lqw.eq(StringUtils.isNotBlank(bo.getImageSuffix()), Image::getImageSuffix, bo.getImageSuffix());
        lqw.eq(StringUtils.isNotBlank(bo.getUrl()), Image::getUrl, bo.getUrl());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            Image::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.eq(ObjectUtil.isNotNull(bo.getCreateBy()), Image::getCreateBy, bo.getCreateBy());
        lqw.orderByAsc(Image::getImageId);
        return lqw;
    }

    private ImageVo buildResultEntity(String originalFileName, String suffix, String configKey, UploadResult uploadResult) {
        Image image = new Image();
        image.setUrl(uploadResult.getUrl());
        image.setImageSuffix(suffix);
        image.setImageName(uploadResult.getFilename());
        image.setOriginalName(originalFileName);
        image.setService(configKey);
        baseMapper.insert(image);
        ImageVo imageVo = MapstructUtils.convert(image, ImageVo.class);
        return this.matchingUrl(imageVo);
    }

    private ImageVo matchingUrl(ImageVo image) {
        OssClient storage = OssFactory.instance("image-bucket");
        if (AccessPolicyType.PRIVATE == storage.getAccessPolicy()) {
            image.setUrl(storage.getPrivateUrl(image.getImageName(), Duration.ofSeconds(120)));
        }
        return image;
    }
}
