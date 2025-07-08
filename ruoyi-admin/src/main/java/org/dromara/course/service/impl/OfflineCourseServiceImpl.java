package org.dromara.course.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.dromara.course.domain.bo.OfflineCourseBo;
import org.dromara.course.domain.vo.OfflineCourseVo;
import org.dromara.course.domain.OfflineCourse;
import org.dromara.course.mapper.OfflineCourseMapper;
import org.dromara.course.service.IOfflineCourseService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 线下课程Service业务层处理
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OfflineCourseServiceImpl implements IOfflineCourseService {

    private final OfflineCourseMapper baseMapper;

    /**
     * 查询线下课程
     *
     * @param id 主键
     * @return 线下课程
     */
    @Override
    public OfflineCourseVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询线下课程列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 线下课程分页列表
     */
    @Override
    public TableDataInfo<OfflineCourseVo> queryPageList(OfflineCourseBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OfflineCourse> lqw = buildQueryWrapper(bo);
        Page<OfflineCourseVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的线下课程列表
     *
     * @param bo 查询条件
     * @return 线下课程列表
     */
    @Override
    public List<OfflineCourseVo> queryList(OfflineCourseBo bo) {
        LambdaQueryWrapper<OfflineCourse> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OfflineCourse> buildQueryWrapper(OfflineCourseBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OfflineCourse> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(OfflineCourse::getId);
        lqw.like(StringUtils.isNotBlank(bo.getCourseName()), OfflineCourse::getCourseName, bo.getCourseName());
        return lqw;
    }

    /**
     * 新增线下课程
     *
     * @param bo 线下课程
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(OfflineCourseBo bo) {
        OfflineCourse add = MapstructUtils.convert(bo, OfflineCourse.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改线下课程
     *
     * @param bo 线下课程
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(OfflineCourseBo bo) {
        OfflineCourse update = MapstructUtils.convert(bo, OfflineCourse.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(OfflineCourse entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除线下课程信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
