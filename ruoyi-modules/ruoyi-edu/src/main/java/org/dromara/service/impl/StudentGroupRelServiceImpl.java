package org.dromara.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.domain.vo.StudentGroupRelVo;
import org.springframework.stereotype.Service;
import org.dromara.domain.bo.StudentGroupRelBo;
import org.dromara.domain.StudentGroupRel;
import org.dromara.mapper.StudentGroupRelMapper;
import org.dromara.service.IStudentGroupRelService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 学生用户组关联Service业务层处理
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class StudentGroupRelServiceImpl implements IStudentGroupRelService {

    private final StudentGroupRelMapper baseMapper;

    /**
     * 查询学生用户组关联
     *
     * @param id 主键
     * @return 学生用户组关联
     */
    @Override
    public StudentGroupRelVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询学生用户组关联列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 学生用户组关联分页列表
     */
    @Override
    public TableDataInfo<StudentGroupRelVo> queryPageList(StudentGroupRelBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<StudentGroupRel> lqw = buildQueryWrapper(bo);
        // 添加group_id查询条件
        lqw.eq(bo.getGroupId() != null, StudentGroupRel::getGroupId, bo.getGroupId());
        Page<StudentGroupRelVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的学生用户组关联列表
     *
     * @param bo 查询条件
     * @return 学生用户组关联列表
     */
    @Override
    public List<StudentGroupRelVo> queryList(StudentGroupRelBo bo) {
        LambdaQueryWrapper<StudentGroupRel> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<StudentGroupRel> buildQueryWrapper(StudentGroupRelBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<StudentGroupRel> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(StudentGroupRel::getId);
        lqw.eq(bo.getStudentId() != null, StudentGroupRel::getStudentId, bo.getStudentId());
        return lqw;
    }

    /**
     * 新增学生用户组关联
     *
     * @param bo 学生用户组关联
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(StudentGroupRelBo bo) {
        StudentGroupRel add = MapstructUtils.convert(bo, StudentGroupRel.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改学生用户组关联
     *
     * @param bo 学生用户组关联
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(StudentGroupRelBo bo) {
        StudentGroupRel update = MapstructUtils.convert(bo, StudentGroupRel.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(StudentGroupRel entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除学生用户组关联信息
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
