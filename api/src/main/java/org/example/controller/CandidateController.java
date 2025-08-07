package org.example.controller;

import jakarta.annotation.Resource;
import org.example.grace.result.GraceJSONResult;
import org.example.pojo.bo.CandidateBO;
import org.example.serive.CandidateService;
import org.example.utils.PagedGridResult;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CandidateController
 * @Version 1.0
 * @Description CandidateController
 **/
@RestController
@RequestMapping("candidate")
public class CandidateController {

    @Resource
    private CandidateService candidateService;

    /**
     * @Description: 创建或者更新候选人的信息
     * @param candidateBO
     * @return GraceJSONResult
     */
    @PostMapping("createOrUpdate")
    public GraceJSONResult createOrUpdate(@RequestBody CandidateBO candidateBO) {
        candidateService.createOrUpdate(candidateBO);
        return GraceJSONResult.ok();
    }
    
}
