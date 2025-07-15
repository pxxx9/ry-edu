package org.dromara.edu.controller;

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
import org.dromara.edu.domain.vo.VideoVo;
import org.dromara.edu.domain.bo.VideoBo;
import org.dromara.edu.service.IVideoService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 视频
 *
 * @author Pyx
 * @date 2025-07-14
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/material/video")
public class VideoController extends BaseController {

    private final IVideoService videoService;

    /**
     * 查询视频列表
     */
    @SaCheckPermission("material:video:list")
    @GetMapping("/list")
    public TableDataInfo<VideoVo> list(VideoBo bo, PageQuery pageQuery) {
        return videoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出视频列表
     */
    @SaCheckPermission("material:video:export")
    @Log(title = "视频", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(VideoBo bo, HttpServletResponse response) {
        List<VideoVo> list = videoService.queryList(bo);
        ExcelUtil.exportExcel(list, "视频", VideoVo.class, response);
    }

    /**
     * 获取视频详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("material:video:query")
    @GetMapping("/{id}")
    public R<VideoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(videoService.queryById(id));
    }

    /**
     * 新增视频
     */
    @SaCheckPermission("material:video:add")
    @Log(title = "视频", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody VideoBo bo) {
        return toAjax(videoService.insertByBo(bo));
    }

    /**
     * 修改视频
     */
    @SaCheckPermission("material:video:edit")
    @Log(title = "视频", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody VideoBo bo) {
        return toAjax(videoService.updateByBo(bo));
    }

    /**
     * 删除视频
     *
     * @param ids 主键串
     */
    @SaCheckPermission("material:video:remove")
    @Log(title = "视频", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(videoService.deleteWithValidByIds(List.of(ids), true));
    }
}
