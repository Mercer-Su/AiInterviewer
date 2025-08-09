package org.example.controller;

import jakarta.annotation.Resource;
import org.example.ChatGLMTask;
import org.example.grace.result.GraceJSONResult;
import org.example.pojo.bo.SubmitAnswerBO;
import org.example.serive.InterviewRecordService;
import org.example.utils.PagedGridResult;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName InterviewRecordController
 * @Version 1.0
 * @Description InterviewRecordController
 **/
@RestController
@RequestMapping("interviewRecord")
public class InterviewRecordController {

    @Resource
    private ChatGLMTask chatGLMTask;

    @Resource
    private InterviewRecordService interviewRecordService;

    /**
     * @Description: 提交面试的回答内容进行AI分析
     * @param submitAnswerBO
     * @return GraceJSONResult
     */
    @PostMapping("collect")
    public GraceJSONResult collect(@RequestBody SubmitAnswerBO submitAnswerBO) {

        chatGLMTask.display(submitAnswerBO);

        return GraceJSONResult.ok();
    }

    /**
     * @Description: 分页查询条件搜索面试记录列表
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
        PagedGridResult result = interviewRecordService.queryList(realName, mobile, page, pageSize);
        return GraceJSONResult.ok(result);
    }

}
