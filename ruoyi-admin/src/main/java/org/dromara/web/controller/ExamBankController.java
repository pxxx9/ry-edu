package org.dromara.web.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.extern.slf4j.Slf4j;
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
import org.dromara.edu.domain.vo.ExamBankVo;
import org.dromara.edu.domain.bo.ExamBankBo;
import org.dromara.edu.service.IExamBankService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 题库
 *
 * @author Pyx
 * @date 2025-07-10
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/question/bank")
public class ExamBankController extends BaseController {

    private final IExamBankService examBankService;

    /**
     * 查询题库列表
     */
    @SaCheckPermission("question:bank:list")
    @GetMapping("/list")
    public TableDataInfo<ExamBankVo> list(ExamBankBo bo, PageQuery pageQuery) {
        return examBankService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出题库列表
     */
    @SaCheckPermission("question:bank:export")
    @Log(title = "题库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ExamBankBo bo, HttpServletResponse response) {
        List<ExamBankVo> list = examBankService.queryList(bo);
        ExcelUtil.exportExcel(list, "题库", ExamBankVo.class, response);
    }

    /**
     * 获取题库详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("question:bank:query")
    @GetMapping("/{id}")
    public R<ExamBankVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(examBankService.queryById(id));
    }

    /**
     * 新增题库
     */
    @SaCheckPermission("question:bank:add")
    @Log(title = "题库", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ExamBankBo bo) {
        log.info("controller新增题库:{}",bo);
        return toAjax(examBankService.insertByBo(bo));
    }

    /**
     * 修改题库
     */
    @SaCheckPermission("question:bank:edit")
    @Log(title = "题库", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ExamBankBo bo) {
        return toAjax(examBankService.updateByBo(bo));
    }

    /**
     * 删除题库
     *
     * @param ids 主键串
     */
    @SaCheckPermission("question:bank:remove")
    @Log(title = "题库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(examBankService.deleteWithValidByIds(List.of(ids), true));
    }
}
