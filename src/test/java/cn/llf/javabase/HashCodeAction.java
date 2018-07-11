package cn.llf.javabase;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import priv.llf.ability.course.south.dto.CourseDto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: eleven
 * @since: 2018/5/22 15:19
 * @description:
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/common.xml")
public class HashCodeAction {

    @Test
    public void testHasCodeEquals(){
        CourseDto math = new CourseDto();
        math.setId("2");
        CourseDto chinese = new CourseDto();
        chinese.setId("2");


        CourseDto mathReference = new CourseDto();
        mathReference = math;
        log.info(String.valueOf("math的hashcode值为："+math.hashCode()));
        log.info(String.valueOf("chinese的hashcode值为："+chinese.hashCode()));
        log.info(String.valueOf("对象equals是否相等:"+math.equals(chinese)));
        log.info(String.valueOf("对象=否相等:"+(math==chinese)));


        Map<String ,String> map =new HashMap<>();
        map.put("","");



    }
}
