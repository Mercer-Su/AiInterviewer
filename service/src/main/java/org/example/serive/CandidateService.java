package org.example.serive;

import org.example.pojo.Candidate;
import org.example.pojo.bo.CandidateBO;

public interface CandidateService {
    /**
     * @Description: 创建或者更新候选人（应聘者）的信息
     * @param candidateBO
     */
    public void createOrUpdate(CandidateBO candidateBO);

    /**
     * @Description: 获得候选人信息详情
     * @param candidateId
     */
    public Candidate getDetail(String candidateId);
}
