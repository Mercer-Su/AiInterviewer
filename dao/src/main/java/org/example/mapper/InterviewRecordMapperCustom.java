package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.vo.InterviewRecordVO;

import java.util.List;
import java.util.Map;

public interface InterviewRecordMapperCustom {

    public List<InterviewRecordVO> queryList(@Param("paramMap") Map<String, Object> map);

}
