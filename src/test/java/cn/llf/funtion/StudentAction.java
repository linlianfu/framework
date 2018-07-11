package cn.llf.funtion;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author: eleven
 * @since: 2018/5/13 15:22
 * @description:
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:common.xml")
public class StudentAction {

    private List<StudentDto> studentDtoList = new ArrayList<>();

    @Before
    public void initStudent(){
        StudentDto s1 = new StudentDto(UUID.randomUUID().toString().replaceAll("-",""),
                "张三", 10,"男",4,60,70);
        StudentDto s2 = new StudentDto(UUID.randomUUID().toString().replaceAll("-",""),
                "小青", 13,"女",7,55,44);
        StudentDto s3 = new StudentDto(UUID.randomUUID().toString().replaceAll("-",""),
                "王五", 11,"男",5,80,90);
        StudentDto s4 = new StudentDto(UUID.randomUUID().toString().replaceAll("-",""),
                "小红", 12,"女",6,12,66);
        studentDtoList.add(s1);
        studentDtoList.add(s2);
        studentDtoList.add(s3);
        studentDtoList.add(s4);
    }

    @Test
    public void filterPassStudent(){
       studentDtoList = studentDtoList.stream().filter(p->p.getChineseScore()>=60 && p.getMathScore() >= 60).collect(Collectors.toList());
       studentDtoList.forEach(s->log.info(s.toString()));
    }
    @Test
    public void getAllMathScore(){
        List<Double> collect = studentDtoList.stream().map(StudentDto::getMathScore).collect(Collectors.toList());
        double asDouble = studentDtoList.stream().mapToDouble(StudentDto::getMathScore).average().orElse(0);
        log.info(collect.toString());
        log.info(String.valueOf(asDouble));
    }
    @Test
    public void group(){
        Map<String, Map<Integer, List<StudentDto>>> collect = studentDtoList.stream().collect(Collectors.groupingBy(p -> p.getSex(), Collectors.groupingBy(p -> p.getGrade())));
    }
}
