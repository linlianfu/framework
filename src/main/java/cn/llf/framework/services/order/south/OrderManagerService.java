package cn.llf.framework.services.order.south;

import cn.llf.framework.model.mongo.GoodsSaleCount;

import java.util.List;

/**
 * @author: eleven
 * @since: 2018/8/8 21:28
 * @description:
 */
public interface OrderManagerService {


     List<GoodsSaleCount> countGoodSale();
}
