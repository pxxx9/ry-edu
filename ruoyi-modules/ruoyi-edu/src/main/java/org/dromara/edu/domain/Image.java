package org.dromara.edu.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 图片对象 material_image
 *
 * @author Pyx
 * @date 2025-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("material_image")
public class Image extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 对象存储主键
     */
    @TableId(value = "image_id")
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

    /**
     * 服务商
     */
    private String service;


}
