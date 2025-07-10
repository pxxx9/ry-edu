package org.dromara.edu.domain.bo;

import org.dromara.edu.domain.StudentGroupRel;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 学生用户组关联业务对象 student_group_rel
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = StudentGroupRel.class, reverseConvertGenerate = false)
public class StudentGroupRelBo extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户组
     */
    private Long groupId;

    /**
     * 学生
     */
    private Long studentId;


}
