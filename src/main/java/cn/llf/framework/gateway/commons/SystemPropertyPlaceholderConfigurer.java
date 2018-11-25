package cn.llf.framework.gateway.commons;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author eleven
 * @date 2018/11/25
 * @description
 */
@Slf4j
public class SystemPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    public Map<String,String> systemPropertyMap = new HashMap<>();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()) {
            Object value = props.get(key);
            systemPropertyMap.put(key.toString(),value.toString());
        }

    }

    public String getProperty(String key){
        String value = "";
        value = systemPropertyMap.getOrDefault(key,"");
        log.debug("获取的key:{},value:{}");
        return value;
    }

}
