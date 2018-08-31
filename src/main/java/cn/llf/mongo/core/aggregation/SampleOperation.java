package cn.llf.mongo.core.aggregation;

import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

/**
 * @author: eleven
 * @date: 2018/8/30 21:38
 * @description: mongo聚合管道sample操作符
 */
public class SampleOperation  implements AggregationOperation{

    @Override
    public DBObject toDBObject(AggregationOperationContext context) {

        return null;
    }
}
