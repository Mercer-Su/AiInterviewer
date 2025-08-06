package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.vo.InitQuestionsVO;
import org.example.pojo.vo.QuestionLibVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 面试题库表（每个数字人面试官都会对应一些面试题） Mapper 接口
 * </p>
 *
 */
public interface QuestionLibMapperCustom {

    public List<QuestionLibVO> queryQuestionLibList(@Param("paramMap") Map<String, Object> map);

    public InitQuestionsVO queryRandomQuestion(@Param("paramMap") Map<String, Object> map);

}
