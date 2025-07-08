package org.dromara.student.domain.vo;

import org.dromara.student.domain.StudentGroup;
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
 * 用户组视图对象 student_group
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = StudentGroup.class)
public class StudentGroupVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 用户组名称
     */
    @ExcelProperty(value = "用户组名称")
    private String groupName;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ExcelProperty(value = "创建人")
    private Long createBy;


}
