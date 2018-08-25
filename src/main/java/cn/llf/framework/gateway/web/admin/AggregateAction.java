package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.gateway.commons.AbstractFrameWorkAction;
import cn.llf.framework.services.order.args.AggregateQuery;
import cn.llf.framework.services.order.dto.AggregateBuyerOrderInfo;
import cn.llf.framework.services.order.south.OrderManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("lisBuyerOrderInfo")
    public List<AggregateBuyerOrderInfo> lisBuyerOrderInfo(@ModelAttribute AggregateQuery query){
        return service.aggregateBuyerOrderInfo(query);
    }


}
