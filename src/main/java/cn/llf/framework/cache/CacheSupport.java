package cn.llf.framework.cache;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;


/**
 * 创建者：   linlf
 * 创建时间： 2017/8/14
 * 描述：
 */
@Slf4j
@Service("cacheSupport")
public class CacheSupport implements InitializingBean,DisposableBean{

    @Setter
    private String projectName;



    public void initMethod(){
        log.info("xml配置的程序启动调用initMethod()");
    }

    @Override
    public void destroy() throws Exception {
        log.info("程序终止，调用spring bean: DisposableBean的destroy()");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("程序启动，调用spring bean :InitializingBean的afterPropertiesSet()");
    }
}
