package org.dromara.domain.vo;

import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.domain.StudentInfo;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 学生信息视图对象 student_info
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = StudentInfo.class)
public class StudentInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 头像URL
     */
    @ExcelProperty(value = "头像URL")
    private String avatar;

    /**
     * 头像URLUrl
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "avatar")
    private String avatarUrl;
    /**
     * 学生姓名
     */
    @ExcelProperty(value = "学生姓名")
    private String name;

    /**
     * 登录账号
     */
    @ExcelProperty(value = "登录账号")
    private String username;

    /**
     * 密码
     */
    @ExcelProperty(value = "密码")
    private String password;

    /**
     * 部门
     */
    @ExcelProperty(value = "部门")
    private Long deptId;


}
