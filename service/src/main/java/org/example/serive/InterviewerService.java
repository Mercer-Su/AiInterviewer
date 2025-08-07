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

    /**
     * @Description: 查询所有的面试官数据列表
     * @param
     * @return List<Interviewer>
     */
    public List<Interviewer> queryAll();

    /**
     * @Description: 删除数字人面试官
     * @param interviewerId
     */
    public void delete(String interviewerId);
}
