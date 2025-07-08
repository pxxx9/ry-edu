package org.dromara.course.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 线下课程对象 edu_offline_course
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("edu_offline_course")
public class OfflineCourse extends TenantEntity {

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
     * 授课时间
     */
    private Date courseTime;

    /**
     * 授课地点
     */
    private String courseLocation;

    /**
     * 指派学员
     */
    private Long studentCount;

    /**
     * 签到人数
     */
    private Long attendanceCount;

    /**
     * 讲师ID
     */
    private Long teacherId;

    /**
     * 删除标志（0-正常，1-删除）
     */
    @TableLogic
    private Long delFlag;

    /**
     * 分类
     */
    private Long categoryId;


}
