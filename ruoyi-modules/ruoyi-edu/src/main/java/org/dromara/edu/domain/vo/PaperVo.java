package org.dromara.edu.domain.vo;

import org.dromara.edu.domain.Paper;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 试卷视图对象 exam_paper
 *
 * @author Pyx
 * @date 2025-07-11
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
     * 试卷名称
     */
    @ExcelProperty(value = "试卷名称")
    private String title;

    /**
     * 试卷总分
     */
    @ExcelProperty(value = "试卷总分")
    private Long score;

    /**
     * 及格分数
     */
    @ExcelProperty(value = "及格分数")
    private Long passScore;

    /**
     * 分类ID
     */
    @ExcelProperty(value = "分类ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "edu_type")
    private Long categoryId;

    /**
     * 组卷方式（1:固定试卷, 2:随机试卷）
     */
    @ExcelProperty(value = "组卷方式", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "exam_paper_type")
    private Long combinationMode;

    /**
     * 扩展配置（存储随机规则等JSON数据）
     */
    @ExcelProperty(value = "扩展配置", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "存=储随机规则等JSON数据")
    private String extra;


}
