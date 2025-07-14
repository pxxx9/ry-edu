package org.dromara.edu.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 通用分类对象 common_category
 *
 * @author Pyx
 * @date 2025-07-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("common_category")
public class Category extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @TableId(value = "category_id")
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
    private String categoryName;

    /**
     * 排序号
     */
    private Long sortOrder;

    /**
     * 删除标志（0-正常，1-删除）
     */
    @TableLogic
    private Long delFlag;


}
