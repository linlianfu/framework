package cn.llf.framework.services.user.dto;

import lombok.Data;

/**
 * @author eleven
 * @date 2019/12/7
 * @description  graphql查询用户的参数
 */
@Data
public class GraphQLUserForm {

    /**
     *分页参数
     */
    private int pageNo;
    /**
     *分页参数
     */
    private int pageSize;
    /**
     * 用户名称
     */
    private String userName;
}
