package cn.llf.spring.property;

import cn.llf.framework.gateway.commons.SystemPropertyPlaceholderConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author eleven
 * @date 2018/11/25
 * @description
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/common.xml")
public class SystemPropertyTest {

    @Autowired
    SystemPropertyPlaceholderConfigurer system;

    @Test
    public void getContext(){
        //考虑递归情况，再property文件中，本身也可以直接使用${}引用其他变量，
        //countyId=350101
        //比如：regionPath = 350000/350100/${countyId},则regionPath实际值为350000/350100/350101
        String exportUrl = system.getProperty("exportUrl");
        log.info(">>>>>导入路径：{}",exportUrl);

    }
}
