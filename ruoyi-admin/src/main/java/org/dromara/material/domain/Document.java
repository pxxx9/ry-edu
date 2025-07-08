package org.dromara.material.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 文档对象 material_document
 *
 * @author Pyx
 * @date 2025-07-08
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
     * 文档路径
     */
    private String documentPath;

    /**
     * 文档大小
     */
    private String documentSize;

    /**
     * 删除标志（0-正常，1-删除）
     */
    @TableLogic
    private Long delFlag;


}
