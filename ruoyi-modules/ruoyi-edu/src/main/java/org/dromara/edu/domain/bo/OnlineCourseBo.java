package org.dromara.edu.domain.bo;

import org.dromara.edu.domain.OnlineCourse;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 线上课程业务对象 edu_online_course
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = OnlineCourse.class, reverseConvertGenerate = false)
public class OnlineCourseBo extends BaseEntity {

    /**
     * 课程ID
     */
    private Long id;

    /**
     * 课程名称
     */
    @NotBlank(message = "课程名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String courseName;

    /**
     * 分类
     */
    private Long categoryId;

    /**
     * 课程类型
     */
    private String courseType;

    /**
     * 学员人数
     */
    private Long studentCount;

    /**
     * 讲师
     */
    private Long teacherId;


}
