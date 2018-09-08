package cn.llf.validate;

import cn.eleven.common.utils.RegexValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author eleven
 * @date  2018/2/24
 * @description
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/common.xml")
public class ValidateTest {

    @Test
    public void testDouble(){
        log.info(RegexValidateUtil.isDouble("1223.2")+"");
    }
}
