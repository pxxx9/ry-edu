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
import org.dromara.domain.vo.QuestionVo;
import org.dromara.domain.bo.QuestionBo;
import org.dromara.service.IQuestionService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 题库
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/question/question")
public class QuestionController extends BaseController {

    private final IQuestionService questionService;

    /**
     * 查询题库列表
     */
    @SaCheckPermission("question:question:list")
    @GetMapping("/list")
    public TableDataInfo<QuestionVo> list(QuestionBo bo, PageQuery pageQuery) {
        return questionService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出题库列表
     */
    @SaCheckPermission("question:question:export")
    @Log(title = "题库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QuestionBo bo, HttpServletResponse response) {
        List<QuestionVo> list = questionService.queryList(bo);
        ExcelUtil.exportExcel(list, "题库", QuestionVo.class, response);
    }

    /**
     * 获取题库详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("question:question:query")
    @GetMapping("/{id}")
    public R<QuestionVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(questionService.queryById(id));
    }

    /**
     * 新增题库
     */
    @SaCheckPermission("question:question:add")
    @Log(title = "题库", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QuestionBo bo) {
        return toAjax(questionService.insertByBo(bo));
    }

    /**
     * 修改题库
     */
    @SaCheckPermission("question:question:edit")
    @Log(title = "题库", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QuestionBo bo) {
        return toAjax(questionService.updateByBo(bo));
    }

    /**
     * 删除题库
     *
     * @param ids 主键串
     */
    @SaCheckPermission("question:question:remove")
    @Log(title = "题库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(questionService.deleteWithValidByIds(List.of(ids), true));
    }
}
