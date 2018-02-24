package cn.llf.validate;

import cn.llf.framework.utils.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 创建者：   linlf
 * 创建时间： 2018/2/24
 * 描述：
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/common.xml")
public class ValidateTest {

    @Test
    public void testDouble(){
        log.info(ValidateUtil.isDouble("1223.2")+"");
    }
}
