package org.dromara.edu.service;

import org.dromara.edu.domain.vo.DocumentVo;
import org.dromara.edu.domain.bo.DocumentBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片Service接口
 *
 * @author Pyx
 * @date 2025-07-09
 */
public interface IDocumentService {

    /**
     * 分页查询图片列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 图片分页列表
     */
    TableDataInfo<DocumentVo> queryPageList(DocumentBo bo, PageQuery pageQuery);

    /**
     * 根据一组ID获取对应的图片列表
     *
     * @param DocumentIds 图片ID集合
     * @return 图片VO列表
     */
    List<DocumentVo> listByIds(Collection<Long> DocumentIds);

    /**
     * 根据ID获取图片详细信息
     *
     * @param DocumentId 主键
     * @return 图片VO
     */
    DocumentVo getById(Long DocumentId);

    /**
     * 上传MultipartFile图片文件
     *
     * @param file 上传文件
     * @return 图片VO
     */
    DocumentVo upload(MultipartFile file);

    /**
     * 上传File类型的图片文件
     *
     * @param file 上传文件
     * @return 图片VO
     */
    DocumentVo upload(File file);

    /**
     * 下载图片文件
     *
     * @param DocumentId 图片ID
     * @param response HTTP响应对象
     * @throws IOException 文件处理异常
     */
    void download(Long DocumentId, HttpServletResponse response) throws IOException;


    /**
     * 校验并批量删除图片
     *
     * @param ids     待删除ID集合
     * @param isValid 是否进行校验
     * @return 操作结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
