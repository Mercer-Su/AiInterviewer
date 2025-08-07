package org.example.serive;

import org.example.pojo.Interviewer;
import org.example.pojo.bo.InterviewerBO;

import java.util.List;

/**
 * @ClassName InterviewerService
 * @Version 1.0
 * @Description InterviewerService
 **/
public interface InterviewerService {

    /**
     * @Description: 新增或者修改面试官信息
     * @param interviewerBO
     */
    public void createOrUpdate(InterviewerBO interviewerBO);

}
