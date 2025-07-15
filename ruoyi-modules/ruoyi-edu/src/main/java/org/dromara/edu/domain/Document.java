package org.dromara.edu.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 文档对象 material_document
 *
 * @author Pyx
 * @date 2025-07-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("material_document")
public class Document extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 文档名称
     */
    private String documentName;

    /**
     * URL地址
     */
    private String url;

    private Long ossId;

    /**
     * 文档大小
     */
    private String size;

    /**
     * 扩展字段
     */
    private String ext1;

    /**
     * 分类
     */
    private Long categoryId;

    /**
     * 删除标志（0-正常，1-删除）
     */
    @TableLogic
    private Long delFlag;


}
