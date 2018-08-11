package cn.llf.framework.services.order.south;

import cn.llf.framework.dao.impl.mongo.OrderDao;
import cn.llf.framework.model.mongo.GoodsSaleCount;
import cn.llf.framework.model.mongo.Order;
import cn.llf.framework.model.mongo.SubOrder;
import cn.llf.framework.services.order.enums.CategoryType;
import cn.llf.framework.services.order.enums.SubOrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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
        bookOrder.setDealPrice(new BigDecimal(10.4));
        bookOrder.setPurchaseQuantity(6);
        bookOrder.setTotalAmount(bookOrder.getDealPrice().multiply(new BigDecimal(bookOrder.getPurchaseQuantity())));
        subOrderList.add(bookOrder);

        SubOrder pencilOrder = new SubOrder();
        pencilOrder.setId(UUID.randomUUID().toString().replaceAll("-",""));
        pencilOrder.setDealPrice(new BigDecimal(25.9));
        pencilOrder.setPurchaseQuantity(3);
        pencilOrder.setTotalAmount(pencilOrder.getDealPrice().multiply(new BigDecimal(pencilOrder.getPurchaseQuantity())));
        pencilOrder.setType(CategoryType.PENCIL);
        pencilOrder.setStatus(SubOrderStatus.CANCEL);
        subOrderList.add(pencilOrder);

        SubOrder bagOrder = new SubOrder();
        bagOrder.setId(UUID.randomUUID().toString().replaceAll("-",""));
        bagOrder.setDealPrice(new BigDecimal(32));
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
    public List<GoodsSaleCount> countGoodSale() {
        List<GoodsSaleCount> result = new ArrayList<>();
        Criteria criteria = Criteria.where("userId").is("5b887a64246111e8b529a11e84e01b61")
                .and("masterOrder.unitId").is("5b887a211d2111e8b519a81382e02b6e");
        MapReduceResults<GoodsSaleCount> order = orderDao.getMt().mapReduce(new Query(criteria),"order",
                                "classpath:/config/mongo/GoodsSaleCountMap.js",
                                "classpath:/config/mongo/GoodsSaleCountReduce.js",
                                GoodsSaleCount.class);
        Iterator<GoodsSaleCount> iterator = order.iterator();
        while (iterator.hasNext()){
            GoodsSaleCount next = iterator.next();
            result.add(next);
        }
        return result;
    }
}
