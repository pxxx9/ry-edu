package org.dromara.edu.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.edu.domain.OnlineCourse;
import org.dromara.edu.domain.bo.OnlineCourseBo;
import org.dromara.edu.domain.vo.OnlineCourseVo;
import org.dromara.edu.mapper.OnlineCourseMapper;
import org.dromara.edu.service.IOnlineCourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 线上课程Service业务层处理
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OnlineCourseServiceImpl implements IOnlineCourseService {

    private final OnlineCourseMapper baseMapper;

    /**
     * 查询线上课程
     *
     * @param id 主键
     * @return 线上课程
     */
    @Override
    public OnlineCourseVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询线上课程列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 线上课程分页列表
     */
    @Override
    public TableDataInfo<OnlineCourseVo> queryPageList(OnlineCourseBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OnlineCourse> lqw = buildQueryWrapper(bo);
        Page<OnlineCourseVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的线上课程列表
     *
     * @param bo 查询条件
     * @return 线上课程列表
     */
    @Override
    public List<OnlineCourseVo> queryList(OnlineCourseBo bo) {
        LambdaQueryWrapper<OnlineCourse> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OnlineCourse> buildQueryWrapper(OnlineCourseBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OnlineCourse> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(OnlineCourse::getId);
        lqw.like(StringUtils.isNotBlank(bo.getCourseName()), OnlineCourse::getCourseName, bo.getCourseName());
        lqw.eq(bo.getCategoryId() != null, OnlineCourse::getCategoryId, bo.getCategoryId());
        lqw.eq(StringUtils.isNotBlank(bo.getCourseType()), OnlineCourse::getCourseType, bo.getCourseType());
        lqw.eq(bo.getStudentCount() != null, OnlineCourse::getStudentCount, bo.getStudentCount());
        lqw.eq(bo.getTeacherId() != null, OnlineCourse::getTeacherId, bo.getTeacherId());
        return lqw;
    }

    /**
     * 新增线上课程
     *
     * @param bo 线上课程
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(OnlineCourseBo bo) {
        OnlineCourse add = MapstructUtils.convert(bo, OnlineCourse.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改线上课程
     *
     * @param bo 线上课程
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(OnlineCourseBo bo) {
        OnlineCourse update = MapstructUtils.convert(bo, OnlineCourse.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(OnlineCourse entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除线上课程信息
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
