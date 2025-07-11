package org.dromara.edu.domain.bo;

import org.dromara.edu.domain.Paper;
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
 * @date 2025-07-11
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
     * 试卷名称
     */
    @NotBlank(message = "试卷名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String title;

    /**
     * 试卷总分
     */
    private Long score;

    /**
     * 及格分数
     */
    private Long passScore;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 组卷方式（1:固定试卷, 2:随机试卷）
     */
    private Long combinationMode;

    /**
     * 扩展配置（存储随机规则等JSON数据）
     */
    private String extra;


}
