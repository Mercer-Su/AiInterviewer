package org.example.serive.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.mapper.CandidateMapper;
import org.example.mapper.CandidateMapperCustom;
import org.example.pojo.Candidate;
import org.example.pojo.bo.CandidateBO;
import org.example.pojo.vo.CandidateVO;
import org.example.serive.CandidateService;
import org.example.utils.PagedGridResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName CandidateServiceImpl
 * @Version 1.0
 * @Description CandidateServiceImpl
 **/
@Service
public class CandidateServiceImpl extends BaseInfoProperties implements CandidateService {

    @Resource
    private CandidateMapper candidateMapper;
    @Resource
    private CandidateMapperCustom candidateMapperCustom;

    @Override
    public void createOrUpdate(CandidateBO candidateBO) {

        Candidate candidate = new Candidate();
        BeanUtils.copyProperties(candidateBO, candidate);
        candidate.setUpdatedTime(LocalDateTime.now());

        if (StringUtils.isBlank(candidate.getId())) {
            candidate.setCreatedTime(LocalDateTime.now());
            candidateMapper.insert(candidate);
        } else {
            candidateMapper.updateById(candidate);
        }

    }

    @Override
    public PagedGridResult queryList(String realName, String mobile, Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);

        Map<String, Object> map = new HashMap<>();
        map.put("realName", realName);
        map.put("mobile", mobile);

        List<CandidateVO> list = candidateMapperCustom.queryCandidateList(map);

        return setterPagedGrid(list, page);
    }

    @Override
    public Candidate getDetail(String candidateId) {
        return candidateMapper.selectById(candidateId);
    }
    @Override
    public void delete(String candidateId) {
        candidateMapper.deleteById(candidateId);
    }

    @Override
    public Candidate queryMobileIsExist(String mobile) {
        return candidateMapper.selectOne(
                new QueryWrapper<Candidate>()
                        .eq("mobile", mobile)
        );
    }

}
