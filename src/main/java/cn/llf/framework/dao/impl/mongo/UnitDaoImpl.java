package cn.llf.framework.dao.impl.mongo;

import cn.llf.framework.model.mongo.Unit;
import cn.llf.mongodb.core.MDSpringDaoTemplate;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Author：calvin
 * Date:  2017/10/21 0021
 */
@Slf4j
@Data
@Repository("unit")
public class UnitDaoImpl extends MDSpringDaoTemplate<Unit> {
}
