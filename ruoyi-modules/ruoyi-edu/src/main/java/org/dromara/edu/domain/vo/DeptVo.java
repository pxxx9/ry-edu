package org.dromara.edu.domain.vo;

import org.dromara.edu.domain.Dept;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 部门视图对象 student_dept
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Dept.class)
public class DeptVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @ExcelProperty(value = "部门ID")
    private Long deptId;

    /**
     * 父部门
     */
    @ExcelProperty(value = "父部门")
    private Long parentId;

    /**
     * 祖级列表
     */
    @ExcelProperty(value = "祖级列表")
    private String ancestors;

    /**
     * 部门名称
     */
    @ExcelProperty(value = "部门名称")
    private String deptName;

    /**
     * 显示顺序
     */
    @ExcelProperty(value = "显示顺序")
    private Long orderNum;


}
