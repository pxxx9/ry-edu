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
import org.dromara.edu.service.IPaperService;
import org.springframework.stereotype.Service;
import org.dromara.edu.domain.bo.PaperBo;
import org.dromara.edu.domain.vo.PaperVo;
import org.dromara.edu.domain.Paper;
import org.dromara.edu.mapper.PaperMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 试卷Service业务层处理
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PaperServiceImpl implements IPaperService {

    private final PaperMapper baseMapper;

    /**
     * 查询试卷
     *
     * @param id 主键
     * @return 试卷
     */
    @Override
    public PaperVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询试卷列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 试卷分页列表
     */
    @Override
    public TableDataInfo<PaperVo> queryPageList(PaperBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Paper> lqw = buildQueryWrapper(bo);
        Page<PaperVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的试卷列表
     *
     * @param bo 查询条件
     * @return 试卷列表
     */
    @Override
    public List<PaperVo> queryList(PaperBo bo) {
        LambdaQueryWrapper<Paper> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Paper> buildQueryWrapper(PaperBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Paper> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(Paper::getId);
        lqw.like(StringUtils.isNotBlank(bo.getName()), Paper::getName, bo.getName());
        return lqw;
    }

    /**
     * 新增试卷
     *
     * @param bo 试卷
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(PaperBo bo) {
        Paper add = MapstructUtils.convert(bo, Paper.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改试卷
     *
     * @param bo 试卷
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(PaperBo bo) {
        Paper update = MapstructUtils.convert(bo, Paper.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Paper entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除试卷信息
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
