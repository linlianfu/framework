package cn.llf.spring.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * @author eleven
 * @date 2018/11/12
 * @description spring ioc 后置处理器
 */
@Slf4j
public class MyInstantiationAwareBeanPostProcessorAdapter extends InstantiationAwareBeanPostProcessorAdapter {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        log.info(">>>>>InstantiationAwareBeanPostProcessorAdapter#postProcessBeforeInstantiation,beanName:{}",beanName);
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        log.info(">>>>>InstantiationAwareBeanPostProcessorAdapter#postProcessAfterInstantiation,beanName:{}",beanName);
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        log.info(">>>>>InstantiationAwareBeanPostProcessorAdapter#postProcessPropertyValues,beanName:{}",beanName);
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info(">>>>>InstantiationAwareBeanPostProcessorAdapter#postProcessBeforeInitialization,beanName:{}",beanName);
        return super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info(">>>>>InstantiationAwareBeanPostProcessorAdapter#postProcessAfterInitialization,beanName:{}",beanName);
        return super.postProcessAfterInitialization(bean, beanName);
    }
}
