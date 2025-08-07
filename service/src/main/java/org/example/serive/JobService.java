package org.example.serive;

import org.example.pojo.Job;
import org.example.pojo.bo.JobBO;
import org.example.utils.PagedGridResult;

import java.util.HashMap;
import java.util.List;

public interface JobService {

    /**
     * @Description: 创建或者更新职位信息
     * @param jobBO
     */
    public void createOrUpdate(JobBO jobBO);

    /**
     * @Description: 分页查询职位列表
     * @param page
     * @param pageSize
     * @return PagedGridResult
     */
    public PagedGridResult queryList(Integer page, Integer pageSize);

    /**
     * @Description: 查询职位详情
     * @param id
     * @return Job
     */
    public Job getDetail(String id);

    /**
     * @Description: 删除职位详情
     * @param id
     */
    public void delete(String id);

    /**
     * @Description: 判断所有职位中是否包含某个面试官
     * @param InterviewerId
     * @return boolean
     */
    public boolean isJobContainInterviewer(String InterviewerId);

    /**
     * @Description: 获得所有岗位名称的列表
     * @param
     * @return List<HashMap<String,String>>
     */
    public List<HashMap<String, String>> nameList();
}
