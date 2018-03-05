package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.model.mongo.Question;
import cn.llf.framework.services.exam.dto.ChoiceQuestionConfigurationItemDto;
import cn.llf.framework.services.exam.dto.ExamObjectDto;
import cn.llf.framework.services.exam.south.QuestionManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Author: calvin
 * @Since: 2018/3/5 20:48
 * @Description: 测试mongo数据库
 */
@Controller("question")
@RequestMapping("question")
public class QuestionAction {

    @Autowired
    QuestionManagerService questionManagerService;

    /**
     * 添加试题
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addQuestion",method = RequestMethod.GET)
    public boolean addQuestion(){
        Question mongoDto = new Question();
        mongoDto.setId(UUID.randomUUID().toString());
        mongoDto.setTopic("英雄联盟的最新比赛模式");
        mongoDto.setCreateTime(new Date().toString());
        mongoDto.setCreateUserId(UUID.randomUUID().toString());

        ChoiceQuestionConfigurationItemDto choiceQuestionConfigurationItemDto = new ChoiceQuestionConfigurationItemDto();
        choiceQuestionConfigurationItemDto.setId("1");
        choiceQuestionConfigurationItemDto.setContent("五军对决");

        ChoiceQuestionConfigurationItemDto second = new ChoiceQuestionConfigurationItemDto();
        second.setId("2");
        second.setContent("匹配赛");
        List<ChoiceQuestionConfigurationItemDto> configurationItems = new ArrayList<>();
        configurationItems.add(choiceQuestionConfigurationItemDto);
        configurationItems.add(second);
        mongoDto.setConfigurationItems(configurationItems);

        ExamObjectDto examObjectDto = new ExamObjectDto("course","questionType");
        mongoDto.setExamObjects(Arrays.asList(examObjectDto));


        questionManagerService.add(mongoDto);
        return true;

    }
}
