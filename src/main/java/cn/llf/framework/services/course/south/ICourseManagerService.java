package cn.llf.framework.services.course.south;

import priv.llf.ability.course.south.dto.CourseDto;

import java.util.List;

/**
 * @author: eleven
 * @since: 2018/4/29 15:15
 * @description:
 */
public interface ICourseManagerService {
    /**
     * 查询课程集合
     * @return
     */
    List<CourseDto> listCourse();
}
