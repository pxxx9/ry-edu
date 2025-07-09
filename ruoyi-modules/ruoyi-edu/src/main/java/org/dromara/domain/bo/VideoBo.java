package org.dromara.domain.bo;

import org.dromara.domain.Video;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 视频业务对象 material_video
 *
 * @author Pyx
 * @date 2025-07-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Video.class, reverseConvertGenerate = false)
public class VideoBo extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 视频名称
     */
    private String videoName;

    /**
     * 视频路径
     */
    @NotBlank(message = "视频路径不能为空", groups = { AddGroup.class, EditGroup.class })
    private String videoPath;

    /**
     * 视频时长
     */
    private String videoDuration;


}
