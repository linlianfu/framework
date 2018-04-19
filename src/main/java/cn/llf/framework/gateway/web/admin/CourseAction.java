package cn.llf.framework.gateway.web.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.llf.ability.course.south.arg.CourseQuery;
import priv.llf.ability.course.south.dto.CourseDto;
import priv.llf.ability.course.south.service.ICourseService;

import java.util.List;

/**s
 * @author: eleven
 * @since: 2018/3/10 13:59
 * @description:
 */
@Slf4j
@Controller
@RequestMapping("course")
public class CourseAction {

    @Autowired
    ICourseService courseService;

    @ResponseBody
    @RequestMapping(value = "addCourse" ,method = RequestMethod.POST)
    public void addCourse(){

        CourseQuery query = new CourseQuery();

        List<CourseDto> remoteDtoList = courseService.listCourse(query);
        log.info("课程添加成功");

//        courseService.addCourse(courseDto);
    }

    @ResponseBody
    @RequestMapping(value = "listCourse" ,method = RequestMethod.GET)
    public List<CourseDto> listCourse(){

        CourseQuery query = new CourseQuery();

        List<CourseDto> remoteDtoList = courseService.listCourse(query);
        log.info("课程添加成功");
        return remoteDtoList;
    }
}
