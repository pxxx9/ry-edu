package org.dromara.question.domain.vo;

import org.dromara.question.domain.Paper;
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
 * 试卷视图对象 exam_paper
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Paper.class)
public class PaperVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 试卷ID
     */
    @ExcelProperty(value = "试卷ID")
    private Long id;

    /**
     * 试卷分类ID
     */
    @ExcelProperty(value = "试卷分类ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "edu_type")
    private Long categoryId;

    /**
     * 试卷名称
     */
    @ExcelProperty(value = "试卷名称")
    private String name;

    /**
     * 试卷类型
     */
    @ExcelProperty(value = "试卷类型")
    private Integer type;

    /**
     * 试题数
     */
    @ExcelProperty(value = "试题数")
    private Integer questionCount;

    /**
     * 总分
     */
    @ExcelProperty(value = "总分")
    private Integer totalScore;

    /**
     * 及格分
     */
    @ExcelProperty(value = "及格分")
    private Integer passScore;


}
