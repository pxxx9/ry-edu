package org.dromara.material.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.material.domain.vo.ImageVo;
import org.dromara.material.domain.bo.ImageBo;
import org.dromara.material.service.IImageService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 图片
 *
 * @author Pyx
 * @date 2025-07-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/material/image")
public class ImageController extends BaseController {

    private final IImageService imageService;

    /**
     * 查询图片列表
     */
    @SaCheckPermission("material:image:list")
    @GetMapping("/list")
    public TableDataInfo<ImageVo> list(ImageBo bo, PageQuery pageQuery) {
        return imageService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出图片列表
     */
    @SaCheckPermission("material:image:export")
    @Log(title = "图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImageBo bo, HttpServletResponse response) {
        List<ImageVo> list = imageService.queryList(bo);
        ExcelUtil.exportExcel(list, "图片", ImageVo.class, response);
    }

    /**
     * 获取图片详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("material:image:query")
    @GetMapping("/{id}")
    public R<ImageVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imageService.queryById(id));
    }

    /**
     * 新增图片
     */
    @SaCheckPermission("material:image:add")
    @Log(title = "图片", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImageBo bo) {
        return toAjax(imageService.insertByBo(bo));
    }

    /**
     * 修改图片
     */
    @SaCheckPermission("material:image:edit")
    @Log(title = "图片", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImageBo bo) {
        return toAjax(imageService.updateByBo(bo));
    }

    /**
     * 删除图片
     *
     * @param ids 主键串
     */
    @SaCheckPermission("material:image:remove")
    @Log(title = "图片", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imageService.deleteWithValidByIds(List.of(ids), true));
    }
}
