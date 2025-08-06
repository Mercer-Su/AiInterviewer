package org.example.controller;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.enums.YesOrNo;
import org.example.grace.result.GraceJSONResult;
import org.example.grace.result.ResponseStatusEnum;
import org.example.pojo.bo.QuestionLibBO;
import org.example.pojo.vo.InitQuestionsVO;
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

    /**
     * @Description: 设置某个面试题隐藏（禁用）
     * @param questionLibId
     * @return GraceJSONResult
     */
    @PostMapping("hide")
    public GraceJSONResult hide(@RequestParam String questionLibId) {
        if (StringUtils.isBlank(questionLibId)) return GraceJSONResult.error();
        questionLibService.setDisplayOrNot(questionLibId, YesOrNo.NO.type);
        return GraceJSONResult.ok();
    }

    /**
     * @Description: 删除指定的面试题
     * @param questionLibId
     * @return GraceJSONResult
     */
    @PostMapping("delete")
    public GraceJSONResult delete(@RequestParam String questionLibId) {
        if (StringUtils.isBlank(questionLibId)) return GraceJSONResult.error();
        questionLibService.delete(questionLibId);
        return GraceJSONResult.ok();
    }

    /**
     * @Description: 准备面试题，随机获得一定数量的面试题返回给前端
     * @param candidateId
     * @return GraceJSONResult
     */
    @GetMapping("prepareQuestion")
    public GraceJSONResult prepareQuestion(@RequestParam String candidateId) {

        // 判断应聘者候选人是否在会话中，限制接口被恶意调用
        String candidateInfo = redis.get(REDIS_USER_INFO + ":" + candidateId);
        String userToken = redis.get(REDIS_USER_TOKEN + ":" + candidateId);
        if (StringUtils.isBlank(candidateInfo) || StringUtils.isBlank(userToken)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.USER_INFO_NOT_EXIST_ERROR);
        }

        List<InitQuestionsVO> result = questionLibService.getRandomQuestions(candidateId, 2);
        return GraceJSONResult.ok(result);
    }

}
