package org.dromara.student.domain.bo;

import org.dromara.student.domain.Dept;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 部门业务对象 student_dept
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Dept.class, reverseConvertGenerate = false)
public class DeptBo extends BaseEntity {

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 父部门
     */
    private Long parentId;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deptName;

    /**
     * 显示顺序
     */
    private Long orderNum;


}
