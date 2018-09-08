package cn.llf.framework.gateway.web.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: eleven
 * @since: 2018/3/10 13:03
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("question")
public class QuestionAction {

    @GetMapping("testJar")
    public void testJar(){
        log.info("11111111111");

    }
}
