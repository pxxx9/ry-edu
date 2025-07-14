package org.dromara.edu.domain.vo;

import org.dromara.edu.domain.Category;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 通用分类视图对象 common_category
 *
 * @author Pyx
 * @date 2025-07-14
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Category.class)
public class CategoryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @ExcelProperty(value = "分类ID")
    private Long categoryId;

    /**
     * 父分类ID (0表示根节点)
     */
    @ExcelProperty(value = "父分类ID (0表示根节点)")
    private Long parentId;

    /**
     * 祖级列表
     */
    @ExcelProperty(value = "祖级列表")
    private String categoryPath;

    /**
     * 分类名称
     */
    @ExcelProperty(value = "分类名称")
    private String categoryName;

    /**
     * 排序号
     */
    @ExcelProperty(value = "排序号")
    private Long sortOrder;


}
