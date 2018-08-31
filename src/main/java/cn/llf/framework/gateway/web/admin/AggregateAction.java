package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.gateway.commons.AbstractFrameWorkAction;
import cn.llf.framework.services.order.args.AggregateQuery;
import cn.llf.framework.services.order.dto.AggregateBuyerOrderInfo;
import cn.llf.framework.services.order.dto.UserOrderStatisticsDto;
import cn.llf.framework.services.order.south.OrderManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.llf.mybatis.support.Page;

import java.util.List;

/**
 * @author: eleven
 * @date: 2018/8/25 11:23
 * @description: mongo聚合管道学习
 */
@Slf4j
@RestController
@RequestMapping("aggregate")
public class AggregateAction extends AbstractFrameWorkAction {
    @Autowired
    OrderManagerService service;

    /**
     * 通过封装的聚合管道实现聚合
     * @param query
     * @return
     */
    @GetMapping("aggregateBuyerOrderInfoImplementsByAggregation")
    public List<AggregateBuyerOrderInfo> listAggregateBuyerOrderInfoImplementsByAggregation(@ModelAttribute AggregateQuery query){
        return service.listAggregateBuyerOrderInfoImplementsByAggregation(query);
    }
    /**
     * 通过原生的DBObject的聚合管道实现聚合
     * @param query
     * @return
     */
    @GetMapping("listAggregateBuyerOrderInfoImplementsByDBObject")
    public List<AggregateBuyerOrderInfo> listAggregateBuyerOrderInfoImplementsByDBObject(@ModelAttribute AggregateQuery query){
        return service.listAggregateBuyerOrderInfoImplementsByDBObject(query);
    }
    /**
     * 用户订单分类统计
     * @param query
     * @return
     */
    @GetMapping("pageUserOrderStatistic")
    public Page<UserOrderStatisticsDto> pageUserOrderStatistic(@ModelAttribute Page page, @ModelAttribute AggregateQuery query){
        return service.pageUserOrderStatistic(page,query);
    }
    /**
     * 用户订单分类统计
     * @param query
     * @return
     */
    @GetMapping("pageUserOrderStatisticByDBObject")
    public Page<UserOrderStatisticsDto> pageUserOrderStatisticByDBObject(@ModelAttribute Page page, @ModelAttribute AggregateQuery query){
        return service.pageUserOrderStatisticByDBObject(page,query);
    }


}
