package org.dromara.material.service;

import org.dromara.material.domain.vo.ImageVo;
import org.dromara.material.domain.bo.ImageBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 图片Service接口
 *
 * @author Pyx
 * @date 2025-07-08
 */
public interface IImageService {

    /**
     * 查询图片
     *
     * @param id 主键
     * @return 图片
     */
    ImageVo queryById(Long id);

    /**
     * 分页查询图片列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 图片分页列表
     */
    TableDataInfo<ImageVo> queryPageList(ImageBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的图片列表
     *
     * @param bo 查询条件
     * @return 图片列表
     */
    List<ImageVo> queryList(ImageBo bo);

    /**
     * 新增图片
     *
     * @param bo 图片
     * @return 是否新增成功
     */
    Boolean insertByBo(ImageBo bo);

    /**
     * 修改图片
     *
     * @param bo 图片
     * @return 是否修改成功
     */
    Boolean updateByBo(ImageBo bo);

    /**
     * 校验并批量删除图片信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
