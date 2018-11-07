package cn.llf.spring.applicationContext;

import cn.llf.spring.dto.Car;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author eleven
 * @date 2018/11/7
 * @description
 */
@Slf4j
public class ApplicationContextTest {


    /**
     * {@link ClassPathXmlApplicationContext}以类路径的方式加载启动spring ioc容器
     */
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        Car bean = context.getBean(Car.class);
        bean.introduce();
    }
}
