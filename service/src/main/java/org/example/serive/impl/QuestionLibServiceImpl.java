package org.example.serive.impl;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.enums.YesOrNo;
import org.example.mapper.QuestionLibMapper;
import org.example.pojo.QuestionLib;
import org.example.pojo.bo.QuestionLibBO;
import org.example.serive.QuestionLibService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * @ClassName QuestionLibServiceImpl
 * @Version 1.0
 * @Description QuestionLibServiceImpl
 **/
@Service
public class QuestionLibServiceImpl extends BaseInfoProperties implements QuestionLibService {

    @Resource
    private QuestionLibMapper questionLibMapper;
    @Override
    public void createOrUpdate(QuestionLibBO questionLibBO) {
        QuestionLib questionLib = new QuestionLib();
        BeanUtils.copyProperties(questionLibBO, questionLib);
        questionLib.setUpdatedTime(LocalDateTime.now());

        if (StringUtils.isBlank(questionLib.getId())) {
            questionLib.setIsOn(YesOrNo.YES.type);
            questionLib.setCreateTime(LocalDateTime.now());
            questionLibMapper.insert(questionLib);
        } else {
            questionLibMapper.updateById(questionLib);
        }
    }
}


