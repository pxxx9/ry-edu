package org.dromara.course.controller;

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
import org.dromara.course.domain.vo.OfflineCourseVo;
import org.dromara.course.domain.bo.OfflineCourseBo;
import org.dromara.course.service.IOfflineCourseService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 线下课程
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/course/offlineCourse")
public class OfflineCourseController extends BaseController {

    private final IOfflineCourseService offlineCourseService;

    /**
     * 查询线下课程列表
     */
    @SaCheckPermission("course:offlineCourse:list")
    @GetMapping("/list")
    public TableDataInfo<OfflineCourseVo> list(OfflineCourseBo bo, PageQuery pageQuery) {
        return offlineCourseService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出线下课程列表
     */
    @SaCheckPermission("course:offlineCourse:export")
    @Log(title = "线下课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OfflineCourseBo bo, HttpServletResponse response) {
        List<OfflineCourseVo> list = offlineCourseService.queryList(bo);
        ExcelUtil.exportExcel(list, "线下课程", OfflineCourseVo.class, response);
    }

    /**
     * 获取线下课程详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("course:offlineCourse:query")
    @GetMapping("/{id}")
    public R<OfflineCourseVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(offlineCourseService.queryById(id));
    }

    /**
     * 新增线下课程
     */
    @SaCheckPermission("course:offlineCourse:add")
    @Log(title = "线下课程", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OfflineCourseBo bo) {
        return toAjax(offlineCourseService.insertByBo(bo));
    }

    /**
     * 修改线下课程
     */
    @SaCheckPermission("course:offlineCourse:edit")
    @Log(title = "线下课程", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OfflineCourseBo bo) {
        return toAjax(offlineCourseService.updateByBo(bo));
    }

    /**
     * 删除线下课程
     *
     * @param ids 主键串
     */
    @SaCheckPermission("course:offlineCourse:remove")
    @Log(title = "线下课程", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(offlineCourseService.deleteWithValidByIds(List.of(ids), true));
    }
}
