package org.example.serive;

import org.example.pojo.Candidate;

public interface CandidateService {
    /**
     * @Description: 获得候选人信息详情
     * @param candidateId
     */
    public Candidate getDetail(String candidateId);
}
