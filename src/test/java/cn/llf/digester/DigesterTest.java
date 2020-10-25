package cn.llf.digester;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.digester.Digester;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;

/**
 * @author eleven
 */
@Slf4j
public class DigesterTest {


    @Test
    public void parseXml(){
        Digester digester = new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("user",User.class);
        digester.addSetProperties("user");
        digester.addObjectCreate("user/course",Course.class);
        digester.addBeanPropertySetter("user/course/courseId");
        digester.addBeanPropertySetter("user/course/courseName");
        digester.addSetNext("user/course","addCourse");
        try {
            User user = (User) digester.parse(this.getClass().getClassLoader().getResourceAsStream("user.xml"));
            log.warn(JSON.toJSONString(user));
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
