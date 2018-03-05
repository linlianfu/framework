package cn.llf.framework.services.exam.south.impl;

import cn.llf.framework.dao.impl.mongo.QuestionExtendMongoDaoImpl;
import cn.llf.framework.model.mongo.Question;
import cn.llf.framework.services.exam.dto.RadioQuestionDto;
import cn.llf.framework.services.exam.south.QuestionManagerService;
import cn.llf.mybatis.support.Page;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: calvin
 * @Since: 2018/3/5 21:04
 * @Description:
 */
@Controller("questionManagerService")
public class QuestionManagerServiceImpl  implements QuestionManagerService{

    @Autowired
    QuestionExtendMongoDaoImpl questionExtendMongoDao;
    @Override
    public boolean add(Question mongoDto) {


        questionExtendMongoDao.save(mongoDto);
        return false;
    }

    @Override
    public List<RadioQuestionDto> listQuestion() {
        List<RadioQuestionDto> result = new ArrayList<>();
        Criteria criteria = Criteria.where("topic").ne("");
        Query query = new Query(criteria);
        List<Question> modelList =(List<Question>) questionExtendMongoDao.findByQuery(query);
        if (CollectionUtils.isEmpty(modelList))return Collections.emptyList();
        modelList.forEach(p->{
            RadioQuestionDto radioQuestionDto = new ModelMapper().map(p,RadioQuestionDto.class);
            result.add(radioQuestionDto);
        });
        return result;
    }


}
