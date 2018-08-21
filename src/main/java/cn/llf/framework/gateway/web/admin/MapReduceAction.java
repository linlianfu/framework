package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.gateway.commons.AbstractFrameWorkAction;
import cn.llf.framework.model.mongo.GoodsSaleCount;
import cn.llf.framework.model.mongo.Order;
import cn.llf.framework.services.order.dto.OrderForm;
import cn.llf.framework.services.order.enums.CategoryType;
import cn.llf.framework.services.order.south.OrderManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    /**
     * 创建订单
     * @return
     */
    @PostMapping("createOrder")
    public boolean createOrder(@RequestBody @Valid OrderForm form){
        return orderManagerService.createOrder(form);
    }
    /**
     * 获取订单详情
     * @return
     */
    @GetMapping("getOrderDetail")
    public Order getOrderDetail(String id){
        return orderManagerService.getOrderDetail(id);
    }

    /**
     * 物品销售统计
     * @return
     */
    @GetMapping("countGoodSale")
    public List<GoodsSaleCount> countGoodSale(CategoryType type){
        return orderManagerService.countGoodSale(type);
    }
}
