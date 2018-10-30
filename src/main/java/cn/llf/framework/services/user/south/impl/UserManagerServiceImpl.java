package cn.llf.framework.services.user.south.impl;

import cn.eleven.common.except.BasicRuntimeException;
import cn.llf.framework.dao.impl.mybatis.UserInfoDao;
import cn.llf.framework.model.mybatis.UserInfoPO;
import cn.llf.framework.services.user.south.IUserManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserInfoPO add(UserInfoPO po) {
        po = dao.add(po);
        log.warn("第一天数据增加成功，主键id：{}",po.getId());
        po.setTel(po.getTel()+"-1");
        if (po.getTel().contains("-1"))
            throw new BasicRuntimeException("测试异常抛出");
        po = dao.add(po);
        log.warn("第二条数据增加成功，主键id：{}",po.getId());
        return po;
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
    public void update(UserInfoPO entity) {

        dao.update(entity);
    }

    @Override
    public List<UserInfoPO> list(UserInfoPO query) {
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
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.warn("sessionA执行完成， 等待5S，等待sessionB执行到第二部，等待死锁+");
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
        userInfoPO.setTel("89");
        userInfoPO.setAge(16);
        userInfoPO.setName("temp");
        dao.add(userInfoPO);
        return userInfoPO;
    }
}
