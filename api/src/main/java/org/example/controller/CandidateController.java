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

    /**
     * @Description: 条件搜索候选人的列表
     * @param realName
     * @param mobile
     * @param page
     * @param pageSize
     * @return GraceJSONResult
     */
    @GetMapping("list")
    public GraceJSONResult list(@RequestParam String realName,
                                @RequestParam String mobile,
                                @RequestParam(defaultValue = "1", name = "page") Integer page,
                                @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {
        PagedGridResult result = candidateService.queryList(realName, mobile, page, pageSize);
        return GraceJSONResult.ok(result);
    }

    /**
     * @Description: 查询候选人
     * @param candidateId
     * @return GraceJSONResult
     */
    @GetMapping("detail")
    public GraceJSONResult detail(@RequestParam String candidateId) {
        return GraceJSONResult.ok(candidateService.getDetail(candidateId));
    }

    /**
     * @Description: 删除候选人
     * @param candidateId
     * @return GraceJSONResult
     */
    @PostMapping("delete")
    public GraceJSONResult delete(@RequestParam String candidateId) {
        candidateService.delete(candidateId);
        return GraceJSONResult.ok();
    }
}
