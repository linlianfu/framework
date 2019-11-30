package cn.llf.framework.gateway.web.admin;

import cn.eleven.basic.data.user.south.api.IUserQueryService;
import cn.eleven.basic.data.user.south.api.arg.UserQuery;
import cn.eleven.basic.data.user.south.api.dto.UserBaseDto;
import cn.eleven.common.dao.Page;
import cn.eleven.common.zookeeper.LockFactory;
import cn.llf.framework.gateway.web.admin.dto.UserInfo;
import cn.llf.framework.services.user.south.IUserManagerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: eleven
 * @date: 2018/9/1 21:55
 * @description: 用户管理控制器
 */
@Slf4j
@RestController
@RequestMapping("userManager")
public class UserManagerAction {

    @Autowired
    private IUserQueryService userQueryService;

    @Autowired
    private LockFactory lockFactory;
    @Autowired
    protected IUserManagerService userManagerService;

    @GetMapping("listBase")
    public List<UserBaseDto> listBase(@ModelAttribute UserQuery query){
        InterProcessMutex interProcessMutex = lockFactory.getInterProcessMutex("user");
        try {
            boolean acquire = interProcessMutex.acquire(1, TimeUnit.MINUTES);
            if (acquire){
                Collection<String> participantNodes = interProcessMutex.getParticipantNodes();
                log.warn("{}", participantNodes);
                log.warn(">>>>>name:{},region:{}",query.getName(),query.getRegion());

                List<UserBaseDto> userBaseDtos = userQueryService.listBase(query);
                List<UserBaseDto> secondList = userQueryService.listBase(query);
                List<UserBaseDto> thirdList = userQueryService.listBase(query);
                try {
                    interProcessMutex.release();
                } catch (Exception e) {
                    log.info("释放锁失败");
                    e.printStackTrace();
                }
                return   userQueryService.listBase(query);
            }else {
                log.error(">>>>>等待1分钟后还是没有获得锁，请求失败");
            }
        } catch (Exception e) {
            log.error("获取锁失败");
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    @GetMapping("lock")
    public List<UserBaseDto> lock(@ModelAttribute UserQuery query){
        InterProcessMutex interProcessMutex = lockFactory.getInterProcessMutex("lock");
//        try {
//            interProcessMutex.acquire();
//        } catch (Exception e) {
//            log.error("获取锁失败");
//            e.printStackTrace();
//        }
        log.warn("name:{},region:{}",query.getName(),query.getRegion());

        List<UserBaseDto> userBaseDtos = userQueryService.listBase(query);
        List<UserBaseDto> secondList = userQueryService.listBase(query);
        List<UserBaseDto> thirdList = userQueryService.listBase(query);
//        try {
//            interProcessMutex.release();
//        } catch (Exception e) {
//            log.info("释放锁失败");
//            e.printStackTrace();
//        }
        return   userQueryService.listBase(query);
    }

    @GetMapping("pageUserInfo")
    public Page<UserInfo> pageUserInfo(){
        return userManagerService.pageUserInfo();
    }
}
