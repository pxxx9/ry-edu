package org.dromara.edu.domain.vo;

import org.dromara.edu.domain.Image;
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
 * 图片视图对象 material_image
 *
 * @author Pyx
 * @date 2025-07-15
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Image.class)
public class ImageVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 图片名称
     */
    @ExcelProperty(value = "图片名称")
    private String imageName;

    /**
     * URL地址
     */
    @ExcelProperty(value = "URL地址")
    private String url;

    /**
     * 分类
     */
    @ExcelProperty(value = "分类")
    private Long categoryId;


}
