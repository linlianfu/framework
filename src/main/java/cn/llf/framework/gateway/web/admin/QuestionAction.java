package cn.llf.framework.gateway.web.admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: calvin
 * @Since: 2018/3/10 13:03
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("question")
public class QuestionAction {

    @ResponseBody
    @RequestMapping("testJar")
    public void testJar(){
        log.info("11111111111");

    }
}
