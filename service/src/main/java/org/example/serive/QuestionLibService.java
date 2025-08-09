package org.example.serive;

import org.example.pojo.bo.QuestionLibBO;
import org.example.pojo.vo.InitQuestionsVO;
import org.example.utils.PagedGridResult;

import java.util.List;

public interface QuestionLibService {

    /**
     * @Description: 创建或者更新题库
     * @param questionLibBO
     */
    public void createOrUpdate(QuestionLibBO questionLibBO);

    /**
     * @Description: 分页查询题库列表
     * @param aiName
     * @param question
     * @param page
     * @param pageSize
     * @return PagedGridResult
     */
    public PagedGridResult queryList(String aiName, String question, Integer page, Integer pageSize);

    /**
     * @Description: 启用或者禁用某个面试题
     * @param questionLibId
     * @param isOn
     */
    public void setDisplayOrNot(String questionLibId, Integer isOn);

    /**
     * @Description: 删除面试题
     * @param questionLibId
     */
    public void delete(String questionLibId);

    /**
     * @Description: 判断所有面试题库中是否包含某个面试官
     * @param InterviewerId
     * @return boolean
     */
    public boolean isQuestionLibContainInterviewer(String InterviewerId);

    /**
     * @Description: 获得指定数量的随机面试题
     * @param candidateId
     * @param questionNum
     * @return List<InitQuestionsVO>
     */
    public List<InitQuestionsVO> getRandomQuestions(String candidateId, Integer questionNum);
}
