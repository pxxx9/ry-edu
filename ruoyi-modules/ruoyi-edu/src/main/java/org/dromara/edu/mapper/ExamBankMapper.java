package org.dromara.edu.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.dromara.edu.domain.ExamBank;
import org.dromara.edu.domain.vo.ExamBankVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 题库Mapper接口
 *
 * @author Pyx
 * @date 2025-07-10
 */
public interface ExamBankMapper extends BaseMapperPlus<ExamBank, ExamBankVo> {


    void incrementField(@Param("bankId") Long bankId, @Param("fieldName") String fieldName);

    void decrementField(@Param("bankId") Long bankId, @Param("fieldName") String fieldName,@Param("count") Long count);
}
