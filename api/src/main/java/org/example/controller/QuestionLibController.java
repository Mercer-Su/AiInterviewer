package org.example.controller;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.enums.YesOrNo;
import org.example.grace.result.GraceJSONResult;
import org.example.pojo.bo.QuestionLibBO;
import org.example.serive.QuestionLibService;
import org.example.utils.PagedGridResult;
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
    /**
     * @Description: 分页查询面试题库列表
     * @param aiName
     * @param question
     * @param page
     * @param pageSize
     * @return GraceJSONResult
     */
    @GetMapping("list")
    public GraceJSONResult list(@RequestParam String aiName,
                                @RequestParam String question,
                                @RequestParam(defaultValue = "1", name = "page") Integer page,
                                @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {

        PagedGridResult result = questionLibService.queryList(aiName, question, page, pageSize);
        return GraceJSONResult.ok(result);
    }
    /**
     * @Description: 设置某个面试题显示（启用）
     * @param questionLibId
     * @return GraceJSONResult
     */
    @PostMapping("show")
    public GraceJSONResult show(@RequestParam String questionLibId) {
        if (StringUtils.isBlank(questionLibId)) return GraceJSONResult.error();
        questionLibService.setDisplayOrNot(questionLibId, YesOrNo.YES.type);
        return GraceJSONResult.ok();
    }
}
