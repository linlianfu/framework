package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.model.mybatis.UserInfoPO;
import cn.llf.framework.services.user.south.IUserManagerService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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

    /**
     * 批量保存用户数据。测试AOP事务
     * @param list
     * @return
     */
    @PostMapping(value = "saveBatch")
    public List<UserInfoPO> saveBatch(@RequestBody List<UserInfoPO> list){
        return service.saveUserBatchByAOPTransactionManager(JSON.parseArray(JSON.toJSONString(list), UserInfoPO.class));
    }
    /**
     * 批量保存用户数据。测试AOP事务不对非save开头的方法起作用
     * @param list
     * @return
     */
    @PostMapping(value = "excludeByAOPTransactionManagerSaveUserBatch")
    public List<UserInfoPO> excludeByAOPTransactionManagerSaveUserBatch(@RequestBody List<UserInfoPO> list){
        return service.excludeByAOPTransactionManagerSaveUserBatch(JSON.parseArray(JSON.toJSONString(list), UserInfoPO.class));
    }

    @GetMapping(value = "deleteByAge")
    public void deleteByAge(int age){
        service.deleteByAge(age);
    }
    @DeleteMapping(value = "deleteByIdentity")
    public void deleteByIdentity(String identity){
        service.deleteByIdentity(identity);
    }
    @PutMapping(value = "update")
    public void update(@RequestBody UserInfoPO form){
        service.update(form);
    }
    @GetMapping(value = "list")
    public List<UserInfoPO> list(@ModelAttribute UserInfoPO form){
        return service.list(form);
    }

    /**
     * 模拟死锁的sessionA
     * 1.先删除指定年龄的数据，获取年龄的间隙锁
     * 2.休息5s，等待sessionB请求添加数据，获取指定电话的行锁
     * 3.添加和sessionB一样电话为B的数据，模拟sessionA进入锁等待状态，等待sessionB占用锁
     * 4.执行完后等待5S，等待sessionB也执行到最后，观察死锁
     * @param deleteAge
     * @param entity
     * @return
     */
    @GetMapping(value = "simulationDeadLockSessionA")
    public UserInfoPO simulationDeadLockSessionA(int deleteAge,@ModelAttribute UserInfoPO entity){
        return service.simulationDeadLockSessionA(deleteAge,entity);
    }

    /**
     * 模拟死锁的sessionB
     * 1.sessionA删除数据之后，发起该请求，请求添加的电话数据假设为 B；
     * 2.休息5s，等待sessionA执行第三步骤；
     * 3.执行添加第二条数据，第二条数据年龄必须在sessionA第一步获取到的间隙范围内，此时应该抛出死锁异常
     * @param entityList
     * @return
     */
    @PostMapping(value = "simulationDeadLockSessionB")
    public UserInfoPO simulationDeadLockSessionB(@RequestBody UserInfoPO entityList){
        // TODO: 2018/10/30  数组接收不到？
        return service.simulationDeadLockSessionB(Collections.singletonList(entityList));
    }


}
