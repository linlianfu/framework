package cn.llf.framework.model.mongo;

import cn.llf.framework.services.exam.dto.ChoiceQuestionConfigurationItemDto;
import cn.llf.framework.services.exam.dto.QuestionBaseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: calvin
 * @Since: 2018/3/5 20:50
 * @Description:
 */
@Data
public class Question extends QuestionBaseDto implements Serializable {
    /**选择项**/
    private List<ChoiceQuestionConfigurationItemDto> configurationItems;
    /**
     * 正确答案 选择项id
     * */
    private String correctAnswer;
}
