package org.example.serive.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.mapper.JobMapper;
import org.example.mapper.JobMapperCustom;
import org.example.pojo.Job;
import org.example.pojo.bo.JobBO;
import org.example.pojo.vo.JobVO;
import org.example.serive.JobService;
import org.example.utils.PagedGridResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName JobServiceImpl
 * @Version 1.0
 * @Description JobServiceImpl
 **/
@Service
public class JobServiceImpl extends BaseInfoProperties implements JobService {

    @Resource
    private JobMapper jobMapper;

    @Resource
    private JobMapperCustom jobMapperCustom;

    @Override
    public void createOrUpdate(JobBO jobBO) {
        Job job = new Job();
        BeanUtils.copyProperties(jobBO, job);
        job.setCreateTime(LocalDateTime.now());

        if (StringUtils.isBlank(job.getId())) {
            job.setUpdatedTime(LocalDateTime.now());
            jobMapper.insert(job);
        } else {
            jobMapper.updateById(job);
        }
    }

    @Override
    public PagedGridResult queryList(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<JobVO> jobList = jobMapperCustom.queryJobList(null);
        return setterPagedGrid(jobList, page);
    }

    @Override
    public Job getDetail(String id) {
        return jobMapper.selectById(id);
    }

}
