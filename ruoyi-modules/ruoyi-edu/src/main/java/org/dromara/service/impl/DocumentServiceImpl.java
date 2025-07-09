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
import org.dromara.domain.bo.DocumentBo;
import org.dromara.domain.vo.DocumentVo;
import org.dromara.domain.Document;
import org.dromara.mapper.DocumentMapper;
import org.dromara.service.IDocumentService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 文档Service业务层处理
 *
 * @author Pyx
 * @date 2025-07-08
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DocumentServiceImpl implements IDocumentService {

    private final DocumentMapper baseMapper;

    /**
     * 查询文档
     *
     * @param id 主键
     * @return 文档
     */
    @Override
    public DocumentVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询文档列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 文档分页列表
     */
    @Override
    public TableDataInfo<DocumentVo> queryPageList(DocumentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Document> lqw = buildQueryWrapper(bo);
        Page<DocumentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的文档列表
     *
     * @param bo 查询条件
     * @return 文档列表
     */
    @Override
    public List<DocumentVo> queryList(DocumentBo bo) {
        LambdaQueryWrapper<Document> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Document> buildQueryWrapper(DocumentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Document> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(Document::getId);
        lqw.like(StringUtils.isNotBlank(bo.getDocumentName()), Document::getDocumentName, bo.getDocumentName());
        return lqw;
    }

    /**
     * 新增文档
     *
     * @param bo 文档
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DocumentBo bo) {
        Document add = MapstructUtils.convert(bo, Document.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改文档
     *
     * @param bo 文档
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DocumentBo bo) {
        Document update = MapstructUtils.convert(bo, Document.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Document entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除文档信息
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
