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
 * @date 2025-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Document.class, reverseConvertGenerate = false)
public class DocumentBo extends BaseEntity {

    /**
     * 对象存储主键
     */
    private Long documentId;

    /**
     * 文件名
     */
    private String documentName;

    /**
     * 原名
     */
    @NotBlank(message = "原名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String originalName;

    /**
     * 文件后缀名
     */
    private String documentSuffix;

    /**
     * URL地址
     */
    private String url;


}
