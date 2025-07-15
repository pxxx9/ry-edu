package org.dromara.edu.domain.vo;

import org.dromara.edu.domain.Document;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 文档视图对象 material_document
 *
 * @author Pyx
 * @date 2025-07-15
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Document.class)
public class DocumentVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 文档名称
     */
    @ExcelProperty(value = "文档名称")
    private String documentName;

    /**
     * URL地址
     */
    @ExcelProperty(value = "URL地址")
    private String url;

    private Long ossId;

    /**
     * 文档大小
     */
    @ExcelProperty(value = "文档大小")
    private String size;

    /**
     * 分类
     */
    @ExcelProperty(value = "分类")
    private Long categoryId;


}
