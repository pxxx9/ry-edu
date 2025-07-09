package org.dromara.domain.bo;

import org.dromara.domain.Question;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 题库业务对象 exam_question
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Question.class, reverseConvertGenerate = false)
public class QuestionBo extends BaseEntity {

    /**
     * 题目ID
     */
    private Long id;

    /**
     * 题目内容
     */
    @NotBlank(message = "题目内容不能为空", groups = { AddGroup.class, EditGroup.class })
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


}
