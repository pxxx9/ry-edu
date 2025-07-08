package org.dromara.material.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;

import java.io.Serial;

/**
 * 图片对象 material_image
 *
 * @author Pyx
 * @date 2025-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("material_image")
public class Image extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 图片名称
     */
    private String imageName;

    /**
     * 图片路径
     */
    private String imagePath;

    /**
     * 删除标志（0-正常，1-删除）
     */
    @TableLogic
    private Long delFlag;


}
