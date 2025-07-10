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
import org.dromara.edu.domain.Video;
import org.dromara.edu.domain.bo.VideoBo;
import org.dromara.edu.domain.vo.VideoVo;
import org.dromara.edu.mapper.VideoMapper;
import org.springframework.stereotype.Service;

import org.dromara.edu.service.IVideoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 视频Service业务层处理
 *
 * @author Pyx
 * @date 2025-07-07
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class VideoServiceImpl implements IVideoService {

    private final VideoMapper baseMapper;

    /**
     * 查询视频
     *
     * @param id 主键
     * @return 视频
     */
    @Override
    public VideoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询视频列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 视频分页列表
     */
    @Override
    public TableDataInfo<VideoVo> queryPageList(VideoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Video> lqw = buildQueryWrapper(bo);
        Page<VideoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的视频列表
     *
     * @param bo 查询条件
     * @return 视频列表
     */
    @Override
    public List<VideoVo> queryList(VideoBo bo) {
        LambdaQueryWrapper<Video> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Video> buildQueryWrapper(VideoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Video> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(Video::getId);
        lqw.like(StringUtils.isNotBlank(bo.getVideoName()), Video::getVideoName, bo.getVideoName());
        return lqw;
    }

    /**
     * 新增视频
     *
     * @param bo 视频
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(VideoBo bo) {
        Video add = MapstructUtils.convert(bo, Video.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改视频
     *
     * @param bo 视频
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(VideoBo bo) {
        Video update = MapstructUtils.convert(bo, Video.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Video entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除视频信息
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
