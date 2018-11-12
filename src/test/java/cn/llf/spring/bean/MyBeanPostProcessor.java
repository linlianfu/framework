package cn.llf.spring.bean;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author eleven
 * @date 2018/11/12
 * @description
 */
@Slf4j
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanInitProcessorService){
            BeanInitProcessorService beanInitProcessorService = (BeanInitProcessorService) bean;
            log.info("BeanInitProcessorService属性：{}",beanInitProcessorService.toString());
            if (StringUtils.isBlank(beanInitProcessorService.getRegion())){
                log.info("属性【region】为空，设置为福州");
                beanInitProcessorService.setRegion("福州");
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanInitProcessorService){
            BeanInitProcessorService beanInitProcessorService = (BeanInitProcessorService) bean;
            if (beanInitProcessorService.getCount() >100 ){
                beanInitProcessorService.setCount(12);
                log.info("count调整为12");
            }
        }
        return bean;
    }
}
