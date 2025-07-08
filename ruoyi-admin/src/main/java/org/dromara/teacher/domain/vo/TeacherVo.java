package org.dromara.teacher.domain.vo;

import org.dromara.teacher.domain.Teacher;
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
 * 讲师视图对象 edu_teacher
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Teacher.class)
public class TeacherVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 讲师ID
     */
    @ExcelProperty(value = "讲师ID")
    private Long id;

    /**
     * 讲师姓名
     */
    @ExcelProperty(value = "讲师姓名")
    private String name;


}
