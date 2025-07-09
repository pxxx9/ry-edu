package org.dromara.domain.bo;

import org.dromara.domain.Teacher;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 讲师业务对象 edu_teacher
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Teacher.class, reverseConvertGenerate = false)
public class TeacherBo extends BaseEntity {

    /**
     * 讲师ID
     */
    private Long id;

    /**
     * 讲师姓名
     */
    @NotBlank(message = "讲师姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;


}
