package cn.llf.framework.model.mybatis;

import cn.eleven.common.bean.superbean.PersistentBean;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author eleven
 * @date 2018/10/28
 * @description  用户信息持久化的bean，测试mysql锁
 */
@Data
@Alias("userInfoPo")
public class UserInfoPO implements PersistentBean {
    /**
     * 主键id
     */
    private String id;
    /**
     * 用户名字
     */
    private String name;
    /**
     * 用户年龄
     */
    private int age;
    /**
     * 用户身份证
     */
    private String identity;
    /**
     * 数据库选择key
     */
    private int dataSourceKey;


}
