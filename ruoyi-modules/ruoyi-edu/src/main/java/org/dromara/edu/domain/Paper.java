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
 * @date 2025-07-11
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
     * 试卷名称
     */
    private String title;

    /**
     * 试卷总分
     */
    private Long score;

    /**
     * 及格分数
     */
    private Long passScore;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 组卷方式（1:固定试卷, 2:随机试卷）
     */
    private Long combinationMode;

    /**
     * 扩展配置（存储随机规则等JSON数据）
     */
    private String extra;

    /**
     * 删除标志（0-正常，1-删除）
     */
    @TableLogic
    private Long delFlag;


}
