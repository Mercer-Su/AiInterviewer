package org.example.serive.impl;

import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.example.base.BaseInfoProperties;
import org.example.mapper.InterviewRecordMapper;
import org.example.mapper.InterviewRecordMapperCustom;
import org.example.pojo.InterviewRecord;
import org.example.pojo.vo.InterviewRecordVO;
import org.example.serive.InterviewRecordService;
import org.example.utils.PagedGridResult;
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
    @Resource
    private InterviewRecordMapperCustom interviewRecordMapperCustom;
    @Override
    public void save(InterviewRecord interviewRecord) {
        interviewRecordMapper.insert(interviewRecord);
    }

    @Override
    public PagedGridResult queryList(String realName, String mobile, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        Map<String, Object> map = new HashMap<>();
        map.put("realName", realName);
        map.put("mobile", mobile);

        List<InterviewRecordVO> list = interviewRecordMapperCustom.queryList(map);
        return setterPagedGrid(list, page);
    }
}
