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
import org.dromara.edu.mapper.ExamBankMapper;
import org.springframework.stereotype.Service;
import org.dromara.edu.domain.bo.QuestionBo;
import org.dromara.edu.domain.vo.QuestionVo;
import org.dromara.edu.domain.Question;
import org.dromara.edu.mapper.QuestionMapper;
import org.dromara.edu.service.IQuestionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

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

    private final ExamBankMapper examBankMapper;

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
        lqw.like(StringUtils.isNotBlank(bo.getQuestionContent()), Question::getQuestionContent, bo.getQuestionContent());
        lqw.eq(bo.getBankId() != null, Question::getBankId, bo.getBankId());
        lqw.eq(bo.getQuestionType() != null, Question::getQuestionType, bo.getQuestionType());
        lqw.eq(bo.getDifficulty() != null, Question::getDifficulty, bo.getDifficulty());
        return lqw;
    }

    /**
     * 新增题库
     *
     * @param bo 题目
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(QuestionBo bo) {
        Question add = MapstructUtils.convert(bo, Question.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
            // 插入成功后，更新题库的题型数量字段
            Long bankId = add.getBankId();
            Long type = add.getQuestionType();

            if (bankId != null && type != null) {
                String fieldName = switch (type.intValue()) {
                    case 1 -> "single_choice_count";
                    case 2 -> "multiple_choice_count";
                    case 3 -> "fill_blank_count";
                    case 4 -> "judge_count";
                    case 5 -> "answer_count";
                    case 6 -> "case_count";
                    default -> null;
                };

                if (fieldName != null) {
                    examBankMapper.incrementField(bankId, fieldName);
                }
            }
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
        if (isValid) {
            // TODO: 做一些校验逻辑（如是否允许删除）
        }

        // 1. 查询题目信息
        List<Question> questionList = baseMapper.selectBatchIds(ids);

        // 2. 分组统计要减少的数量：Map<bankId_type, count>
        Map<String, Long> reductionMap = questionList.stream()
            .collect(Collectors.groupingBy(
                q -> q.getBankId() + "_" + q.getQuestionType(),
                Collectors.counting()
            ));

        // 3. 遍历每组，更新题库字段（-数量）
        for (Map.Entry<String, Long> entry : reductionMap.entrySet()) {
            String[] keyParts = entry.getKey().split("_");
            Long bankId = Long.valueOf(keyParts[0]);
            int questionType = Integer.parseInt(keyParts[1]);
            Long count = entry.getValue();

            String fieldName = switch (questionType) {
                case 1 -> "single_choice_count";
                case 2 -> "multiple_choice_count";
                case 3 -> "fill_blank_count";
                case 4 -> "judge_count";
                case 5 -> "answer_count";
                case 6 -> "case_count";
                default -> null;
            };

            if (fieldName != null) {
                examBankMapper.decrementField(bankId, fieldName, count);
            }
        }

        // 4. 执行逻辑删除
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
