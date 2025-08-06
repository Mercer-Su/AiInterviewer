package org.example.serive.impl;

import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.enums.YesOrNo;
import org.example.mapper.QuestionLibMapper;
import org.example.mapper.QuestionLibMapperCustom;
import org.example.pojo.QuestionLib;
import org.example.pojo.bo.QuestionLibBO;
import org.example.pojo.vo.QuestionLibVO;
import org.example.serive.QuestionLibService;
import org.example.utils.PagedGridResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName QuestionLibServiceImpl
 * @Version 1.0
 * @Description QuestionLibServiceImpl
 **/
@Service
public class QuestionLibServiceImpl extends BaseInfoProperties implements QuestionLibService {

    @Resource
    private QuestionLibMapper questionLibMapper;
    @Resource
    private QuestionLibMapperCustom questionLibMapperCustom;

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

    @Override
    public PagedGridResult queryList(String aiName, String question, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotBlank(aiName)) {
            map.put("aiName", aiName);
        }
        if (StringUtils.isNotBlank(question)) {
            map.put("question", question);
        }

        List<QuestionLibVO> list = questionLibMapperCustom.queryQuestionLibList(map);

        return setterPagedGrid(list, page);
    }

    @Override
    public void setDisplayOrNot(String questionLibId, Integer isOn) {

        QuestionLib questionLib = new QuestionLib();
        questionLib.setId(questionLibId);
        questionLib.setIsOn(isOn);
        questionLib.setUpdatedTime(LocalDateTime.now());

        questionLibMapper.updateById(questionLib);
    }
}


