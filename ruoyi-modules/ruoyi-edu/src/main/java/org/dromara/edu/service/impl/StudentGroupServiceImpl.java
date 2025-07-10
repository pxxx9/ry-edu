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
import org.dromara.edu.domain.vo.StudentGroupVo;
import org.dromara.edu.mapper.StudentInfoMapper;
import org.springframework.stereotype.Service;
import org.dromara.edu.domain.bo.StudentGroupBo;
import org.dromara.edu.domain.StudentGroup;
import org.dromara.edu.mapper.StudentGroupMapper;
import org.dromara.edu.service.IStudentGroupService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 用户组Service业务层处理
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class StudentGroupServiceImpl implements IStudentGroupService {

    private final StudentGroupMapper baseMapper;
    private final StudentInfoMapper studentMapper;

    /**
     * 查询用户组
     *
     * @param id 主键
     * @return 用户组
     */
    @Override
    public StudentGroupVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }



    /**
     * 分页查询用户组列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 用户组分页列表
     */
    @Override
    public TableDataInfo<StudentGroupVo> queryPageList(StudentGroupBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<StudentGroup> lqw = buildQueryWrapper(bo);
        Page<StudentGroupVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的用户组列表
     *
     * @param bo 查询条件
     * @return 用户组列表
     */
    @Override
    public List<StudentGroupVo> queryList(StudentGroupBo bo) {
        LambdaQueryWrapper<StudentGroup> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<StudentGroup> buildQueryWrapper(StudentGroupBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<StudentGroup> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(StudentGroup::getId);
        lqw.like(StringUtils.isNotBlank(bo.getGroupName()), StudentGroup::getGroupName, bo.getGroupName());
        return lqw;
    }

    /**
     * 新增用户组
     *
     * @param bo 用户组
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(StudentGroupBo bo) {
        StudentGroup add = MapstructUtils.convert(bo, StudentGroup.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改用户组
     *
     * @param bo 用户组
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(StudentGroupBo bo) {
        StudentGroup update = MapstructUtils.convert(bo, StudentGroup.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(StudentGroup entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除用户组信息
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
