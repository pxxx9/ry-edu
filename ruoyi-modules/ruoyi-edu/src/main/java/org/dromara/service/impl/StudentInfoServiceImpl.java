package org.dromara.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.domain.vo.StudentInfoVo;
import org.springframework.stereotype.Service;
import org.dromara.domain.bo.StudentInfoBo;
import org.dromara.domain.StudentInfo;
import org.dromara.mapper.StudentInfoMapper;
import org.dromara.service.IStudentInfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 学生信息Service业务层处理
 *
 * @author Pyx
 * @date 2025-07-04
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class StudentInfoServiceImpl implements IStudentInfoService {

    private final StudentInfoMapper baseMapper;

    /**
     * 查询学生信息
     *
     * @param id 主键
     * @return 学生信息
     */
    @Override
    public StudentInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询学生信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 学生信息分页列表
     */
    @Override
    public TableDataInfo<StudentInfoVo> queryPageList(StudentInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<StudentInfo> lqw = buildQueryWrapper(bo);
        Page<StudentInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的学生信息列表
     *
     * @param bo 查询条件
     * @return 学生信息列表
     */
    @Override
    public List<StudentInfoVo> queryList(StudentInfoBo bo) {
        LambdaQueryWrapper<StudentInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<StudentInfo> buildQueryWrapper(StudentInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<StudentInfo> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(StudentInfo::getId);
        lqw.like(StringUtils.isNotBlank(bo.getName()), StudentInfo::getName, bo.getName());
        lqw.like(StringUtils.isNotBlank(bo.getUsername()), StudentInfo::getUsername, bo.getUsername());
        return lqw;
    }

    /**
     * 新增学生信息
     *
     * @param bo 学生信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(StudentInfoBo bo) {
        StudentInfo add = MapstructUtils.convert(bo, StudentInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改学生信息
     *
     * @param bo 学生信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(StudentInfoBo bo) {
        StudentInfo update = MapstructUtils.convert(bo, StudentInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(StudentInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除学生信息信息
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
