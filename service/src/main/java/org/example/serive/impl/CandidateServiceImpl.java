package org.example.serive.impl;


import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.mapper.CandidateMapper;
import org.example.pojo.Candidate;
import org.example.pojo.bo.CandidateBO;
import org.example.serive.CandidateService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * @ClassName CandidateServiceImpl
 * @Version 1.0
 * @Description CandidateServiceImpl
 **/
@Service
public class CandidateServiceImpl extends BaseInfoProperties implements CandidateService {

    @Resource
    private CandidateMapper candidateMapper;

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
    public Candidate getDetail(String candidateId) {
        return candidateMapper.selectById(candidateId);
    }

}
