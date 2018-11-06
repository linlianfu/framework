package cn.llf.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
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

    }
}
