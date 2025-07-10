package org.dromara.edu.service;

import org.dromara.edu.domain.vo.OfflineCourseVo;
import org.dromara.edu.domain.bo.OfflineCourseBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 线下课程Service接口
 *
 * @author Pyx
 * @date 2025-07-03
 */
public interface IOfflineCourseService {

    /**
     * 查询线下课程
     *
     * @param id 主键
     * @return 线下课程
     */
    OfflineCourseVo queryById(Long id);

    /**
     * 分页查询线下课程列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 线下课程分页列表
     */
    TableDataInfo<OfflineCourseVo> queryPageList(OfflineCourseBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的线下课程列表
     *
     * @param bo 查询条件
     * @return 线下课程列表
     */
    List<OfflineCourseVo> queryList(OfflineCourseBo bo);

    /**
     * 新增线下课程
     *
     * @param bo 线下课程
     * @return 是否新增成功
     */
    Boolean insertByBo(OfflineCourseBo bo);

    /**
     * 修改线下课程
     *
     * @param bo 线下课程
     * @return 是否修改成功
     */
    Boolean updateByBo(OfflineCourseBo bo);

    /**
     * 校验并批量删除线下课程信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
