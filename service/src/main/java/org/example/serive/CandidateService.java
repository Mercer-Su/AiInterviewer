package org.example.serive;

import org.example.pojo.Candidate;
import org.example.pojo.bo.CandidateBO;
import org.example.utils.PagedGridResult;

public interface CandidateService {

    /**
     * @Description: 创建或者更新候选人（应聘者）的信息
     * @param candidateBO
     */
    public void createOrUpdate(CandidateBO candidateBO);

    /**
     * @Description: 查询候选人（应聘者）列表
     * @param realName
     * @param mobile
     * @param page
     * @param pageSize
     * @return PagedGridResult
     */
    public PagedGridResult queryList(String realName, String mobile, Integer page, Integer pageSize);

    /**
     * @Description: 获得候选人信息详情
     * @param candidateId
     */
    public Candidate getDetail(String candidateId);

    /**
     * @Description: 删除候选人
     * @param candidateId
     */
    public void delete(String candidateId);

    /**
     * @Description: 判断用户是否存在，如果存在返回用户信息，如果不存在返回null
     * @param mobile
     * @return Candidate
     */
    public Candidate queryMobileIsExist(String mobile);
}
