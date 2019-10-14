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
 * @since: 2018/5/22 15:19 测试git命令提交
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
        //==比较的是是否完全是同一个对象，也就是是否在同一片内存空间上
        //而math和chinese存的分别是指向不同堆内对象的reference。
        //obj1==obj2 判断是obj1,obj2这两个引用变量是否相等，即它们所指向的对象是否为同一个对象。
        // 言外之意就是要求两个变量所指内存地址相等的时候，才能返回true，每个对象都有自己的一块内存，
        // 因此必须指向同一个对象才返回ture。
        log.info(String.valueOf("对象=否相等:"+(math==chinese)));


        Map<String ,String> map =new HashMap<>();
        map.put("","");



    }
    @Test
    public void copyTest(){

        CopyDTO copyDTO = new CopyDTO();
        CodeDto codeDto = new CodeDto();
        codeDto.setCode("原始对象");
        copyDTO.setCodeDto(codeDto);
        copyDTO.setI(10);
        log.info("原始对象:{}",copyDTO);
        CopyDTO clone = null;
        try {
            clone = (CopyDTO) copyDTO.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        log.info("clone result:{}",clone);
    }

    @Test
    public void memoryTest(){

        int i = Integer.MAX_VALUE;
        log.info(i+"");
        i++;
        log.info(i+"");
        long b = 1111111111;


    }
}
