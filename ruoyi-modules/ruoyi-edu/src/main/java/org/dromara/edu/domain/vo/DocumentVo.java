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
 * @date 2025-07-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Document.class)
public class DocumentVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 对象存储主键
     */
    @ExcelProperty(value = "对象存储主键")
    private Long documentId;

    /**
     * 文件名
     */
    @ExcelProperty(value = "文件名")
    private String documentName;

    /**
     * 原名
     */
    @ExcelProperty(value = "原名")
    private String originalName;

    /**
     * 文件后缀名
     */
    @ExcelProperty(value = "文件后缀名")
    private String documentSuffix;

    /**
     * URL地址
     */
    @ExcelProperty(value = "URL地址")
    private String url;


}
