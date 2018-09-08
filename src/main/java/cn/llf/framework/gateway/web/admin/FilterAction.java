package cn.llf.framework.gateway.web.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.llf.ability.course.south.model.mybatis.Course;

import javax.servlet.http.HttpServletRequest;

/**
 * @author eleven
 * @since 2018/7/12
 * @description
 */
@Slf4j
@RestController
@RequestMapping("filter")
public class FilterAction {

    @GetMapping(value = "request")
    public Course request(HttpServletRequest request){

        Course course = new Course();
        return course;
    }
}
