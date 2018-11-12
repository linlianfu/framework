package cn.llf.spring.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * @author eleven
 * @date 2018/11/12
 * @description bean生命周期研究
 */
@Data
@Slf4j
public class BeanInitProcessorService implements InitializingBean,DisposableBean,BeanNameAware,BeanFactoryAware {
    /**
     * 地区
     */
    private String region;

    /**
     * 颜色
     */
    private String color;
    /**
     * 数量
     */
    private int count;


    public BeanInitProcessorService(){
        log.info(">>>>>1:构造函数调用");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info(">>>>>3:BeanFactoryAware.setBeanFactory");

    }

    @Override
    public void setBeanName(String name) {
        log.info(">>>>>2:BeanNameAware.setBeanName,入参:{}",name);

    }

    @Override
    public void destroy() throws Exception {
        log.info("销毁前的值：{}",this.toString());
        log.info(">>>>>6:DisposableBean.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(">>>>>4:InitializingBean.afterPropertiesSet");
    }

    public void myInit(){
        log.info(">>>>>5:myInit");
    }
    public void myDestroy(){
        log.info(">>>>>7:myDestroy");
    }


    @Override
    public String toString() {
        return "BeanInitProcessorService{" +
                "region='" + region + '\'' +
                ", color='" + color + '\'' +
                ", count=" + count +
                '}';
    }
}
