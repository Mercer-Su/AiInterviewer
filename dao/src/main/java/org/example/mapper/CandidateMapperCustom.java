package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.vo.CandidateVO;

import java.util.List;
import java.util.Map;

public interface CandidateMapperCustom {

    public List<CandidateVO> queryCandidateList(@Param("paramMap") Map<String, Object> map);

}
