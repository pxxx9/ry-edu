package org.dromara.domain.vo;

import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.domain.Image;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 图片视图对象 material_image
 *
 * @author Pyx
 * @date 2025-07-08
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
     * 图片路径
     */
    @ExcelProperty(value = "图片路径")
    private String imagePath;

    /**
     * 图片路径Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "imagePath")
    private String imagePathUrl;
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
