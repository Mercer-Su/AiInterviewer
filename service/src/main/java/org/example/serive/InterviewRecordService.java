package org.example.serive;

import org.example.pojo.InterviewRecord;
import org.example.utils.PagedGridResult;

public interface InterviewRecordService {

    /**
     * @Description: 保存面试结果
     * @param interviewRecord
     */
    public void save(InterviewRecord interviewRecord);

    /**
     * @Description: 判断候选人是否面试过
     * @param candidateId
     * @return boolean
     */
    public boolean isCandidateRecordExist(String candidateId);

    /**
     * @Description: 分页条件查询面试结果列表
     * @param realName
     * @param mobile
     * @param page
     * @param pageSize
     * @return PagedGridResult
     */
    public PagedGridResult queryList(String realName, String mobile, Integer page, Integer pageSize);
}
