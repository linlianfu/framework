package cn.llf.framework.services.order.south;

import cn.llf.framework.dao.impl.mongo.MasterOrderDao;
import cn.llf.framework.model.mongo.GoodsSaleCount;
import cn.llf.framework.model.mongo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
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
    MasterOrderDao masterOrderDao;

    @Override
    public List<GoodsSaleCount> countGoodSale() {
        Order order1 = (Order)masterOrderDao.findById("5b66beb635dc9f501b26d213");
        List<GoodsSaleCount> result = new ArrayList<>();
        MapReduceResults<GoodsSaleCount> order = masterOrderDao.getMt().mapReduce("order", "classpath:\\config\\mongo\\GoodsSaleCountMap.js", "classpath:\\config\\mongo\\GoodsSaleCountReduce.js", GoodsSaleCount.class);
        Iterator<GoodsSaleCount> iterator = order.iterator();






        return result;
    }
}