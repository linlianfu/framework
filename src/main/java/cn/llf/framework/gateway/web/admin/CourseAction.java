package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.services.course.south.ICourseManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.llf.ability.course.south.dto.CourseDto;

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
    ICourseManagerService courseManagerService;



    @ResponseBody
    @RequestMapping(value = "addCourse" ,method = RequestMethod.POST)
    public void addCourse(){

    }

    @ResponseBody
    @RequestMapping(value = "listCourse" ,method = RequestMethod.GET)
    public List<CourseDto> listCourse(){

    return courseManagerService.listCourse();
    }
}
