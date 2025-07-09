package org.dromara.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.domain.vo.DeptVo;
import org.springframework.stereotype.Service;
import org.dromara.domain.bo.DeptBo;
import org.dromara.domain.Dept;
import org.dromara.mapper.DeptMapper;
import org.dromara.service.IDeptService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 部门Service业务层处理
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DeptServiceImpl implements IDeptService {

    private final DeptMapper baseMapper;

    /**
     * 查询部门
     *
     * @param deptId 主键
     * @return 部门
     */
    @Override
    public DeptVo queryById(Long deptId){
        return baseMapper.selectVoById(deptId);
    }


    /**
     * 查询符合条件的部门列表
     *
     * @param bo 查询条件
     * @return 部门列表
     */
    @Override
    public List<DeptVo> queryList(DeptBo bo) {
        LambdaQueryWrapper<Dept> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Dept> buildQueryWrapper(DeptBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Dept> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(Dept::getDeptId);
        lqw.like(StringUtils.isNotBlank(bo.getDeptName()), Dept::getDeptName, bo.getDeptName());
        return lqw;
    }

    /**
     * 新增部门
     *
     * @param bo 部门
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DeptBo bo) {
        Dept add = MapstructUtils.convert(bo, Dept.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setDeptId(add.getDeptId());
        }
        return flag;
    }

    /**
     * 修改部门
     *
     * @param bo 部门
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DeptBo bo) {
        Dept update = MapstructUtils.convert(bo, Dept.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Dept entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除部门信息
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
