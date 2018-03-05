package cn.llf.framework.services.exam.south;

import cn.llf.framework.model.mongo.Question;

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
}
