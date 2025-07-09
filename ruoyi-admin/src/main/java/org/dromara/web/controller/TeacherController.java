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
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.domain.vo.TeacherVo;
import org.dromara.domain.bo.TeacherBo;
import org.dromara.service.ITeacherService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 讲师
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/teacher/teacher")
public class TeacherController extends BaseController {

    private final ITeacherService teacherService;

    /**
     * 查询讲师列表
     */
    @SaCheckPermission("teacher:teacher:list")
    @GetMapping("/list")
    public TableDataInfo<TeacherVo> list(TeacherBo bo, PageQuery pageQuery) {
        return teacherService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出讲师列表
     */
    @SaCheckPermission("teacher:teacher:export")
    @Log(title = "讲师", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TeacherBo bo, HttpServletResponse response) {
        List<TeacherVo> list = teacherService.queryList(bo);
        ExcelUtil.exportExcel(list, "讲师", TeacherVo.class, response);
    }

    /**
     * 获取讲师详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("teacher:teacher:query")
    @GetMapping("/{id}")
    public R<TeacherVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(teacherService.queryById(id));
    }

    /**
     * 新增讲师
     */
    @SaCheckPermission("teacher:teacher:add")
    @Log(title = "讲师", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TeacherBo bo) {
        return toAjax(teacherService.insertByBo(bo));
    }

    /**
     * 修改讲师
     */
    @SaCheckPermission("teacher:teacher:edit")
    @Log(title = "讲师", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TeacherBo bo) {
        return toAjax(teacherService.updateByBo(bo));
    }

    /**
     * 删除讲师
     *
     * @param ids 主键串
     */
    @SaCheckPermission("teacher:teacher:remove")
    @Log(title = "讲师", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(teacherService.deleteWithValidByIds(List.of(ids), true));
    }
}
