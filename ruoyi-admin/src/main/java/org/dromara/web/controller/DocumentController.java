package org.dromara.web.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.ObjectUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.QueryGroup;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.edu.domain.bo.DocumentBo;
import org.dromara.edu.domain.vo.OssUploadVo;
import org.dromara.edu.domain.vo.DocumentVo;
import org.dromara.edu.domain.vo.OssUploadVo;
import org.dromara.edu.service.IDocumentService;
import org.dromara.system.domain.bo.SysOssBo;
import org.dromara.system.domain.vo.SysOssVo;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/material/document")
public class DocumentController extends BaseController {

    private final IDocumentService DocumentService;

    /**
     * 查询OSS对象存储列表
     */
    @SaCheckPermission("system:oss:list")
    @GetMapping("/list")
    public TableDataInfo<DocumentVo> list(@Validated(QueryGroup.class) DocumentBo bo, PageQuery pageQuery) {
        return DocumentService.queryPageList(bo, pageQuery);
    }
    /**
     * 查询文档基于ID串
     *
     * @param DocumentIds 文档ID串
     */
    @SaCheckPermission("edu:Document:query")
    @GetMapping("/listByIds/{DocumentIds}")
    public R<List<DocumentVo>> listByIds(@NotEmpty(message = "主键不能为空")
                                      @PathVariable Long[] DocumentIds) {
        List<DocumentVo> list = DocumentService.listByIds(Arrays.asList(DocumentIds));
        return R.ok(list);
    }

    /**
     * 上传文档
     *
     * @param file 文档文件
     */
    @SaCheckPermission("edu:Document:upload")
    @Log(title = "文档上传", businessType = BusinessType.INSERT)
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<OssUploadVo> upload(@RequestPart("file") MultipartFile file) {
        if (ObjectUtil.isNull(file) || file.isEmpty()) {
            return R.fail("上传文档不能为空");
        }
        DocumentVo Document = DocumentService.upload(file);
        OssUploadVo uploadVo = new OssUploadVo();
        uploadVo.setUrl(Document.getUrl());
        uploadVo.setFileName(Document.getOriginalName());
        uploadVo.setId(Document.getDocumentId().toString());
        return R.ok(uploadVo);
    }

    /**
     * 下载文档
     *
     * @param DocumentId 文档ID
     */
    @SaCheckPermission("edu:Document:download")
    @GetMapping("/download/{DocumentId}")
    public void download(@PathVariable Long DocumentId, HttpServletResponse response) throws IOException {
        DocumentService.download(DocumentId, response);
    }

    /**
     * 删除文档
     *
     * @param DocumentIds 文档ID串
     */
    @SaCheckPermission("edu:Document:remove")
    @Log(title = "文档删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/{DocumentIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] DocumentIds) {
        return toAjax(DocumentService.deleteWithValidByIds(Arrays.asList(DocumentIds), true));
    }
}
