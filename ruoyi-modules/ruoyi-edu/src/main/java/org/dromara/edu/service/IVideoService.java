package org.dromara.edu.service;

import org.dromara.edu.domain.vo.VideoVo;
import org.dromara.edu.domain.bo.VideoBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 视频Service接口
 *
 * @author Pyx
 * @date 2025-07-14
 */
public interface IVideoService {

    /**
     * 查询视频
     *
     * @param id 主键
     * @return 视频
     */
    VideoVo queryById(Long id);

    /**
     * 分页查询视频列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 视频分页列表
     */
    TableDataInfo<VideoVo> queryPageList(VideoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的视频列表
     *
     * @param bo 查询条件
     * @return 视频列表
     */
    List<VideoVo> queryList(VideoBo bo);

    /**
     * 新增视频
     *
     * @param bo 视频
     * @return 是否新增成功
     */
    Boolean insertByBo(VideoBo bo);

    /**
     * 修改视频
     *
     * @param bo 视频
     * @return 是否修改成功
     */
    Boolean updateByBo(VideoBo bo);

    /**
     * 校验并批量删除视频信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
