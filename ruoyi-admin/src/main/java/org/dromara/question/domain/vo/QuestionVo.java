package org.dromara.question.domain.vo;

import org.dromara.question.domain.Question;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 题库视图对象 exam_question
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Question.class)
public class QuestionVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 题目ID
     */
    @ExcelProperty(value = "题目ID")
    private Long id;

    /**
     * 题目内容
     */
    @ExcelProperty(value = "题目内容")
    private String questionContent;

    /**
     * 题型
     */
    @ExcelProperty(value = "题型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "question_type")
    private Long questionType;

    /**
     * 难度
     */
    @ExcelProperty(value = "难度", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "question_difficulty")
    private Long difficulty;

    /**
     * 正确答案
     */
    @ExcelProperty(value = "正确答案")
    private String correctAnswer;


}
