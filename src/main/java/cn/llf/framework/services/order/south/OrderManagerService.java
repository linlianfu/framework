package cn.llf.framework.services.order.south;

import cn.eleven.common.dao.Page;
import cn.llf.framework.model.mongo.GoodsSaleCount;
import cn.llf.framework.model.mongo.Order;
import cn.llf.framework.services.order.args.AggregateQuery;
import cn.llf.framework.services.order.args.SampleQuery;
import cn.llf.framework.services.order.dto.AggregateBuyerOrderInfo;
import cn.llf.framework.services.order.dto.OrderForm;
import cn.llf.framework.services.order.dto.UserOrderStatisticsDto;
import cn.llf.framework.services.order.enums.CategoryType;

import java.util.List;

/**
 * @author: eleven
 * @since: 2018/8/8 21:28
 * @description:
 */
public interface OrderManagerService {


     /**
      * 创建订单
      */
     boolean createOrder(OrderForm form);

     /**
      * 获取订单详情
      * @param id
      * @return
      */
     Order getOrderDetail(String id);
     /**
      * 物品销售统计
      * @return
      */
     List<GoodsSaleCount> countGoodSale(CategoryType type);

     /**
      * 通过聚合管道实现统计每个买家的订单信息
      * 实现方式：mongoTemplate的聚合接口，入参为封装了操作符的Aggregation实现
      */
     List<AggregateBuyerOrderInfo> listAggregateBuyerOrderInfoImplementsByAggregation(AggregateQuery query);

     /**
      * 通过原生的DBObject的聚合管道实现聚合，
      * @param query
      * @return
      */
     List<AggregateBuyerOrderInfo> listAggregateBuyerOrderInfoImplementsByDBObject(AggregateQuery query);

     /**
      * 用户订单分类统计
      * @param page
      * @param query
      * @return
      */
     Page<UserOrderStatisticsDto> pageUserOrderStatistic(Page page, AggregateQuery query);
     /**
      * 用户订单分类统计
      * @param page
      * @param query
      * @return
      */
     Page<UserOrderStatisticsDto> pageUserOrderStatisticByDBObject(Page page,AggregateQuery query);

     /**
      * 随机取样
      * @param query
      * @return
      */
     List<Order> randomSampleOrder(SampleQuery query);
}
