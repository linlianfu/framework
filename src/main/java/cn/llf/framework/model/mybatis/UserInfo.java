package cn.llf.framework.model.mybatis;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@Data
@Entity
@Alias("userInfo")
@Table(name = "user_info")
public class UserInfo {

    @Id
    @Column(name = "id")
    private String id;


    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "identity")
    private String identity;


}
