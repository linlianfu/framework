package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.services.commoditySku.south.ICommodityManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "listBySkuProperty")
    public void listBySkuProperty(){
        service.listBySkuProperty();
    }
}
