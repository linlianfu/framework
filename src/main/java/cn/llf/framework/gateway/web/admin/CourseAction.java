package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.gateway.commons.AbstractFrameWorkAction;
import cn.llf.framework.gateway.web.admin.dto.CourseDTO;
import cn.llf.framework.services.course.south.ICourseManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    @GetMapping("course")
    public CourseDTO course(String id){
        log.info(">>>>>收到入参[{}]",id);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setEnable(true);
        courseDTO.setName("eleven");
        courseDTO.setId("111111111111222222222222222222");
        return courseDTO;
    }

    @PostMapping("addCourse")
    public CourseDTO addCourse(@RequestBody CourseDTO courseDTO){
        log.info("收到入参:{}",courseDTO);
        courseDTO.setId("3333333333333333333333333");
        courseDTO.setName("chinese");
        courseDTO.setEnable(true);
        return courseDTO;
    }
}
