package org.example.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.example.grace.result.GraceJSONResult;
import org.example.pojo.bo.InterviewerBO;
import org.example.serive.InterviewerService;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName InterviewerController
 * @Version 1.0
 * @Description InterviewerController
 **/
@RestController
@RequestMapping("interviewer")
public class InterviewerController {

    @Resource
    private InterviewerService interviewerService;

    /**
     * @Description: 创建或者更新数字人面试官的信息
     * @param interviewerBO
     * @return GraceJSONResult
     */
    @PostMapping("createOrUpdate")
    public GraceJSONResult createOrUpdate(@Valid @RequestBody InterviewerBO interviewerBO) {
        interviewerService.createOrUpdate(interviewerBO);
        return GraceJSONResult.ok();
    }

    /**
     * @Description: 查询所有数字人面试官列表
     * @param
     * @return GraceJSONResult
     */
    @GetMapping("list")
    public GraceJSONResult list() {
        return GraceJSONResult.ok(interviewerService.queryAll());
    }

    /**
     * @Description: 删除数字人面试官
     * @param interviewerId
     * @return GraceJSONResult
     */
    @DeleteMapping("delete")
    public GraceJSONResult delete(@RequestParam String interviewerId) {
        interviewerService.delete(interviewerId);
        return GraceJSONResult.ok();
    }
}
