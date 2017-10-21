package cn.llf.mongodb.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Author：calvin
 * Date:  2017/10/21 0021
 */
public class MDSpringDaoTemplate  implements  IMongo{
    @Autowired
    @Setter
    @Getter
    MongoTemplate mt;
    @Override
    public void create(Object entity) {
    }


    @Override
    public Object findById(Object id, Class entityClass) {
        return this.mt.findById(id,entityClass);
    }
}
