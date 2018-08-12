package cn.llf.framework.services.order.south;

import cn.llf.framework.dao.impl.mongo.OrderDao;
import cn.llf.framework.model.mongo.GoodsSaleCount;
import cn.llf.framework.model.mongo.Order;
import cn.llf.framework.model.mongo.SubOrder;
import cn.llf.framework.services.order.enums.CategoryType;
import cn.llf.framework.services.order.enums.SubOrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author: eleven
 * @since: 2018/8/8 21:28
 * @description:
 */
@Slf4j
@Service("orderManagerService")
public class OrderManagerServiceImpl implements OrderManagerService{

    @Autowired
    OrderDao orderDao;



    public boolean createOrder(){
        Order order = new Order();
        order.setBuyerId(UUID.randomUUID().toString().replaceAll("-",""));
        order.setSellerId(UUID.randomUUID().toString().replaceAll("-",""));
        order.setUnitRegionPath("/350000/350600/350624");

        List<SubOrder> subOrderList = new ArrayList<>();
        SubOrder bookOrder = new SubOrder();
        bookOrder.setId(UUID.randomUUID().toString().replaceAll("-",""));
        bookOrder.setDealPrice(new BigDecimal(10.4).setScale(2,BigDecimal.ROUND_FLOOR));
        bookOrder.setPurchaseQuantity(6);
        bookOrder.setTotalAmount(bookOrder.getDealPrice().multiply(new BigDecimal(bookOrder.getPurchaseQuantity())));
        subOrderList.add(bookOrder);

        SubOrder pencilOrder = new SubOrder();
        pencilOrder.setId(UUID.randomUUID().toString().replaceAll("-",""));
        pencilOrder.setDealPrice(new BigDecimal(25.9).setScale(2,BigDecimal.ROUND_FLOOR));
        pencilOrder.setPurchaseQuantity(3);
        pencilOrder.setTotalAmount(pencilOrder.getDealPrice().multiply(new BigDecimal(pencilOrder.getPurchaseQuantity())));
        pencilOrder.setType(CategoryType.PENCIL);
        pencilOrder.setStatus(SubOrderStatus.CANCEL);
        subOrderList.add(pencilOrder);

        SubOrder bagOrder = new SubOrder();
        bagOrder.setId(UUID.randomUUID().toString().replaceAll("-",""));
        bagOrder.setDealPrice(new BigDecimal(32).setScale(2,BigDecimal.ROUND_FLOOR));
        bagOrder.setPurchaseQuantity(7);
        bagOrder.setTotalAmount(bagOrder.getDealPrice().multiply(new BigDecimal(bagOrder.getPurchaseQuantity())));
        bagOrder.setType(CategoryType.BAG);
        bagOrder.setStatus(SubOrderStatus.RECEIVED);
        subOrderList.add(bagOrder);

        order.setSubOrderList(subOrderList);
        orderDao.save(order);

        return true;
    }


    public Order getOrderDetail(String id){

        Order order = (Order) orderDao.findById(id);

        return order;
    }


    @Override
    public List<GoodsSaleCount> countGoodSale(CategoryType type) {
        List<GoodsSaleCount> result = new ArrayList<>();
        Query query = new Query();
        query.limit(1);
        MapReduceOptions  options = MapReduceOptions.options().outputTypeInline();
        Map<String,Object> map = new HashMap<>();
        if (type != null)
            map.put("typeArg",type.name());
        options.scopeVariables(map);

        MapReduceResults<GoodsSaleCount> order = orderDao.getMt().mapReduce(query,"order",
                                "classpath:/config/mongo/GoodsSaleCountMap.js",
                                "classpath:/config/mongo/GoodsSaleCountReduce.js",
                                 options,
                                GoodsSaleCount.class);
        Iterator<GoodsSaleCount> iterator = order.iterator();
        while (iterator.hasNext()){
            GoodsSaleCount next = iterator.next();
            result.add(next);
        }

//        orderDao.getMt().getDb().getCollection("").mapReduce()

        return result;
    }
}
