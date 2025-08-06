package org.example.controller;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.grace.result.GraceJSONResult;
import org.example.pojo.bo.QuestionLibBO;
import org.example.serive.QuestionLibService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questionLib")
public class QuestionLibController extends BaseInfoProperties {

    @Resource
    private QuestionLibService questionLibService;

    /**
     * 创建面试题
     * @param questionLibBO
     * @return
     */
    @PostMapping("createOrUpdate")
    public GraceJSONResult createOrUpdate(@RequestBody QuestionLibBO questionLibBO) {
        questionLibService.createOrUpdate(questionLibBO);
        return GraceJSONResult.ok();
    }
}
