package cn.llf.commodityManager;

import com.mongodb.DBObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: eleven
 * @date: 2019/4/19
 * @description:
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:common.xml")
public class ElemMatchAction {


    @Test
    public void testCriteria(){
        Criteria criteria = Criteria.where("propertyList");

        List<DBObject> elemMatch = new ArrayList<>();
        Criteria yearCriteria = Criteria.where("propertyId").is("year").and("value").is("2018");
        Criteria subjectCriteria = Criteria.where("propertyId").is("subject").and("value").is("public");

        elemMatch.add(new Criteria().elemMatch(yearCriteria).getCriteriaObject());
        elemMatch.add(new Criteria().elemMatch(subjectCriteria).getCriteriaObject());
        criteria.all(elemMatch);
        log.info(criteria.getCriteriaObject().toString());

    }
}
