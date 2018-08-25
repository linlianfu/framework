package cn.llf.framework.services.order.south;

import cn.llf.framework.model.mongo.GoodsSaleCount;
import cn.llf.framework.model.mongo.Order;
import cn.llf.framework.services.order.args.AggregateQuery;
import cn.llf.framework.services.order.dto.AggregateBuyerOrderInfo;
import cn.llf.framework.services.order.dto.OrderForm;
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
      * 统计每个买家的订单信息
      */
     List<AggregateBuyerOrderInfo> aggregateBuyerOrderInfo(AggregateQuery query);
}
