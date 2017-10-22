package cn.llf.framework.dao.impl;

import cn.llf.framework.model.Unit;
import cn.llf.mongodb.core.MDSpringDaoTemplate;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

/**
 * Author：calvin
 * Date:  2017/10/21 0021
 */
@Log4j
@Data
@Repository("unit")
public class UnitDaoImpl extends MDSpringDaoTemplate<Unit> {

}
