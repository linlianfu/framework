package cn.llf;

import cn.llf.framework.model.mybatis.Bill;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author: eleven
 * @since: 2018/5/13 10:22
 * @description:
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:common.xml")
public class Collection {

    @Test
    public void functionTest(){
        defaultMethod(p->{
//            String tep = p;
            log.info("2");
            return 2;
        });
    }

    public<R> R defaultMethod(Function<String,R> function){
        R r = null;
            function.apply("");
            log.info("111");
            return r;
    }


     @Test
    public void testCollection(){
         List<String> list = new ArrayList<>();
         list.add(1+"h");
         list.add(2+"-");
         list.add(3+"=");
//         String max = Collections.max(list);
         List<String> copy = new ArrayList<>();
         copy.add("22");
         Collections.addAll(copy,"1","2");
//         copy.forEach(p->log.info(p));

//         List<Integer> integerList = new ArrayList<>();
//         integerList.add(22);
//         integerList.add(11);
//         integerList.add(122);
//         integerList.add(55);
//         Collections.sort(integerList);
//         integerList.forEach(p->{
//             log.info(String.valueOf(p));
//         });

         Bill bill = new Bill();
         bill.setId("2");
         bill.setTemp(2);
         Bill bill2 = new Bill();
         bill2.setId("3");
         bill2.setTemp(1);
         Bill bill3 = new Bill();
         bill3.setId("1");
         bill3.setTemp(3);
         List<Bill> billList = new ArrayList<>();
         billList.add(bill);
         billList.add(bill2);
         billList.add(bill3);
         Collections.sort(billList);
         billList.forEach(p->log.info(p.toString()));
//         billList.stream().sorted(new compara);
     }



     public void textCollector(){

     }




}
