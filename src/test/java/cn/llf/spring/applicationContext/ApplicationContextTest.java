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
     * 从容器种获取bean方法：根据类回去，根据xml配置的id获取，根据xml配置的name获取
     */
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
        Car getBeanById = (Car)context.getBean("carId");
        Car getBeanByClass = context.getBean(Car.class);
        Car getBeanByName = (Car)context.getBean("carName");
        getBeanById.introduce();
        getBeanByClass.introduce();
        getBeanByName.introduce();
    }
}
