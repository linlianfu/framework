package cn.llf.framework.services.user.south.impl;

import cn.eleven.common.dao.Page;
import cn.eleven.common.exception.BasicRuntimeException;
import cn.llf.framework.annotation.MethodInvocationStatistic;
import cn.llf.framework.dao.impl.mybatis.UserInfoDao;
import cn.llf.framework.gateway.web.admin.dto.UserInfo;
import cn.llf.framework.model.mybatis.UserInfoPO;
import cn.llf.framework.services.user.UserErrorCodeConst;
import cn.llf.framework.services.user.south.IUserManagerService;
import cn.llf.jdbc.dataSource.MultipleDataSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author eleven
 * @date 2018/10/28
 * @description
 */
@Slf4j
@Service("userManagerService")
public class UserManagerServiceImpl implements IUserManagerService {

    @Autowired
    UserInfoDao dao;
    @Autowired
    PlatformTransactionManager platformTransactionManager;



    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserInfoPO add(UserInfoPO po) {
        po = dao.add(po);
        log.warn("第一天数据增加成功，主键id：{}",po.getId());
//        po.setIdentity(po.getIdentity()+"-1");
//        if (po.getIdentity().contains("-1"))
//            throw new BasicRuntimeException("测试异常抛出");
//        po = dao.add(po);
//        log.warn("第二条数据增加成功，主键id：{}",po.getId());
        return po;
    }

    @Override
    public List<UserInfoPO> saveUserBatchByAOPTransactionManager(List<UserInfoPO> list) {
        if (CollectionUtils.isNotEmpty(list)){
            list.forEach(p-> {
                if (p.getIdentity().contains("1111")){
                    throw new BasicRuntimeException(UserErrorCodeConst.ILLEAGE_ERROR_CODE);
                }
                UserInfoPO add = dao.add(p);
                log.warn("添加成功的主键id：{}",add.getId());
            });
        }
        return list;
    }
    @Override
    public List<UserInfoPO> excludeByAOPTransactionManagerSaveUserBatch(List<UserInfoPO> list) {
        return saveUserBatchByAOPTransactionManager(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteByAge(int age) {
        dao.deleteByAge(age);
        log.warn("删除的线程等待延时60S");
        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.warn("删除的线程延时结束，提交事务");
    }

    @Override
    public void deleteByIdentity(String identity) {
        dao.deleteByIdentity(identity);
    }

    @Override
    public void update(UserInfoPO entity) {

        dao.update(entity);
    }

    @Override
    @MethodInvocationStatistic(methodName = "获取用户分页数据")
    public List<UserInfoPO> list(@ModelAttribute UserInfoPO query) {
        MultipleDataSourceConfig.setDataSourceKey(query.getDataSourceKey());
        List<UserInfoPO> list = dao.list(query);
        return list;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserInfoPO simulationDeadLockSessionA(int deleteAge,UserInfoPO entity){
        dao.deleteByAge(deleteAge);
        log.warn("sessionA进入睡眠5S，模拟业务处理耗时");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.warn("sessionA睡眠结束，开始执行add操作");

        dao.add(entity);
        log.warn("sessionA执行完成， 等待5S，等待sessionB执行到第二部，等待死锁+");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.warn("sessionA请求结束");
        return entity;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserInfoPO simulationDeadLockSessionB(List<UserInfoPO> entityList) {

        dao.add(entityList.get(0));
        log.warn("sessionB进入睡眠5S，等待sessionA执行第二步骤");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.warn("sessionB睡眠结束，开始执行第二条的add操作");
        UserInfoPO userInfoPO = new UserInfoPO();
        userInfoPO.setIdentity("87888");
        userInfoPO.setAge(16);
        userInfoPO.setName("temp");
        dao.add(userInfoPO);
        return userInfoPO;
    }


    @Override
    public UserInfoPO memory(String id) {
        UserInfoPO po = new UserInfoPO();
        po.setId(id);
        po.setAge(234);
        po.setIdentity("350524192121");
        po.setName("eleven");
        return po;
    }


    @Override
    public Page<UserInfo> pageUserInfo() {
        Page<UserInfo> page = new Page();
        List<UserInfo> list = new ArrayList<>();
        page.setCurrentPageData(list);

        for (int i = 0;i<=10;i++){
            UserInfo userInfo = new UserInfo();
            userInfo.setName("eleven");
            userInfo.setIdentity("35052419920626201"+i);
            userInfo.setSex(i%2);
            userInfo.setRegion("福建省-泉州市-安溪县");
            userInfo.setUnitName("华博教育");
            userInfo.setPhone("18060614807");
            list.add(userInfo);
        }
        return page;
    }


    @Override
    public List<UserInfo> graphQLListUserInfo(int count) {
        List<UserInfo> list = new ArrayList<>();
        for (;count>0;count--){
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(count+"");
            userInfo.setIdentity("35052419920626201"+count);
            userInfo.setName("eleven");
            userInfo.setSex(count%2);
            userInfo.setRegion("福建省-泉州市-安溪县");
            userInfo.setUnitName("华博教育");
            userInfo.setPhone("18060614807");
            list.add(userInfo);
        }
        return list;
    }
}
