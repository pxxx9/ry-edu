package org.dromara.edu.domain.bo;

import org.dromara.edu.domain.StudentInfo;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 学生信息业务对象 student_info
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = StudentInfo.class, reverseConvertGenerate = false)
public class StudentInfoBo extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 学生姓名
     */
    @NotBlank(message = "学生姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 登录账号
     */
    @NotBlank(message = "登录账号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String password;

    /**
     * 部门
     */
    @NotNull(message = "部门不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long deptId;


}
