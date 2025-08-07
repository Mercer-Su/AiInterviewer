package org.example.controller;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.grace.result.GraceJSONResult;
import org.example.grace.result.ResponseStatusEnum;
import org.example.pojo.Candidate;
import org.example.pojo.bo.VerifySMSBO;
import org.example.pojo.vo.CandidateVO;
import org.example.serive.CandidateService;
import org.example.utils.JsonUtils;
import org.example.utils.SMSUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @ClassName WelcomeController
 * @Version 1.0
 * @Description WelcomeController
 **/
@RestController
@RequestMapping("welcome")
public class WelcomeController extends BaseInfoProperties {

    @Resource
    private SMSUtils smsUtils;

    @Resource
    private CandidateService candidateService;

    /**
     * @Description: 获得短信验证码
     * @param mobile
     * @return GraceJSONResult
     */
    @PostMapping("getSMSCode")
    public GraceJSONResult getSMSCode(String mobile) throws Exception {

        if (StringUtils.isBlank(mobile)) return GraceJSONResult.error();

        String code = (int)((Math.random() * 9 + 1) * 100000) + "";
        System.out.println(code);
//        smsUtils.sendSMS(mobile, code);

        // 把验证码存入到redis中，用于后续进入面试的校验
        redis.set(MOBILE_SMSCODE + ":" + mobile, code, 10 * 60);

        return GraceJSONResult.ok();
    }


}
