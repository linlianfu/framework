package cn.llf.framework.gateway.web.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv_llf_ability_course_south_api.impl.CourseMnagerServiceImpl;

/**s
 * @Author: calvin
 * @Since: 2018/3/10 13:59
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("course")
public class CourseAction {


    public static void main(String arg[]){
        CourseMnagerServiceImpl courseMnagerService = new CourseMnagerServiceImpl();
        courseMnagerService.addCourse();
    }

    @ResponseBody
    @RequestMapping(value = "addCourse" ,method = RequestMethod.POST)
    public void addCourse(){

        CourseMnagerServiceImpl courseManagerService = new CourseMnagerServiceImpl();
        courseManagerService.addCourse();

        log.info("课程添加成功");



    }
}
