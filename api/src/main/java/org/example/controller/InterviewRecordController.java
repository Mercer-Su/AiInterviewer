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


}
