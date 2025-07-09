package org.dromara.domain.vo;

import org.dromara.domain.StudentGroupRel;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 学生用户组关联视图对象 student_group_rel
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = StudentGroupRel.class)
public class StudentGroupRelVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 用户组
     */
    @ExcelProperty(value = "用户组")
    private Long groupId;

    /**
     * 学生
     */
    @ExcelProperty(value = "学生")
    private Long studentId;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}
