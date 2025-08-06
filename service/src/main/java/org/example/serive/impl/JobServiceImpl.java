package org.example.serive.impl;

import jakarta.annotation.Resource;
import org.example.base.BaseInfoProperties;
import org.example.mapper.JobMapper;
import org.example.pojo.Job;
import org.example.serive.JobService;
import org.springframework.stereotype.Service;

/**
 * @ClassName JobServiceImpl
 * @Version 1.0
 * @Description JobServiceImpl
 **/
@Service
public class JobServiceImpl extends BaseInfoProperties implements JobService {
    @Resource
    private JobMapper jobMapper;
    @Override
    public Job getDetail(String id) {
        return jobMapper.selectById(id);
    }
}

