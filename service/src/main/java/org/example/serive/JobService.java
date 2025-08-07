package org.example.serive;

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


}
