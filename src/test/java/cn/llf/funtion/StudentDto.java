package cn.llf.funtion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: eleven
 * @since: 2018/5/13 15:15
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    /**
     * 学生id
     */
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private int age;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年级
     */
    private int grade;
    /**
     * 语文成绩
     */
    private double chineseScore;
    /**
     * 数学成绩
     */
    private double mathScore;
}
