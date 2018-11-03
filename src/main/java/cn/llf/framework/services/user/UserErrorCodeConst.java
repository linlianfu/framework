package cn.llf.framework.services.user;


import cn.eleven.common.exception.ErrCodeConstant;

/**
 * @author eleven
 * @date 2018/11/3
 * @description 用户模块的一异常code
 */
public class UserErrorCodeConst extends ErrCodeConstant {


    private UserErrorCodeConst(String code, String message) {
        super(code, message);
    }

    public static final UserErrorCodeConst ILLEAGE_ERROR_CODE = new UserErrorCodeConst("400","请求非法-用户模块");
}
