package cn.llf.framework.services.order.south;

import cn.llf.framework.dao.impl.mongo.OrderDao;
import cn.llf.framework.model.mongo.GoodsSaleCount;
import cn.llf.framework.model.mongo.Order;
import cn.llf.framework.model.mongo.SubOrder;
import cn.llf.framework.services.order.enums.CategoryType;
import cn.llf.framework.services.order.enums.SubOrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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
//        order.setBuyerId("da265d9e45f64a9cbe5dcdaadbff4e1d");
        order.setSellerId(UUID.randomUUID().toString().replaceAll("-",""));
        order.setUnitRegionPath("/350000/350600/350624");

        List<SubOrder> subOrderList = new ArrayList<>();
        SubOrder bookOrder = new SubOrder();
        bookOrder.setId(UUID.randomUUID().toString().replaceAll("-",""));
        bookOrder.setDealPrice(12.8);
        bookOrder.setPurchaseQuantity(6);
        bookOrder.setTotalAmount(bookOrder.getDealPrice()*bookOrder.getPurchaseQuantity());
        subOrderList.add(bookOrder);

        SubOrder pencilOrder = new SubOrder();
        pencilOrder.setId(UUID.randomUUID().toString().replaceAll("-",""));
        pencilOrder.setDealPrice(25);
        pencilOrder.setPurchaseQuantity(3);
        pencilOrder.setTotalAmount(pencilOrder.getDealPrice()*pencilOrder.getPurchaseQuantity());
        pencilOrder.setType(CategoryType.PENCIL);
        pencilOrder.setStatus(SubOrderStatus.CANCEL);
        subOrderList.add(pencilOrder);

        SubOrder bagOrder = new SubOrder();
        bagOrder.setId(UUID.randomUUID().toString().replaceAll("-",""));
        bagOrder.setDealPrice(32);
        bagOrder.setPurchaseQuantity(7);
        bagOrder.setTotalAmount(bagOrder.getDealPrice()*bagOrder.getPurchaseQuantity());
        bagOrder.setType(CategoryType.BAG);
        bagOrder.setStatus(SubOrderStatus.RECEIVED);
        subOrderList.add(bagOrder);

        SubOrder otherOrder = new SubOrder();
        otherOrder.setId(UUID.randomUUID().toString().replaceAll("-",""));
        otherOrder.setDealPrice(32);
        otherOrder.setPurchaseQuantity(1);
        otherOrder.setTotalAmount(otherOrder.getDealPrice()*otherOrder.getPurchaseQuantity());
        otherOrder.setType(CategoryType.OTHER);
        otherOrder.setStatus(SubOrderStatus.RECEIVED);
        subOrderList.add(otherOrder);

        order.setSubOrderList(subOrderList);
        order.setTotalAmount();
        order.setMarker("无买家id订单");
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
        //排序使用query的排序字段，对输入的数据document数据排序。这个选项早做优化时很有用，比如将sort的key和emit的key设定为想相同，
        //这样在reduce函数中就可以做更少的操作，极大提升效率。sort中的key必须有索引。
        query.with(new Sort(Sort.Direction.DESC,"sellerId"));
        MapReduceOptions  options = MapReduceOptions.options().outputTypeMerge();
        options.outputCollection("goodStatic");
        //设置map阶段能够进入到reduce处理的文档个数，可在这里设置，也可以在query中设置，效果一样的，内部通过
        // org.springframework.data.mongodb.core.MongoTemplate.copyMapReduceOptionsToCommand()转为MapReduceCommand.limit
//        options.limit(2);
        //设置mapReduce统计结果输出到的一个新的集合，则可以实现分页
        options.outputCollection("goodStatic");
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
            result.add(iterator.next());
        }

//        orderDao.getMt().getDb().getCollection("").mapReduce()

        return result;
    }
}
