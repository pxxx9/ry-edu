package org.dromara.domain.bo;

import org.dromara.domain.Paper;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 试卷业务对象 exam_paper
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Paper.class, reverseConvertGenerate = false)
public class PaperBo extends BaseEntity {

    /**
     * 试卷ID
     */
    private Long id;

    /**
     * 试卷分类ID
     */
    private Long categoryId;

    /**
     * 试卷名称
     */
    @NotBlank(message = "试卷名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 试卷类型
     */
    private Integer type;

    /**
     * 试题数
     */
    private Integer questionCount;

    /**
     * 总分
     */
    private Integer totalScore;

    /**
     * 及格分
     */
    private Integer passScore;


}
