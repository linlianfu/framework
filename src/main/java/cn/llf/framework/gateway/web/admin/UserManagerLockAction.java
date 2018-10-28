package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.model.mybatis.UserInfoPO;
import cn.llf.framework.services.user.south.IUserManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author eleven
 * @date 2018/10/28
 * @description 测试mysql事务和锁
 */
@Slf4j
@RestController
@RequestMapping("userManagerLock")
public class UserManagerLockAction {

    @Autowired
    IUserManagerService service;


    @PostMapping(value = "add")
    public UserInfoPO add(@RequestBody UserInfoPO form){
         return service.add(form);
    }
    @GetMapping(value = "deleteByAge")
    public void deleteByAge(int age){
        service.deleteByAge(age);
    }
    @PostMapping(value = "update")
    public void update(@RequestBody UserInfoPO form){
        service.update(form);
    }
    @GetMapping(value = "list")
    public List<UserInfoPO> list(@ModelAttribute UserInfoPO form){
        return service.list(form);
    }

}
