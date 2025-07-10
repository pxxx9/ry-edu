package org.dromara.edu.domain.bo;

import org.dromara.edu.domain.Image;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片业务对象 material_image
 *
 * @author Pyx
 * @date 2025-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Image.class, reverseConvertGenerate = false)
public class ImageBo extends BaseEntity {

    /**
     * 对象存储主键
     */
    private Long imageId;

    /**
     * 文件名
     */
    private String imageName;

    /**
     * 原名
     */
    private String originalName;

    /**
     * 文件后缀名
     */
    private String imageSuffix;

    /**
     * URL地址
     */
    private String url;

    /**
     * 扩展字段
     */
    private String ext1;


}
