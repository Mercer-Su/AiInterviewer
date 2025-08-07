package org.example.serive;

import org.example.pojo.bo.JobBO;

public interface JobService {

    /**
     * @Description: 创建或者更新职位信息
     * @param jobBO
     */
    public void createOrUpdate(JobBO jobBO);

}
