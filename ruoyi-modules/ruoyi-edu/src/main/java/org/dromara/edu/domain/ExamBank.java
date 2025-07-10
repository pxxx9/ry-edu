package org.dromara.edu.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 题库对象 exam_bank
 *
 * @author Pyx
 * @date 2025-07-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_bank")
public class ExamBank extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 题库ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 题库名称
     */
    private String bankName;

    /**
     * 单选题数量
     */
    private Long singleChoiceCount;

    /**
     * 多选题数量
     */
    private Long multipleChoiceCount;

    /**
     * 填空题数量
     */
    private Long fillBlankCount;

    /**
     * 判断题数量
     */
    private Long judgeCount;

    /**
     * 问答题数量
     */
    private Long answerCount;

    /**
     * 题帽题数量
     */
    private Long caseCount;

    /**
     * 删除标志（0-正常，1-删除）
     */
    @TableLogic
    private Long delFlag;


}
