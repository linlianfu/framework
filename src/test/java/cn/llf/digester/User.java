package cn.llf.digester;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eleven
 */
@Data
public class User {
    /**
     * 姓名
     */
    private String name;
    /**
     * 年级
     */
    private int grade;
    /**
     * 课程集合
     */
    private List<Course> courseList;


    public void addCourse(Course course){
        if (CollectionUtils.isEmpty(this.courseList)){
            this.courseList = new ArrayList<>();
        }
        this.courseList.add(course);
    }
}
