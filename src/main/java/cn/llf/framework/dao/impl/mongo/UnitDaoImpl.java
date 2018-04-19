package cn.llf.framework.dao.impl.mongo;

import cn.llf.framework.model.mongo.Unit;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import priv.llf.commons.dao.MDSpringDaoTemplate;

/**
 * @author: eleven
 * Date:  2017/10/21 0021
 */
@Slf4j
@Data
@Repository("unit")
public class UnitDaoImpl extends MDSpringDaoTemplate<Unit> {
}
