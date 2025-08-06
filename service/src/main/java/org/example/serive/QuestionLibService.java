package org.example.serive;

import org.example.pojo.bo.QuestionLibBO;
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

}
