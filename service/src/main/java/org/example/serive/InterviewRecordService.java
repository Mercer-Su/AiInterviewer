package org.example.serive;

import org.example.pojo.InterviewRecord;

public interface InterviewRecordService {

    /**
     * @Description: 保存面试结果
     * @param interviewRecord
     */
    public void save(InterviewRecord interviewRecord);
}
