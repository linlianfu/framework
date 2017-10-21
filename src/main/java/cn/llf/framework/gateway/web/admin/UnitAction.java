package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.dao.impl.UnitDaoImpl;
import cn.llf.framework.model.Unit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Author：calvin
 * Date:  2017/10/21 0021
 */
@Slf4j
@Controller
@RequestMapping("unit")
public class UnitAction {
    @Autowired
    UnitDaoImpl unitDao;


    @RequestMapping("getUnitDetail")
    public Unit getUnitDetail(){
        Unit unit = new Unit();
        unit = (Unit)unitDao.findById("59e9f4f3854afe980f9593be",Unit.class);
        Criteria criteria = Criteria.where("name").is("正式施教机构注册2");
        Query query = new Query(criteria);
        List<Unit> unitList = (List<Unit>)unitDao.getMt().find(query,Unit.class);
        log.info("查询信息集合：{}",unit);
        return unit;
    }
}
