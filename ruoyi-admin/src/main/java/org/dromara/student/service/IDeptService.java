package org.dromara.student.service;

import org.dromara.student.domain.vo.DeptVo;
import org.dromara.student.domain.bo.DeptBo;

import java.util.Collection;
import java.util.List;

/**
 * 部门Service接口
 *
 * @author Pyx
 * @date 2025-07-04
 */
public interface IDeptService {

    /**
     * 查询部门
     *
     * @param deptId 主键
     * @return 部门
     */
    DeptVo queryById(Long deptId);


    /**
     * 查询符合条件的部门列表
     *
     * @param bo 查询条件
     * @return 部门列表
     */
    List<DeptVo> queryList(DeptBo bo);

    /**
     * 新增部门
     *
     * @param bo 部门
     * @return 是否新增成功
     */
    Boolean insertByBo(DeptBo bo);

    /**
     * 修改部门
     *
     * @param bo 部门
     * @return 是否修改成功
     */
    Boolean updateByBo(DeptBo bo);

    /**
     * 校验并批量删除部门信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
