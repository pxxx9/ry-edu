package org.dromara.edu.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 试卷对象 exam_paper
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_paper")
public class Paper extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 试卷ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 试卷分类ID
     */
    private Long categoryId;

    /**
     * 试卷名称
     */
    private String name;

    /**
     * 试卷类型
     */
    private Integer type;

    /**
     * 试题数
     */
    private Integer questionCount;

    /**
     * 总分
     */
    private Integer totalScore;

    /**
     * 及格分
     */
    private Integer passScore;

    /**
     * 删除标志（0-正常，1-删除）
     */
    @TableLogic
    private Long delFlag;


}
