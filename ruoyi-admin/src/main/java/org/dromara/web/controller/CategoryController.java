package org.dromara.web.controller;

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
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.edu.domain.vo.CategoryVo;
import org.dromara.edu.domain.bo.CategoryBo;
import org.dromara.edu.service.ICategoryService;

/**
 * 通用分类
 *
 * @author Pyx
 * @date 2025-07-14
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/category/category")
public class CategoryController extends BaseController {

    private final ICategoryService categoryService;

    /**
     * 查询通用分类列表
     */
    @SaCheckPermission("category:category:list")
    @GetMapping("/list")
    public R<List<CategoryVo>> list(CategoryBo bo) {
        List<CategoryVo> list = categoryService.queryList(bo);
        return R.ok(list);
    }

    /**
     * 导出通用分类列表
     */
    @SaCheckPermission("category:category:export")
    @Log(title = "通用分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CategoryBo bo, HttpServletResponse response) {
        List<CategoryVo> list = categoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "通用分类", CategoryVo.class, response);
    }

    /**
     * 获取通用分类详细信息
     *
     * @param categoryId 主键
     */
    @SaCheckPermission("category:category:query")
    @GetMapping("/{categoryId}")
    public R<CategoryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long categoryId) {
        return R.ok(categoryService.queryById(categoryId));
    }

    /**
     * 新增通用分类
     */
    @SaCheckPermission("category:category:add")
    @Log(title = "通用分类", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CategoryBo bo) {
        return toAjax(categoryService.insertByBo(bo));
    }

    /**
     * 修改通用分类
     */
    @SaCheckPermission("category:category:edit")
    @Log(title = "通用分类", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CategoryBo bo) {
        return toAjax(categoryService.updateByBo(bo));
    }

    /**
     * 删除通用分类
     *
     * @param categoryIds 主键串
     */
    @SaCheckPermission("category:category:remove")
    @Log(title = "通用分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] categoryIds) {
        return toAjax(categoryService.deleteWithValidByIds(List.of(categoryIds), true));
    }
}
