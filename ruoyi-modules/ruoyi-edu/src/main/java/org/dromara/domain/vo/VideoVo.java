package org.dromara.domain.vo;

import org.dromara.domain.Video;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 视频视图对象 material_video
 *
 * @author Pyx
 * @date 2025-07-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Video.class)
public class VideoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 视频名称
     */
    @ExcelProperty(value = "视频名称")
    private String videoName;

    /**
     * 视频路径
     */
    @ExcelProperty(value = "视频路径")
    private String videoPath;

    /**
     * 视频时长
     */
    @ExcelProperty(value = "视频时长")
    private String videoDuration;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ExcelProperty(value = "创建人")
    private Long createBy;


}
