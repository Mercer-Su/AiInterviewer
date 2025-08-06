package org.example.serive;

import org.example.pojo.Job;

public interface JobService {
    /**
     * @Description: 查询职位详情
     * @param id
     * @return Job
     */
    public Job getDetail(String id);

}
