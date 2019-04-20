package cn.llf.framework.services.commoditySku.south.impl;

import cn.llf.framework.dao.impl.mongo.CommoditySkuDao;
import cn.llf.framework.model.mongo.CommoditySku;
import cn.llf.framework.services.commoditySku.south.ICommodityManagerService;
import com.mongodb.DBObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eleven
 * @date 2019/4/18
 * @description
 */
@Slf4j
@Service("commodityManagerService")
public class CommodityManagerServiceImpl implements ICommodityManagerService{

    @Autowired
    protected CommoditySkuDao commoditySkuDao;


    public List<CommoditySku> listBySkuProperty(){

        Criteria criteria = new Criteria();

        Criteria elemMatchYear = Criteria.where("propertyId").is("year").and("value").is("2019");
        Criteria elemMatchSubject = Criteria.where("propertyId").is("subject").and("value").is("public");

        List<DBObject> elemMatchList = new ArrayList<>();
        elemMatchList.add(new Criteria().elemMatch(elemMatchYear).getCriteriaObject());
        elemMatchList.add(new Criteria().elemMatch(elemMatchSubject).getCriteriaObject());
        criteria.and("propertyList").all(elemMatchList);

        List<CommoditySku> list = (List<CommoditySku> )commoditySkuDao.findByQuery(new Query(criteria));
        list.forEach(p->{
            log.info(">>>>>>>>>>>>>item:{}",p);
        });
        return list;

    }
}
