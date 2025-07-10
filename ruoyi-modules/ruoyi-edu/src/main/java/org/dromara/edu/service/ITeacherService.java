package org.dromara.edu.service;


import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.edu.domain.bo.TeacherBo;
import org.dromara.edu.domain.vo.TeacherVo;

import java.util.Collection;
import java.util.List;

/**
 * 讲师Service接口
 *
 * @author Pyx
 * @date 2025-07-03
 */
public interface ITeacherService {

    /**
     * 查询讲师
     *
     * @param id 主键
     * @return 讲师
     */
    TeacherVo queryById(Long id);

    /**
     * 分页查询讲师列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 讲师分页列表
     */
    TableDataInfo<TeacherVo> queryPageList(TeacherBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的讲师列表
     *
     * @param bo 查询条件
     * @return 讲师列表
     */
    List<TeacherVo> queryList(TeacherBo bo);

    /**
     * 新增讲师
     *
     * @param bo 讲师
     * @return 是否新增成功
     */
    Boolean insertByBo(TeacherBo bo);

    /**
     * 修改讲师
     *
     * @param bo 讲师
     * @return 是否修改成功
     */
    Boolean updateByBo(TeacherBo bo);

    /**
     * 校验并批量删除讲师信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
