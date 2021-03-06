package cn.llf.framework.services.exam.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: calvin
 * @Since: 2018/3/5 20:57
 * @description:
 */
@Data
public class RadioQuestionDto extends QuestionBaseDto{
    /**选择项**/
    private List<ChoiceQuestionConfigurationItemDto> configurationItems;
    /**
     * 正确答案 选择项id
     * */
    private String correctAnswer;
}
