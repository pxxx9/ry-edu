package org.dromara.edu.domain.vo;

import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.edu.domain.Image;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 图片视图对象 material_image
 *
 * @author Pyx
 * @date 2025-07-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Image.class)
public class ImageVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 对象存储主键
     */
    @ExcelProperty(value = "对象存储主键")
    private Long imageId;

    /**
     * 文件名
     */
    @ExcelProperty(value = "文件名")
    private String imageName;

//    /**
//     * 文件名Url
//     */
//    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "imageName")
//    private String imageNameUrl;
    /**
     * 原名
     */
    @ExcelProperty(value = "原名")
    private String originalName;

    /**
     * 文件后缀名
     */
    @ExcelProperty(value = "文件后缀名")
    private String imageSuffix;

    /**
     * URL地址
     */
    @ExcelProperty(value = "URL地址")
    private String url;

    /**
     * 扩展字段
     */
    @ExcelProperty(value = "扩展字段")
    private String ext1;


}
