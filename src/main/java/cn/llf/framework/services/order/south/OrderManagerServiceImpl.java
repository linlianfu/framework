package cn.llf.framework.services.order.south;

import cn.llf.framework.dao.impl.mongo.OrderDao;
import cn.llf.framework.model.mongo.GoodsSaleCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
