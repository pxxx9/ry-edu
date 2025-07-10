package org.dromara.edu.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 题库对象 exam_question
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("exam_question")
public class Question extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 题目ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 题目内容
     */
    private String questionContent;

    /**
     * 题型
     */
    private Long questionType;

    /**
     * 难度
     */
    private Long difficulty;

    /**
     * 正确答案
     */
    private String correctAnswer;

    /**
     * 删除标志（0-正常，1-删除）
     */
    @TableLogic
    private Long delFlag;


}
