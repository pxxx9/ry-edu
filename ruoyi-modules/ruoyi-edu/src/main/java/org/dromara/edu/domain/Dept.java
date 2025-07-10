package org.dromara.edu.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 部门对象 student_dept
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("student_dept")
public class Dept extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @TableId(value = "dept_id")
    private Long deptId;

    /**
     * 父部门
     */
    private Long parentId;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 显示顺序
     */
    private Long orderNum;

    /**
     * 删除标志（0-正常，1-删除）
     */
    @TableLogic
    private Long delFlag;


}
