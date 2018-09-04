package cn.llf.framework.gateway.web.admin;

import cn.eleven.basic.data.user.south.api.IUserQueryService;
import cn.eleven.basic.data.user.south.api.arg.UserQuery;
import cn.eleven.basic.data.user.south.api.dto.UserBaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author: eleven
 * @date: 2018/9/1 21:55
 * @description: 用户管理控制器
 */
@RestController
@RequestMapping("userManager")
public class UserManagerAction {

    @Autowired
    IUserQueryService userQueryService;

    @RequestMapping("listBase")
    public List<UserBaseDto> listBase(UserQuery query){
      return   Collections.emptyList();
    }
}
