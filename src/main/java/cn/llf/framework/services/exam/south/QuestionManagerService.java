package cn.llf.framework.services.exam.south;

import cn.llf.framework.model.mongo.Question;
import cn.llf.framework.services.exam.dto.RadioQuestionDto;

import java.util.List;

/**
 * @Author: calvin
 * @Since: 2018/3/5 21:04
 * @Description:
 */
public interface QuestionManagerService {


    /**
     * 添加试题
     * @param mongoDto
     * @return
     */
    boolean add(Question mongoDto);

    /**
     * 试题查询
     * @return
     */
    List<RadioQuestionDto> listQuestion();
}
