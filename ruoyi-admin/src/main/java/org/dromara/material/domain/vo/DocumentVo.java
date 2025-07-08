package org.dromara.material.domain.vo;

import org.dromara.material.domain.Document;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 文档视图对象 material_document
 *
 * @author Pyx
 * @date 2025-07-08
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
     * 文档路径
     */
    @ExcelProperty(value = "文档路径")
    private String documentPath;

    /**
     * 文档大小
     */
    @ExcelProperty(value = "文档大小")
    private String documentSize;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ExcelProperty(value = "创建人")
    private Long createBy;


}
