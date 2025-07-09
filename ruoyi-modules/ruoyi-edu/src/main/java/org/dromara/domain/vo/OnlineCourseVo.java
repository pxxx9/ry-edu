package org.dromara.domain.vo;

import org.dromara.domain.OnlineCourse;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 线上课程视图对象 edu_online_course
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = OnlineCourse.class)
public class OnlineCourseVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @ExcelProperty(value = "课程ID")
    private Long id;

    /**
     * 课程名称
     */
    @ExcelProperty(value = "课程名称")
    private String courseName;

    /**
     * 分类
     */
    @ExcelProperty(value = "分类", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "edu_type")
    private Long categoryId;

    /**
     * 课程类型
     */
    @ExcelProperty(value = "课程类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "edu_is_required")
    private String courseType;

    /**
     * 学员人数
     */
    @ExcelProperty(value = "学员人数")
    private Long studentCount;

    /**
     * 讲师
     */
    @ExcelProperty(value = "讲师")
    private Long teacherId;


}
