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
import org.dromara.domain.vo.OnlineCourseVo;
import org.dromara.domain.bo.OnlineCourseBo;
import org.dromara.service.IOnlineCourseService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 线上课程
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/course/onlineCourse")
public class OnlineCourseController extends BaseController {

    private final IOnlineCourseService onlineCourseService;

    /**
     * 查询线上课程列表
     */
    @SaCheckPermission("course:onlineCourse:list")
    @GetMapping("/list")
    public TableDataInfo<OnlineCourseVo> list(OnlineCourseBo bo, PageQuery pageQuery) {
        return onlineCourseService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出线上课程列表
     */
    @SaCheckPermission("course:onlineCourse:export")
    @Log(title = "线上课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OnlineCourseBo bo, HttpServletResponse response) {
        List<OnlineCourseVo> list = onlineCourseService.queryList(bo);
        ExcelUtil.exportExcel(list, "线上课程", OnlineCourseVo.class, response);
    }

    /**
     * 获取线上课程详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("course:onlineCourse:query")
    @GetMapping("/{id}")
    public R<OnlineCourseVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(onlineCourseService.queryById(id));
    }

    /**
     * 新增线上课程
     */
    @SaCheckPermission("course:onlineCourse:add")
    @Log(title = "线上课程", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OnlineCourseBo bo) {
        return toAjax(onlineCourseService.insertByBo(bo));
    }

    /**
     * 修改线上课程
     */
    @SaCheckPermission("course:onlineCourse:edit")
    @Log(title = "线上课程", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OnlineCourseBo bo) {
        return toAjax(onlineCourseService.updateByBo(bo));
    }

    /**
     * 删除线上课程
     *
     * @param ids 主键串
     */
    @SaCheckPermission("course:onlineCourse:remove")
    @Log(title = "线上课程", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(onlineCourseService.deleteWithValidByIds(List.of(ids), true));
    }
}
