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
import org.springframework.stereotype.Service;
import org.dromara.domain.bo.ImageBo;
import org.dromara.domain.vo.ImageVo;
import org.dromara.domain.Image;
import org.dromara.mapper.ImageMapper;
import org.dromara.service.IImageService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 图片Service业务层处理
 *
 * @author Pyx
 * @date 2025-07-08
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements IImageService {

    private final ImageMapper baseMapper;

    /**
     * 查询图片
     *
     * @param id 主键
     * @return 图片
     */
    @Override
    public ImageVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询图片列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 图片分页列表
     */
    @Override
    public TableDataInfo<ImageVo> queryPageList(ImageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Image> lqw = buildQueryWrapper(bo);
        Page<ImageVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的图片列表
     *
     * @param bo 查询条件
     * @return 图片列表
     */
    @Override
    public List<ImageVo> queryList(ImageBo bo) {
        LambdaQueryWrapper<Image> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Image> buildQueryWrapper(ImageBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Image> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(Image::getId);
        lqw.like(StringUtils.isNotBlank(bo.getImageName()), Image::getImageName, bo.getImageName());
        lqw.eq(StringUtils.isNotBlank(bo.getImagePath()), Image::getImagePath, bo.getImagePath());
        return lqw;
    }

    /**
     * 新增图片
     *
     * @param bo 图片
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImageBo bo) {
        Image add = MapstructUtils.convert(bo, Image.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改图片
     *
     * @param bo 图片
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImageBo bo) {
        Image update = MapstructUtils.convert(bo, Image.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Image entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除图片信息
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
