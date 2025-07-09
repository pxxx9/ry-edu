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
import org.dromara.domain.vo.StudentInfoVo;
import org.dromara.domain.bo.StudentInfoBo;
import org.dromara.service.IStudentInfoService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 学生信息
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/student/studentInfo")
public class StudentInfoController extends BaseController {

    private final IStudentInfoService studentInfoService;

    /**
     * 查询学生信息列表
     */
    @SaCheckPermission("student:studentInfo:list")
    @GetMapping("/list")
    public TableDataInfo<StudentInfoVo> list(StudentInfoBo bo, PageQuery pageQuery) {
        return studentInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出学生信息列表
     */
    @SaCheckPermission("student:studentInfo:export")
    @Log(title = "学生信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(StudentInfoBo bo, HttpServletResponse response) {
        List<StudentInfoVo> list = studentInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "学生信息", StudentInfoVo.class, response);
    }

    /**
     * 获取学生信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("student:studentInfo:query")
    @GetMapping("/{id}")
    public R<StudentInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(studentInfoService.queryById(id));
    }

    /**
     * 新增学生信息
     */
    @SaCheckPermission("student:studentInfo:add")
    @Log(title = "学生信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody StudentInfoBo bo) {
        return toAjax(studentInfoService.insertByBo(bo));
    }

    /**
     * 修改学生信息
     */
    @SaCheckPermission("student:studentInfo:edit")
    @Log(title = "学生信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody StudentInfoBo bo) {
        return toAjax(studentInfoService.updateByBo(bo));
    }

    /**
     * 删除学生信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("student:studentInfo:remove")
    @Log(title = "学生信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(studentInfoService.deleteWithValidByIds(List.of(ids), true));
    }
}
