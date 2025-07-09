package org.dromara.service;

import org.dromara.domain.vo.StudentInfoVo;
import org.dromara.domain.bo.StudentInfoBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 学生信息Service接口
 *
 * @author Pyx
 * @date 2025-07-04
 */
public interface IStudentInfoService {

    /**
     * 查询学生信息
     *
     * @param id 主键
     * @return 学生信息
     */
    StudentInfoVo queryById(Long id);

    /**
     * 分页查询学生信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 学生信息分页列表
     */
    TableDataInfo<StudentInfoVo> queryPageList(StudentInfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的学生信息列表
     *
     * @param bo 查询条件
     * @return 学生信息列表
     */
    List<StudentInfoVo> queryList(StudentInfoBo bo);

    /**
     * 新增学生信息
     *
     * @param bo 学生信息
     * @return 是否新增成功
     */
    Boolean insertByBo(StudentInfoBo bo);

    /**
     * 修改学生信息
     *
     * @param bo 学生信息
     * @return 是否修改成功
     */
    Boolean updateByBo(StudentInfoBo bo);

    /**
     * 校验并批量删除学生信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
