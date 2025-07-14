package org.dromara.edu.service;

import org.dromara.edu.domain.vo.CategoryVo;
import org.dromara.edu.domain.bo.CategoryBo;

import java.util.Collection;
import java.util.List;

/**
 * 通用分类Service接口
 *
 * @author Pyx
 * @date 2025-07-14
 */
public interface ICategoryService {

    /**
     * 查询通用分类
     *
     * @param categoryId 主键
     * @return 通用分类
     */
    CategoryVo queryById(Long categoryId);


    /**
     * 查询符合条件的通用分类列表
     *
     * @param bo 查询条件
     * @return 通用分类列表
     */
    List<CategoryVo> queryList(CategoryBo bo);

    /**
     * 新增通用分类
     *
     * @param bo 通用分类
     * @return 是否新增成功
     */
    Boolean insertByBo(CategoryBo bo);

    /**
     * 修改通用分类
     *
     * @param bo 通用分类
     * @return 是否修改成功
     */
    Boolean updateByBo(CategoryBo bo);

    /**
     * 校验并批量删除通用分类信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
