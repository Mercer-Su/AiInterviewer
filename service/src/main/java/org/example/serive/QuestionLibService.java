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
}
