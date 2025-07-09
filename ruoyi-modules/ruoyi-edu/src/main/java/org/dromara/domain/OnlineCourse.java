package org.dromara.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 线上课程对象 edu_online_course
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("edu_online_course")
public class OnlineCourse extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 课程名称
     */
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

    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;


}
