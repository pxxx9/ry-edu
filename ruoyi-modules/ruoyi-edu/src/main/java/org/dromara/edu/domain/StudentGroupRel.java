package org.dromara.edu.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 学生用户组关联对象 student_group_rel
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("student_group_rel")
public class StudentGroupRel extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户组
     */
    private Long groupId;

    /**
     * 学生
     */
    private Long studentId;

    /**
     * 删除标志（0-正常，1-删除）
     */
    @TableLogic
    private Long delFlag;


}
