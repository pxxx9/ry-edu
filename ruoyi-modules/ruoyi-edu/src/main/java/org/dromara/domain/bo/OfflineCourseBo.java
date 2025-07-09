package org.dromara.domain.bo;

import org.dromara.domain.OfflineCourse;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * 线下课程业务对象 edu_offline_course
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = OfflineCourse.class, reverseConvertGenerate = false)
public class OfflineCourseBo extends BaseEntity {

    /**
     * 课程ID
     */
    private Long id;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 授课时间
     */
    @NotNull(message = "授课时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date courseTime;

    /**
     * 授课地点
     */
    @NotBlank(message = "授课地点不能为空", groups = { AddGroup.class, EditGroup.class })
    private String courseLocation;

    /**
     * 指派学员
     */
    @NotNull(message = "指派学员不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long studentCount;

    /**
     * 签到人数
     */
    @NotNull(message = "签到人数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long attendanceCount;

    /**
     * 讲师ID
     */
    @NotNull(message = "讲师ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long teacherId;

    /**
     * 分类
     */
    @NotNull(message = "分类不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long categoryId;


}
