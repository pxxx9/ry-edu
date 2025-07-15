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
import org.dromara.edu.domain.vo.DocumentVo;
import org.dromara.edu.domain.bo.DocumentBo;
import org.dromara.edu.service.IDocumentService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 文档
 *
 * @author Pyx
 * @date 2025-07-15
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/material/document")
public class DocumentController extends BaseController {

    private final IDocumentService documentService;

    /**
     * 查询文档列表
     */
    @SaCheckPermission("material:document:list")
    @GetMapping("/list")
    public TableDataInfo<DocumentVo> list(DocumentBo bo, PageQuery pageQuery) {
        return documentService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出文档列表
     */
    @SaCheckPermission("material:document:export")
    @Log(title = "文档", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DocumentBo bo, HttpServletResponse response) {
        List<DocumentVo> list = documentService.queryList(bo);
        ExcelUtil.exportExcel(list, "文档", DocumentVo.class, response);
    }

    /**
     * 获取文档详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("material:document:query")
    @GetMapping("/{id}")
    public R<DocumentVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(documentService.queryById(id));
    }

    /**
     * 新增文档
     */
    @SaCheckPermission("material:document:add")
    @Log(title = "文档", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DocumentBo bo) {
        return toAjax(documentService.insertByBo(bo));
    }

    /**
     * 修改文档
     */
    @SaCheckPermission("material:document:edit")
    @Log(title = "文档", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DocumentBo bo) {
        return toAjax(documentService.updateByBo(bo));
    }

    /**
     * 删除文档
     *
     * @param ids 主键串
     */
    @SaCheckPermission("material:document:remove")
    @Log(title = "文档", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(documentService.deleteWithValidByIds(List.of(ids), true));
    }
}
