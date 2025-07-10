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
import org.springframework.stereotype.Service;
import org.dromara.edu.domain.bo.QuestionBo;
import org.dromara.edu.domain.vo.QuestionVo;
import org.dromara.edu.domain.Question;
import org.dromara.edu.mapper.QuestionMapper;
import org.dromara.edu.service.IQuestionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 题库Service业务层处理
 *
 * @author Pyx
 * @date 2025-07-03
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements IQuestionService {

    private final QuestionMapper baseMapper;

    /**
     * 查询题库
     *
     * @param id 主键
     * @return 题库
     */
    @Override
    public QuestionVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询题库列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 题库分页列表
     */
    @Override
    public TableDataInfo<QuestionVo> queryPageList(QuestionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Question> lqw = buildQueryWrapper(bo);
        Page<QuestionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的题库列表
     *
     * @param bo 查询条件
     * @return 题库列表
     */
    @Override
    public List<QuestionVo> queryList(QuestionBo bo) {
        LambdaQueryWrapper<Question> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Question> buildQueryWrapper(QuestionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Question> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(Question::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getQuestionContent()), Question::getQuestionContent, bo.getQuestionContent());
        return lqw;
    }

    /**
     * 新增题库
     *
     * @param bo 题库
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(QuestionBo bo) {
        Question add = MapstructUtils.convert(bo, Question.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改题库
     *
     * @param bo 题库
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(QuestionBo bo) {
        Question update = MapstructUtils.convert(bo, Question.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Question entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除题库信息
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
