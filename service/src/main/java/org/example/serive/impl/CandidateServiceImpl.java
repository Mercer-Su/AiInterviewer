package org.example.serive.impl;


import jakarta.annotation.Resource;
import org.example.base.BaseInfoProperties;
import org.example.mapper.CandidateMapper;
import org.example.pojo.Candidate;
import org.example.serive.CandidateService;
import org.springframework.stereotype.Service;


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
    public Candidate getDetail(String candidateId) {
        return candidateMapper.selectById(candidateId);
    }

}
