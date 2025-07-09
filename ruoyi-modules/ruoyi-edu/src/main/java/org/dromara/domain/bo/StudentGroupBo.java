package org.dromara.domain.bo;

import org.dromara.domain.StudentGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 用户组业务对象 student_group
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = StudentGroup.class, reverseConvertGenerate = false)
public class StudentGroupBo extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户组名称
     */
    @NotBlank(message = "用户组名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String groupName;


}
