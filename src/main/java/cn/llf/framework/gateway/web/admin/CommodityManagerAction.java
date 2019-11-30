package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.model.mongo.CommoditySku;
import cn.llf.framework.services.commoditySku.south.ICommodityManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author eleven
 * @date 2019/4/18
 * @description
 */
@Slf4j
@RestController
@RequestMapping("commodityManager")
public class CommodityManagerAction {
    @Autowired
    private ICommodityManagerService service;


    /**
     * http://localhost:8080/web/admin/commodityManager/listBySkuProperty
     * @return
     */
    @GetMapping(value = "listBySkuProperty")
    public List<CommoditySku> listBySkuProperty(){
        return service.listBySkuProperty();
    }


    /**
     * 使用CrossOrigin解决该方法可以跨域
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "lisCommodityBySkuOr")
    public List<CommoditySku> lisCommodityBySkuOr(){
        return service.lisCommodityBySkuOr();
    }
}
