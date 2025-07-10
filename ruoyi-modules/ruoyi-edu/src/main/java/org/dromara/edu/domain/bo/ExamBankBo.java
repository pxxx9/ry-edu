package org.dromara.edu.domain.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.dromara.edu.domain.ExamBank;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 题库业务对象 exam_bank
 *
 * @author Pyx
 * @date 2025-07-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ExamBank.class, reverseConvertGenerate = false)
public class ExamBankBo extends BaseEntity {

    /**
     * 题库ID
     */

    private Long id;

    /**
     * 题库名称
     */
    @NotBlank(message = "题库名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bankName;

    /**
     * 单选题数量
     */
    private Long singleChoiceCount;

    /**
     * 多选题数量
     */
    private Long multipleChoiceCount;

    /**
     * 填空题数量
     */
    private Long fillBlankCount;

    /**
     * 判断题数量
     */
    private Long judgeCount;

    /**
     * 问答题数量
     */
    private Long answerCount;

    /**
     * 题帽题数量
     */
    private Long caseCount;


}
