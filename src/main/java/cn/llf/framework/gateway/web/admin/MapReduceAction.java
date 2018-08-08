package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.gateway.commons.AbstractFrameWorkAction;
import cn.llf.framework.model.mongo.GoodsSaleCount;
import cn.llf.framework.services.order.south.OrderManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: eleven
 * @since: 2018/8/8 21:23
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("mapReduce")
public class MapReduceAction extends AbstractFrameWorkAction {

    @Autowired
    OrderManagerService orderManagerService;



    @GetMapping("countGoodSale")
    public List<GoodsSaleCount> countGoodSale(){
        return orderManagerService.countGoodSale();
    }
}
