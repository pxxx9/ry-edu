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
import org.dromara.edu.domain.Teacher;
import org.dromara.edu.domain.bo.TeacherBo;
import org.dromara.edu.domain.vo.TeacherVo;
import org.dromara.edu.mapper.TeacherMapper;
import org.dromara.edu.service.ITeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 讲师Service业务层处理
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements ITeacherService {

    private final TeacherMapper baseMapper;

    /**
     * 查询讲师
     *
     * @param id 主键
     * @return 讲师
     */
    @Override
    public TeacherVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询讲师列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 讲师分页列表
     */
    @Override
    public TableDataInfo<TeacherVo> queryPageList(TeacherBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Teacher> lqw = buildQueryWrapper(bo);
        Page<TeacherVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的讲师列表
     *
     * @param bo 查询条件
     * @return 讲师列表
     */
    @Override
    public List<TeacherVo> queryList(TeacherBo bo) {
        LambdaQueryWrapper<Teacher> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Teacher> buildQueryWrapper(TeacherBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Teacher> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(Teacher::getId);
        lqw.like(StringUtils.isNotBlank(bo.getName()), Teacher::getName, bo.getName());
        return lqw;
    }

    /**
     * 新增讲师
     *
     * @param bo 讲师
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TeacherBo bo) {
        Teacher add = MapstructUtils.convert(bo, Teacher.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改讲师
     *
     * @param bo 讲师
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TeacherBo bo) {
        Teacher update = MapstructUtils.convert(bo, Teacher.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Teacher entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除讲师信息
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
