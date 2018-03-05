package cn.llf.framework.services.exam.south.impl;

import cn.llf.framework.dao.impl.mongo.QuestionExtendMongoDaoImpl;
import cn.llf.framework.model.mongo.Question;
import cn.llf.framework.services.exam.south.QuestionManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
