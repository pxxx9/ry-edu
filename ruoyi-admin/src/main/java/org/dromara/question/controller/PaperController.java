package org.dromara.question.controller;

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
import org.dromara.question.domain.vo.PaperVo;
import org.dromara.question.domain.bo.PaperBo;
import org.dromara.question.service.IPaperService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 试卷
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/question/paper")
public class PaperController extends BaseController {

    private final IPaperService paperService;

    /**
     * 查询试卷列表
     */
    @SaCheckPermission("question:paper:list")
    @GetMapping("/list")
    public TableDataInfo<PaperVo> list(PaperBo bo, PageQuery pageQuery) {
        return paperService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出试卷列表
     */
    @SaCheckPermission("question:paper:export")
    @Log(title = "试卷", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PaperBo bo, HttpServletResponse response) {
        List<PaperVo> list = paperService.queryList(bo);
        ExcelUtil.exportExcel(list, "试卷", PaperVo.class, response);
    }

    /**
     * 获取试卷详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("question:paper:query")
    @GetMapping("/{id}")
    public R<PaperVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(paperService.queryById(id));
    }

    /**
     * 新增试卷
     */
    @SaCheckPermission("question:paper:add")
    @Log(title = "试卷", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PaperBo bo) {
        return toAjax(paperService.insertByBo(bo));
    }

    /**
     * 修改试卷
     */
    @SaCheckPermission("question:paper:edit")
    @Log(title = "试卷", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PaperBo bo) {
        return toAjax(paperService.updateByBo(bo));
    }

    /**
     * 删除试卷
     *
     * @param ids 主键串
     */
    @SaCheckPermission("question:paper:remove")
    @Log(title = "试卷", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(paperService.deleteWithValidByIds(List.of(ids), true));
    }
}
