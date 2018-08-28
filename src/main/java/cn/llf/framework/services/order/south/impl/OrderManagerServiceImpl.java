package cn.llf.framework.services.order.south.impl;

import cn.llf.framework.dao.impl.mongo.OrderDao;
import cn.llf.framework.model.mongo.GoodsSaleCount;
import cn.llf.framework.model.mongo.Order;
import cn.llf.framework.model.mongo.SubOrder;
import cn.llf.framework.services.order.args.AggregateQuery;
import cn.llf.framework.services.order.dto.AggregateBuyerOrderInfo;
import cn.llf.framework.services.order.dto.OrderForm;
import cn.llf.framework.services.order.enums.CategoryType;
import cn.llf.framework.services.order.enums.MasterOrderStatus;
import cn.llf.framework.services.order.enums.SubOrderStatus;
import cn.llf.framework.services.order.south.OrderManagerService;
import com.mongodb.AggregationOptions;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
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
public class OrderManagerServiceImpl implements OrderManagerService {

    @Autowired
    OrderDao orderDao;



    public boolean createOrder(OrderForm form){
        List<String> buyerIdList = new ArrayList<>();
        List<String> sellerIdList = new ArrayList<>();
        List<String> regionList = new ArrayList<>();
        for (int i =0;i<6;i++){
            buyerIdList.add(UUID.randomUUID().toString().replaceAll("-",""));
            sellerIdList.add(UUID.randomUUID().toString().replaceAll("-",""));
        }
        regionList.add("/360000/350600/350624");
        regionList.add("/350000/350600/350624");
        regionList.add("/340000/350600/350624");
        regionList.add("/330000/350600/350624");
        regionList.add("/320000/350600/350624");
        regionList.add("/310000/350600/350624");

        for (int i = 0;i<10;i++){
            int buyerIndex = i%(int)(Math.random()*5+1);
            Order order = new Order();
            order.setBuyerId(buyerIdList.get(buyerIndex));
            order.setSellerId(sellerIdList.get(buyerIndex));
            order.setUnitRegionPath(regionList.get(buyerIndex));

            List<SubOrder> subOrderList = new ArrayList<>();
            SubOrder bookOrder = new SubOrder();
            bookOrder.setId(UUID.randomUUID().toString().replaceAll("-",""));
            bookOrder.setDealPrice(12.8);
            bookOrder.setPurchaseQuantity((int) (Math.random()*10));
            bookOrder.setTotalAmount(bookOrder.getDealPrice()*bookOrder.getPurchaseQuantity());
            subOrderList.add(bookOrder);

            SubOrder pencilOrder = new SubOrder();
            pencilOrder.setId(UUID.randomUUID().toString().replaceAll("-",""));
            pencilOrder.setDealPrice(24);
            pencilOrder.setPurchaseQuantity((int) (Math.random()*10));
            pencilOrder.setTotalAmount(pencilOrder.getDealPrice()*pencilOrder.getPurchaseQuantity());
            pencilOrder.setType(CategoryType.PENCIL);
            pencilOrder.setStatus(SubOrderStatus.CANCEL);
            subOrderList.add(pencilOrder);

            SubOrder bagOrder = new SubOrder();
            bagOrder.setId(UUID.randomUUID().toString().replaceAll("-",""));
            bagOrder.setDealPrice(35);
            bagOrder.setPurchaseQuantity((int) (Math.random()*10));
            bagOrder.setTotalAmount(bagOrder.getDealPrice()*bagOrder.getPurchaseQuantity());
            bagOrder.setType(CategoryType.BAG);
            bagOrder.setStatus(SubOrderStatus.RECEIVED);
            subOrderList.add(bagOrder);

            SubOrder otherOrder = new SubOrder();
            otherOrder.setId(UUID.randomUUID().toString().replaceAll("-",""));
            otherOrder.setDealPrice(22);
            otherOrder.setPurchaseQuantity((int) (Math.random()*10));
            otherOrder.setTotalAmount(otherOrder.getDealPrice()*otherOrder.getPurchaseQuantity());
            otherOrder.setType(CategoryType.OTHER);
            otherOrder.setStatus(SubOrderStatus.RECEIVED);
            subOrderList.add(otherOrder);

            order.setSubOrderList(subOrderList);
            order.setTotalAmount();
            order.setMarker("正常的订单");
            orderDao.save(order);
        }

        return true;
    }


    public Order getOrderDetail(String id){

        Order order = (Order) orderDao.findById(id);

//        orderDao.getMt().find()
//        Criteria criteria = Criteria.where("").alike()
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

    @Override
    public List<AggregateBuyerOrderInfo> listAggregateBuyerOrderInfoImplementsByAggregation(AggregateQuery query) {

        List<AggregateBuyerOrderInfo> result = new ArrayList<>();

        //利用封装的Aggregation实现
        //步骤1：过滤复合条件的需要聚合的数据
        List<AggregationOperation> aggregationOperationList = new ArrayList<>();
        String buyerId = query.getBuyerId(),
               unitRegionPath = query.getUnitRegionPath();
        MasterOrderStatus masterOrderStatus = query.getMasterOrderStatus();

        if (StringUtils.isNotBlank(buyerId)
            || StringUtils.isNotBlank(unitRegionPath)
            || masterOrderStatus != null){
            Criteria criteria = new Criteria();

            if (StringUtils.isNotBlank(buyerId)){
                criteria.and("unitRegionPath").is(query.getUnitRegionPath());
            }
            if (StringUtils.isNotBlank(unitRegionPath)){
                criteria.and("unitRegionPath").is(query.getUnitRegionPath());
            }
            if (masterOrderStatus != null){
                criteria.and("unitRegionPath").is(query.getUnitRegionPath());
            }
            aggregationOperationList.add(Aggregation.match(criteria));
        }
        //步骤2：分组聚合，并统计总金额和平均金额
        aggregationOperationList.add(
                Aggregation.group("buyerId").count().as("count")
                        .sum("totalAmount").as("totalAmount")
                        .avg("totalAmount").as("avg")
        );
        Aggregation aggregation = Aggregation.newAggregation(aggregationOperationList);
        AggregationResults<AggregateBuyerOrderInfo> aggregate = orderDao.getMt().aggregate(aggregation, Order.class, AggregateBuyerOrderInfo.class);

        Iterator<AggregateBuyerOrderInfo> iterator = aggregate.iterator();
        while (iterator.hasNext()){
            AggregateBuyerOrderInfo next = iterator.next();
            result.add(next);
        }
        return result;
    }

    @Override
    public List<AggregateBuyerOrderInfo> listAggregateBuyerOrderInfoImplementsByDBObject(AggregateQuery query) {

        List<AggregateBuyerOrderInfo> result = new ArrayList<>();

        List<DBObject> pipeline = new ArrayList<>();
        //按买家id分组
        BasicDBObject dbObject = new BasicDBObject("_id","$buyerId");
        dbObject.put("count",new BasicDBObject("$sum",1));
        dbObject.put("totalAmount",new BasicDBObject("$sum","$totalAmount"));
        dbObject.put("avg",new BasicDBObject("$avg","$totalAmount"));
        BasicDBObject groupObject = new BasicDBObject("$group",dbObject);
        pipeline.add(groupObject);

        AggregationOptions build = AggregationOptions.builder()
                .outputMode(AggregationOptions.OutputMode.CURSOR)
//                .batchSize(Integer.MAX_VALUE)
                .build();


         Cursor aggregate = orderDao.getMt().getCollection(Order.class.getSimpleName().toLowerCase()).aggregate(pipeline, build);
         while (aggregate.hasNext()){
            AggregateBuyerOrderInfo info = new AggregateBuyerOrderInfo();
            DBObject next = aggregate.next();
            String id = (String)next.get("_id");
            int count = (Integer)next.get("count");
            double totalAmount = (Double) next.get("totalAmount");
            double avg = (Double)next.get("avg");
            info.setId(id);
            info.setCount(count);
            info.setTotalAmount(totalAmount);
            info.setAvg(avg);
            result.add(info);
         }
        return result;
    }
}
