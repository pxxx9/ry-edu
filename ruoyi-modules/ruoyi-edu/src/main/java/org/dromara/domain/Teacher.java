package org.dromara.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 讲师对象 edu_teacher
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("edu_teacher")
public class Teacher extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 讲师ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 讲师姓名
     */
    private String name;

    /**
     * 删除标志（0-正常，1-删除）
     */
    @TableLogic
    private Long delFlag;


}
