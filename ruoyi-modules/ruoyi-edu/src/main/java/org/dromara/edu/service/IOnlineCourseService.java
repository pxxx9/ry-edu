package org.dromara.edu.service;


import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.edu.domain.bo.OnlineCourseBo;
import org.dromara.edu.domain.vo.OnlineCourseVo;

import java.util.Collection;
import java.util.List;

/**
 * 线上课程Service接口
 *
 * @author Pyx
 * @date 2025-07-03
 */
public interface IOnlineCourseService {

    /**
     * 查询线上课程
     *
     * @param id 主键
     * @return 线上课程
     */
    OnlineCourseVo queryById(Long id);

    /**
     * 分页查询线上课程列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 线上课程分页列表
     */
    TableDataInfo<OnlineCourseVo> queryPageList(OnlineCourseBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的线上课程列表
     *
     * @param bo 查询条件
     * @return 线上课程列表
     */
    List<OnlineCourseVo> queryList(OnlineCourseBo bo);

    /**
     * 新增线上课程
     *
     * @param bo 线上课程
     * @return 是否新增成功
     */
    Boolean insertByBo(OnlineCourseBo bo);

    /**
     * 修改线上课程
     *
     * @param bo 线上课程
     * @return 是否修改成功
     */
    Boolean updateByBo(OnlineCourseBo bo);

    /**
     * 校验并批量删除线上课程信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
