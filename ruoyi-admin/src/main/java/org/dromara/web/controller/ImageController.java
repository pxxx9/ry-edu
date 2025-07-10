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
import org.dromara.edu.domain.bo.ImageBo;
import org.dromara.edu.domain.vo.OssUploadVo;
import org.dromara.edu.domain.vo.ImageVo;
import org.dromara.edu.domain.vo.OssUploadVo;
import org.dromara.edu.service.IImageService;
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
@RequestMapping("/material/image")
public class ImageController extends BaseController {

    private final IImageService imageService;

    /**
     * 查询OSS对象存储列表
     */
    @SaCheckPermission("system:oss:list")
    @GetMapping("/list")
    public TableDataInfo<ImageVo> list(@Validated(QueryGroup.class) ImageBo bo, PageQuery pageQuery) {
        return imageService.queryPageList(bo, pageQuery);
    }
    /**
     * 查询图片基于ID串
     *
     * @param imageIds 图片ID串
     */
    @SaCheckPermission("edu:image:query")
    @GetMapping("/listByIds/{imageIds}")
    public R<List<ImageVo>> listByIds(@NotEmpty(message = "主键不能为空")
                                      @PathVariable Long[] imageIds) {
        List<ImageVo> list = imageService.listByIds(Arrays.asList(imageIds));
        return R.ok(list);
    }

    /**
     * 上传图片
     *
     * @param file 图片文件
     */
    @SaCheckPermission("edu:image:upload")
    @Log(title = "图片上传", businessType = BusinessType.INSERT)
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<OssUploadVo> upload(@RequestPart("file") MultipartFile file) {
        if (ObjectUtil.isNull(file) || file.isEmpty()) {
            return R.fail("上传图片不能为空");
        }
        ImageVo image = imageService.upload(file);
        OssUploadVo uploadVo = new OssUploadVo();
        uploadVo.setUrl(image.getUrl());
        uploadVo.setFileName(image.getOriginalName());
        uploadVo.setId(image.getImageId().toString());
        return R.ok(uploadVo);
    }

    /**
     * 下载图片
     *
     * @param imageId 图片ID
     */
    @SaCheckPermission("edu:image:download")
    @GetMapping("/download/{imageId}")
    public void download(@PathVariable Long imageId, HttpServletResponse response) throws IOException {
        imageService.download(imageId, response);
    }

    /**
     * 删除图片
     *
     * @param imageIds 图片ID串
     */
    @SaCheckPermission("edu:image:remove")
    @Log(title = "图片删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/{imageIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] imageIds) {
        return toAjax(imageService.deleteWithValidByIds(Arrays.asList(imageIds), true));
    }
}
