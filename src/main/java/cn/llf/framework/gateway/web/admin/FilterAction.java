package cn.llf.framework.gateway.web.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.llf.ability.course.south.model.mybatis.Course;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: eleven
 * @since: 2018/7/12 22:06
 * @description:
 */
@Slf4j
@Controller
@RequestMapping("filter")
public class FilterAction {

    @ResponseBody
    @RequestMapping(value = "request" ,method = RequestMethod.GET)
    public Course request(HttpServletRequest request){

        Course course = new Course();
        return course;
    }
}
