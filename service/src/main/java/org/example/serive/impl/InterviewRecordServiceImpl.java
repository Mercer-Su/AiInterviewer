package org.example.serive.impl;

import jakarta.annotation.Resource;
import org.example.base.BaseInfoProperties;
import org.example.mapper.InterviewRecordMapper;
import org.example.pojo.InterviewRecord;
import org.example.serive.InterviewRecordService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName InterviewRecordServiceImpl
 * @Version 1.0
 * @Description InterviewRecordServiceImpl
 **/
@Service
public class InterviewRecordServiceImpl extends BaseInfoProperties implements InterviewRecordService {

    @Resource
    private InterviewRecordMapper interviewRecordMapper;
    @Override
    public void save(InterviewRecord interviewRecord) {
        interviewRecordMapper.insert(interviewRecord);
    }
}
