package org.dromara.edu.domain.vo;

import org.dromara.edu.domain.ExamBank;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 题库视图对象 exam_bank
 *
 * @author Pyx
 * @date 2025-07-10
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ExamBank.class)
public class ExamBankVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 题库ID
     */
    @ExcelProperty(value = "题库ID")
    private Long id;

    /**
     * 题库名称
     */
    @ExcelProperty(value = "题库名称")
    private String bankName;

    /**
     * 单选题数量
     */
    @ExcelProperty(value = "单选题数量")
    private Long singleChoiceCount;

    /**
     * 多选题数量
     */
    @ExcelProperty(value = "多选题数量")
    private Long multipleChoiceCount;

    /**
     * 填空题数量
     */
    @ExcelProperty(value = "填空题数量")
    private Long fillBlankCount;

    /**
     * 判断题数量
     */
    @ExcelProperty(value = "判断题数量")
    private Long judgeCount;

    /**
     * 问答题数量
     */
    @ExcelProperty(value = "问答题数量")
    private Long answerCount;

    /**
     * 题帽题数量
     */
    @ExcelProperty(value = "题帽题数量")
    private Long caseCount;


}
