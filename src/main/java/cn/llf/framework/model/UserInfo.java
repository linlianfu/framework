package cn.llf.framework.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

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

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_age")
    private int userAge;

    @Column(name = "user_salary")
    private BigDecimal salary;


}
