package org.example.serive;

import org.example.pojo.bo.QuestionLibBO;
import org.example.utils.PagedGridResult;

public interface QuestionLibService {
    /**
     * @param questionLibBO
     * @Description: 创建或者更新题库
     */
    public void createOrUpdate(QuestionLibBO questionLibBO);

    /**
     * @param aiName
     * @param question
     * @param page
     * @param pageSize
     * @return PagedGridResult
     * @Description: 分页查询题库列表
     */
    public PagedGridResult queryList(String aiName, String question, Integer page, Integer pageSize);

    /**
     * @param questionLibId
     * @param isOn
     * @Description: 启用或者禁用某个面试题
     */
    public void setDisplayOrNot(String questionLibId, Integer isOn);

    /**
     * @param questionLibId
     * @Description: 删除面试题
     */
    public void delete(String questionLibId);
}
