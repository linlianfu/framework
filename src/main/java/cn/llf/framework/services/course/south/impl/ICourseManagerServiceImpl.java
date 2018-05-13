package cn.llf.framework.services.course.south.impl;

import cn.llf.framework.services.course.south.ICourseManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.llf.ability.course.south.arg.CourseQuery;
import priv.llf.ability.course.south.dto.CourseDto;
import priv.llf.ability.course.south.service.ICourseService;

import java.util.List;

/**
 * @author: eleven
 * @since: 2018/4/29 15:16
 * @description:
 */
@Slf4j
@Service("courseManagerService")
public class ICourseManagerServiceImpl implements ICourseManagerService {

    @Autowired
    ICourseService courseService;

    @Override
    public List<CourseDto> listCourse() {
        CourseQuery query = new CourseQuery();

        List<CourseDto> remoteDtoList = courseService.listCourse(query);
        return remoteDtoList;
    }
}
