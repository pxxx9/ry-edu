package org.dromara.edu.domain.bo;

import org.dromara.edu.domain.Category;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 通用分类业务对象 common_category
 *
 * @author Pyx
 * @date 2025-07-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Category.class, reverseConvertGenerate = false)
public class CategoryBo extends BaseEntity {

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 父分类ID (0表示根节点)
     */
    private Long parentId;

    /**
     * 祖级列表
     */
    private String categoryPath;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String categoryName;

    /**
     * 排序号
     */
    private Long sortOrder;


}
