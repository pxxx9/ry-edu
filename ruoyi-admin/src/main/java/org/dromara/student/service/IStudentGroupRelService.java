package org.dromara.student.service;

import org.dromara.student.domain.vo.StudentGroupRelVo;
import org.dromara.student.domain.bo.StudentGroupRelBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 学生用户组关联Service接口
 *
 * @author Pyx
 * @date 2025-07-04
 */
public interface IStudentGroupRelService {

    /**
     * 查询学生用户组关联
     *
     * @param id 主键
     * @return 学生用户组关联
     */
    StudentGroupRelVo queryById(Long id);

    /**
     * 分页查询学生用户组关联列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 学生用户组关联分页列表
     */
    TableDataInfo<StudentGroupRelVo> queryPageList(StudentGroupRelBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的学生用户组关联列表
     *
     * @param bo 查询条件
     * @return 学生用户组关联列表
     */
    List<StudentGroupRelVo> queryList(StudentGroupRelBo bo);

    /**
     * 新增学生用户组关联
     *
     * @param bo 学生用户组关联
     * @return 是否新增成功
     */
    Boolean insertByBo(StudentGroupRelBo bo);

    /**
     * 修改学生用户组关联
     *
     * @param bo 学生用户组关联
     * @return 是否修改成功
     */
    Boolean updateByBo(StudentGroupRelBo bo);

    /**
     * 校验并批量删除学生用户组关联信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
