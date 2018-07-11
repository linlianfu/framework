package cn.llf.order;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: eleven
 * @since: 2018/7/11 14:15
 * @description:
 */
@Data
@AllArgsConstructor
public class User implements Comparable<User>{
    private int age;
    private String sex;
    private int grade;

    @Override
    public int compareTo(User o) {
        return -o.getAge()+this.getAge();
    }
}
