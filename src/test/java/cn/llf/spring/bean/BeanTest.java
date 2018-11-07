package cn.llf.spring.bean;

import cn.llf.spring.dto.Car;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author eleven
 * @date 2018/11/6
 * @description
 */
@Slf4j
public class BeanTest {

    @Test
    public void test() throws IOException {
//        log.info("111");
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resources = resolver.getResource("classpath:spring/beans.xml");
        log.info(resources.getURL().toString());

        //spring底层的bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        //XmlBeanDefinitionReader通过resource装在spring配置信息并启动IOC容器，但是启动初始化的时候并没有实例化bean，
        // 真正实例化步骤是在第一次调用的时候，且会以bean name为key，存在ioc容器的hashMap的缓存种
        beanDefinitionReader.loadBeanDefinitions(resources);
        log.info("init beanFactory...");
        //实例化bean，如果bean是singleton，则第二次调用直接从缓存去，不会再实例化bean
        Car bean = beanFactory.getBean(Car.class);
        bean.introduce();

    }

}
