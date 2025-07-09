package org.dromara.service;

import org.dromara.domain.vo.StudentGroupVo;
import org.dromara.domain.bo.StudentGroupBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 用户组Service接口
 *
 * @author Pyx
 * @date 2025-07-04
 */
public interface IStudentGroupService {

    /**
     * 查询用户组
     *
     * @param id 主键
     * @return 用户组
     */
    StudentGroupVo queryById(Long id);



    /**
     * 分页查询用户组列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 用户组分页列表
     */
    TableDataInfo<StudentGroupVo> queryPageList(StudentGroupBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的用户组列表
     *
     * @param bo 查询条件
     * @return 用户组列表
     */
    List<StudentGroupVo> queryList(StudentGroupBo bo);

    /**
     * 新增用户组
     *
     * @param bo 用户组
     * @return 是否新增成功
     */
    Boolean insertByBo(StudentGroupBo bo);

    /**
     * 修改用户组
     *
     * @param bo 用户组
     * @return 是否修改成功
     */
    Boolean updateByBo(StudentGroupBo bo);

    /**
     * 校验并批量删除用户组信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
