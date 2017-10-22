package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.model.Unit;
import cn.llf.framework.services.unit.dto.UnitQuery;
import cn.llf.framework.services.unit.south.IUnitService;
import cn.llf.mybatis.support.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    IUnitService unitService;

    @ResponseBody
    @RequestMapping("getUnitDetail")
    public Unit getUnitDetail(String id){
        return unitService.getDetailById(id);
    }

    @ResponseBody
    @RequestMapping(value = "addUnit",method = RequestMethod.POST)
    public void add(@RequestBody Unit unit){
        unitService.add(unit);
    }

    @ResponseBody
    @RequestMapping(value = "updateUnit",method = RequestMethod.POST)
    public void update(@RequestBody Unit unit){
        unitService.update(unit);
    }

    @ResponseBody
    @RequestMapping(value = "listByUnitQuery",method = RequestMethod.GET)
    public List<Unit> listByQuery(UnitQuery query){
        return unitService.findByQuery(query);
    }
    @ResponseBody
    @RequestMapping(value = "countByUnitQuery",method = RequestMethod.GET)
    public long count(UnitQuery query){
        return unitService.countByQuery(query);
    }

    @ResponseBody
    @RequestMapping(value = "pageUnit",method = RequestMethod.GET)
    public Page page(@ModelAttribute Page page, @ModelAttribute UnitQuery query){
        return unitService.page(page,query);
    }

    @ResponseBody
    @RequestMapping(value = "deleteUnit",method = RequestMethod.GET)
    public void delete(String id){
        unitService.deleteById(id);
    }


}
