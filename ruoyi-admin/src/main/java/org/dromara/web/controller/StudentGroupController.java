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
import org.dromara.domain.vo.StudentGroupVo;
import org.dromara.domain.bo.StudentGroupBo;
import org.dromara.service.IStudentGroupService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 用户组
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/student/group")
public class StudentGroupController extends BaseController {

    private final IStudentGroupService studentGroupService;

    /**
     * 查询用户组列表
     */
    @SaCheckPermission("student:group:list")
    @GetMapping("/list")
    public TableDataInfo<StudentGroupVo> list(StudentGroupBo bo, PageQuery pageQuery) {
        return studentGroupService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户组列表
     */
    @SaCheckPermission("student:group:export")
    @Log(title = "用户组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(StudentGroupBo bo, HttpServletResponse response) {
        List<StudentGroupVo> list = studentGroupService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户组", StudentGroupVo.class, response);
    }

    /**
     * 获取用户组详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("student:group:query")
    @GetMapping("/{id}")
    public R<StudentGroupVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(studentGroupService.queryById(id));
    }



    /**
     * 新增用户组
     */
    @SaCheckPermission("student:group:add")
    @Log(title = "用户组", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody StudentGroupBo bo) {
        return toAjax(studentGroupService.insertByBo(bo));
    }

    /**
     * 修改用户组
     */
    @SaCheckPermission("student:group:edit")
    @Log(title = "用户组", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody StudentGroupBo bo) {
        return toAjax(studentGroupService.updateByBo(bo));
    }

    /**
     * 删除用户组
     *
     * @param ids 主键串
     */
    @SaCheckPermission("student:group:remove")
    @Log(title = "用户组", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(studentGroupService.deleteWithValidByIds(List.of(ids), true));
    }
}
