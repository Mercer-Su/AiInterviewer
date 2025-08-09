package org.example.serive;

import org.example.pojo.InterviewRecord;
import org.example.utils.PagedGridResult;

public interface InterviewRecordService {

    /**
     * @Description: 保存面试结果
     * @param interviewRecord
     */
     void save(InterviewRecord interviewRecord);

    /**
     * @Description: 分页条件查询面试结果列表
     * @param realName
     * @param mobile
     * @param page
     * @param pageSize
     * @return PagedGridResult
     */
    PagedGridResult queryList(String realName, String mobile, Integer page, Integer pageSize);
}
