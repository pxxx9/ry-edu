package org.dromara.edu.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.dromara.edu.domain.bo.CategoryBo;
import org.dromara.edu.domain.vo.CategoryVo;
import org.dromara.edu.domain.Category;
import org.dromara.edu.mapper.CategoryMapper;
import org.dromara.edu.service.ICategoryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 通用分类Service业务层处理
 *
 * @author Pyx
 * @date 2025-07-14
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryMapper baseMapper;

    /**
     * 查询通用分类
     *
     * @param categoryId 主键
     * @return 通用分类
     */
    @Override
    public CategoryVo queryById(Long categoryId){
        return baseMapper.selectVoById(categoryId);
    }


    /**
     * 查询符合条件的通用分类列表
     *
     * @param bo 查询条件
     * @return 通用分类列表
     */
    @Override
    public List<CategoryVo> queryList(CategoryBo bo) {
        LambdaQueryWrapper<Category> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Category> buildQueryWrapper(CategoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Category> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(Category::getCategoryId);
        lqw.like(StringUtils.isNotBlank(bo.getCategoryName()), Category::getCategoryName, bo.getCategoryName());
        return lqw;
    }

    /**
     * 新增通用分类
     *
     * @param bo 通用分类
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(CategoryBo bo) {
        Category add = MapstructUtils.convert(bo, Category.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCategoryId(add.getCategoryId());
        }
        return flag;
    }

    /**
     * 修改通用分类
     *
     * @param bo 通用分类
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(CategoryBo bo) {
        Category update = MapstructUtils.convert(bo, Category.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Category entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除通用分类信息
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
