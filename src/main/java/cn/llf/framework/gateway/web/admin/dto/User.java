package cn.llf.framework.gateway.web.admin.dto;

import lombok.Data;

/**
 * @author eleven
 * @date 2019/12/1
 * @description
 */
@Data
public class User {

    private String id;
    private String username;
    private String password;
    private int age;
}
