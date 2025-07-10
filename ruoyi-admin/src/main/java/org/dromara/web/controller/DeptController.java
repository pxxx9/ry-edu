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
import org.dromara.edu.domain.vo.DeptVo;
import org.dromara.edu.domain.bo.DeptBo;
import org.dromara.edu.service.IDeptService;

/**
 * 部门
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/student/dept")
public class DeptController extends BaseController {

    private final IDeptService deptService;

    /**
     * 查询部门列表
     */
    @SaCheckPermission("student:dept:list")
    @GetMapping("/list")
    public R<List<DeptVo>> list(DeptBo bo) {
        List<DeptVo> list = deptService.queryList(bo);
        return R.ok(list);
    }

    /**
     * 导出部门列表
     */
    @SaCheckPermission("student:dept:export")
    @Log(title = "部门", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DeptBo bo, HttpServletResponse response) {
        List<DeptVo> list = deptService.queryList(bo);
        ExcelUtil.exportExcel(list, "部门", DeptVo.class, response);
    }

    /**
     * 获取部门详细信息
     *
     * @param deptId 主键
     */
    @SaCheckPermission("student:dept:query")
    @GetMapping("/{deptId}")
    public R<DeptVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long deptId) {
        return R.ok(deptService.queryById(deptId));
    }

    /**
     * 新增部门
     */
    @SaCheckPermission("student:dept:add")
    @Log(title = "部门", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DeptBo bo) {
        return toAjax(deptService.insertByBo(bo));
    }

    /**
     * 修改部门
     */
    @SaCheckPermission("student:dept:edit")
    @Log(title = "部门", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DeptBo bo) {
        return toAjax(deptService.updateByBo(bo));
    }

    /**
     * 删除部门
     *
     * @param deptIds 主键串
     */
    @SaCheckPermission("student:dept:remove")
    @Log(title = "部门", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] deptIds) {
        return toAjax(deptService.deleteWithValidByIds(List.of(deptIds), true));
    }
}
