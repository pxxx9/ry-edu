package org.dromara.edu.domain.bo;

import org.dromara.edu.domain.Document;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 文档业务对象 material_document
 *
 * @author Pyx
 * @date 2025-07-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Document.class, reverseConvertGenerate = false)
public class DocumentBo extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 文档名称
     */
    @NotBlank(message = "文档名称不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 分类
     */
    private Long categoryId;


}
