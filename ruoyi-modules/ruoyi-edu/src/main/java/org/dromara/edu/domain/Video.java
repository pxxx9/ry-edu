package org.dromara.edu.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 视频对象 material_video
 *
 * @author Pyx
 * @date 2025-07-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("material_video")
public class Video extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 视频名称
     */
    private String videoName;

    /**
     * 视频路径
     */
    private String videoPath;

    /**
     * 视频时长
     */
    private String videoDuration;

    /**
     * 删除标志（0-正常，1-删除）
     */
    @TableLogic
    private Long delFlag;


}
