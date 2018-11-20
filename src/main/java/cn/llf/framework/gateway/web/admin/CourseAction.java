package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.gateway.commons.AbstractFrameWorkAction;
import cn.llf.framework.services.course.south.ICourseManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.llf.ability.course.south.arg.CourseQuery;
import priv.llf.ability.course.south.dto.CourseDto;

import java.util.Collections;
import java.util.List;

/**s
 * @author eleven
 * @since 2018/3/10
 * @description
 */
@Slf4j
@RestController
@RequestMapping("course")
public class CourseAction extends AbstractFrameWorkAction{

    @Autowired
    ICourseManagerService courseManagerService;



    @PostMapping(value = "addCourse")
    public void addCourse(){

    }

    /**
     * 模拟参数没有使用注解绑定，springMVC可以自动绑定参数到model
     * @param query
     */
    @GetMapping(value = "getCourse")
    public void getCourse(CourseQuery query){
         log.info(query.toString());
    }

    @GetMapping(value = "listCourse")
    public List<CourseDto> listCourse(){

    return courseManagerService.listCourse();
    }

    @GetMapping(value = "listCourseByType")
    public List<CourseDto> listCourse(String type){
        CourseDto courseDto =new CourseDto();
        courseDto.setName("1212");
        return Collections.singletonList(courseDto);
    }
}
