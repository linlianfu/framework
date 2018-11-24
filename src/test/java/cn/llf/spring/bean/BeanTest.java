package cn.llf.spring.bean;

import cn.llf.spring.dto.Car;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author eleven
 * @date 2018/11/6
 * @description
 */
@Slf4j
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/spring/beanInitProcessor.xml")
public class BeanTest {

//    @Autowired
//    BeanInitProcessorService beanInitProcessor;

    /**
     * 测试bean的生命周期
     * 1.初始化beanFactory：将spring的配置文件信息装入到bean定义注册表{@link org.springframework.beans.factory.support.BeanDefinitionRegistry}中，
     * 但此时bean还未初始化
     * 2.调用工厂后处理器：根据反射机制从BeanDefinitionRegistry中找出所有实现了{@link org.springframework.beans.factory.config.BeanFactoryPostProcessor}的接口
     * 调用唯一的接口方法{postProcessBeanFactory}，该方法目前可以实现初始化一些配置文件，比如配置了数据库配置文件的systemProperty文件等信息；
     * 3.注册bean后处理器：根据反射机制从BeanDefinitionRegistry中找出所有实现了{@link org.springframework.beans.factory.config.BeanPostProcessor}的bean
     * 并将他们注册到容器bean后处理器表中
     * 4.还有其他一系列操作，这里不在描述
     * 5.初始化所有单例模式的bean，除了懒加载模式的bean外；初始化后将他们放到spring的缓存容器中；
     * @throws IOException
     */
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


    @Test
    public void beanInitProcessorTest(){

        log.warn("========================请查看控制台打印序号");

    }

}
