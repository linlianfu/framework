package cn.llf.framework.dao.impl.mongo;

import cn.llf.framework.model.mongo.Question;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import priv.llf.commons.dao.MDSpringDaoTemplate;

/**
 * @Author: calvin
 * @Since: 2018/3/5 20:49
 * @Description:
 */
@Slf4j
@Data
@Repository("questionExtendMongoDaoImpl")
public class QuestionExtendMongoDaoImpl extends MDSpringDaoTemplate<Question> {
}
