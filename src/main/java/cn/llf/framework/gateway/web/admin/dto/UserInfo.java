package cn.llf.framework.gateway.web.admin.dto;

import cn.eleven.common.bean.superbean.ResponseBean;
import lombok.Data;

/**
 * @author eleven
 * @date 2019/11/30
 * @description
 */
@Data
public class UserInfo implements ResponseBean {

    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String name;
    /**
     * 身份证号
     */
    private String identity;
    /**
     * 性别
     */
    private int sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 地区
     */
    private String region;
    /**
     * 单位名称
     */
    private String unitName;
}
