package org.dromara.edu.domain.vo;

import java.util.Date;

import org.dromara.edu.domain.OfflineCourse;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 线下课程视图对象 edu_offline_course
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = OfflineCourse.class)
public class OfflineCourseVo implements Serializable {

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
     * 授课时间
     */
    @ExcelProperty(value = "授课时间")
    private Date courseTime;

    /**
     * 授课地点
     */
    @ExcelProperty(value = "授课地点")
    private String courseLocation;

    /**
     * 指派学员
     */
    @ExcelProperty(value = "指派学员")
    private Long studentCount;

    /**
     * 签到人数
     */
    @ExcelProperty(value = "签到人数")
    private Long attendanceCount;

    /**
     * 讲师ID
     */
    @ExcelProperty(value = "讲师ID")
    private Long teacherId;

    /**
     * 分类
     */
    @ExcelProperty(value = "分类", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "edu_type")
    private Long categoryId;


}
