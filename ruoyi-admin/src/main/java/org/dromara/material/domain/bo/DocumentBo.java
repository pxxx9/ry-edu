package org.dromara.material.domain.bo;

import org.dromara.material.domain.Document;
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
 * @date 2025-07-08
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
    private String documentName;

    /**
     * 文档路径
     */
    @NotBlank(message = "文档路径不能为空", groups = { AddGroup.class, EditGroup.class })
    private String documentPath;

    /**
     * 文档大小
     */
    private String documentSize;


}
