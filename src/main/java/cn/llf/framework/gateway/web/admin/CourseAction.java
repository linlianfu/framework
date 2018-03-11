package cn.llf.framework.gateway.web.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv_llf_ability_course_south_api.ICourseManagerService;
import priv_llf_ability_course_south_api.dto.CourseDto;
import priv_llf_ability_course_south_api.impl.CourseManagerServiceImpl;

/**s
 * @Author: calvin
 * @Since: 2018/3/10 13:59
 * @Description:
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

        CourseManagerServiceImpl courseManagerService = new CourseManagerServiceImpl();
        CourseDto courseDto = new CourseDto();
        courseManagerService.addCourse(courseDto);
        log.info("课程添加成功");

//        courseManagerService.addCourse(courseDto);



    }
}
