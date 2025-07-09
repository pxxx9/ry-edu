package org.dromara.domain.bo;

import org.dromara.domain.Image;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 图片业务对象 material_image
 *
 * @author Pyx
 * @date 2025-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Image.class, reverseConvertGenerate = false)
public class ImageBo extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 图片名称
     */
    private String imageName;

    /**
     * 图片路径
     */
    @NotBlank(message = "图片路径不能为空", groups = { AddGroup.class, EditGroup.class })
    private String imagePath;


}
