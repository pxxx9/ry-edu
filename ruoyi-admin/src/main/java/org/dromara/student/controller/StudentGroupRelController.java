package org.dromara.student.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.dromara.student.domain.vo.StudentGroupRelVo;
import org.dromara.student.domain.bo.StudentGroupRelBo;
import org.dromara.student.service.IStudentGroupRelService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 学生用户组关联
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/student/groupRel")
public class StudentGroupRelController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(StudentGroupRelController.class);
    private final IStudentGroupRelService studentGroupRelService;

    /**
     * 查询学生用户组关联列表
     */
    @SaCheckPermission("student:groupRel:list")
    @GetMapping("/list")
    public TableDataInfo<StudentGroupRelVo> list(StudentGroupRelBo bo, PageQuery pageQuery) {
        log.info("查询参数：{}", bo);
        return studentGroupRelService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出学生用户组关联列表
     */
    @SaCheckPermission("student:groupRel:export")
    @Log(title = "学生用户组关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(StudentGroupRelBo bo, HttpServletResponse response) {
        List<StudentGroupRelVo> list = studentGroupRelService.queryList(bo);
        ExcelUtil.exportExcel(list, "学生用户组关联", StudentGroupRelVo.class, response);
    }

    /**
     * 获取学生用户组关联详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("student:groupRel:query")
    @GetMapping("/{id}")
    public R<StudentGroupRelVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(studentGroupRelService.queryById(id));
    }

    /**
     * 新增学生用户组关联
     */
    @SaCheckPermission("student:groupRel:add")
    @Log(title = "学生用户组关联", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody StudentGroupRelBo bo) {
        return toAjax(studentGroupRelService.insertByBo(bo));
    }

    /**
     * 修改学生用户组关联
     */
    @SaCheckPermission("student:groupRel:edit")
    @Log(title = "学生用户组关联", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody StudentGroupRelBo bo) {
        return toAjax(studentGroupRelService.updateByBo(bo));
    }

    /**
     * 删除学生用户组关联
     *
     * @param ids 主键串
     */
    @SaCheckPermission("student:groupRel:remove")
    @Log(title = "学生用户组关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(studentGroupRelService.deleteWithValidByIds(List.of(ids), true));
    }
}
