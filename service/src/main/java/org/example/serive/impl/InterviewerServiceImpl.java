package org.example.serive.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.exception.GraceException;
import org.example.grace.result.ResponseStatusEnum;
import org.example.mapper.InterviewerMapper;
import org.example.pojo.Interviewer;
import org.example.pojo.bo.InterviewerBO;
import org.example.serive.InterviewerService;
import org.example.serive.JobService;
import org.example.serive.QuestionLibService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName InterviewerServiceImpl
 * @Version 1.0
 * @Description InterviewerServiceImpl
 **/
@Service
public class InterviewerServiceImpl implements InterviewerService {

    @Resource
    private InterviewerMapper interviewerMapper;


    @Override
    public void createOrUpdate(InterviewerBO interviewerBO) {

        Interviewer interviewer = new Interviewer();
        BeanUtils.copyProperties(interviewerBO, interviewer);
        interviewer.setUpdatedTime(LocalDateTime.now());
        //id 为空，则新增
        if (StringUtils.isBlank(interviewer.getId())) {
            interviewer.setCreateTime(LocalDateTime.now());
            interviewerMapper.insert(interviewer);
        } else {
            interviewerMapper.updateById(interviewer);
        }

    }



}
