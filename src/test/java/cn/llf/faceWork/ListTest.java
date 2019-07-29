package cn.llf.faceWork;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author eleven
 * @date 2019/7/7
 * @description
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:common.xml")
public class ListTest {


    @Test
    public void modifyList(){
        List<String> list = Arrays.asList("1");
        log.info("size:{}",list.size());
        list.add("1");
        log.info("size:{}",list.size());

    }
}
