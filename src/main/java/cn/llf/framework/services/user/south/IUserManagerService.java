package cn.llf.framework.services.user.south;

import cn.llf.framework.model.mybatis.UserInfoPO;

import java.util.List;

/**
 * @author eleven
 * @date 2018/10/28
 * @description
 */
public interface IUserManagerService {

    /**
     * 添加用户
     * @param po
     * @return
     */
    UserInfoPO add(UserInfoPO po);

    /**
     * 批量添加用户，测试基于AOP的事务作用,AOP切面事务匹配的是service层save开头的方法
     * @param list
     * @return
     */
    List<UserInfoPO> saveUserBatchByAOPTransactionManager(List<UserInfoPO> list);
    /**
     * 批量添加用户，测试基于AOP的事务不对非save开头的方法起作用,AOP切面事务匹配的是service层save开头的方法
     * @param list
     * @return
     */
    List<UserInfoPO> excludeByAOPTransactionManagerSaveUserBatch(List<UserInfoPO> list);

    /**
     * 删除用户
     * @param age
     */
    void deleteByAge(int age);
    /**
     * 删除用户
     * @param identity
     */
    void deleteByIdentity(String identity);

    /**
     * 更新用户
     * @param entity
     */
    void update(UserInfoPO entity);

    /**
     * 用户查询
     * @return
     */
    List<UserInfoPO> list(UserInfoPO query);

    /**
     * 模拟死锁的sessionA
     * 1.先删除指定年龄的数据，获取年龄的间隙锁
     * 2.休息5s，等待sessionB请求添加数据，获取指定电话的行锁
     * 3.添加和sessionB一样电话为B的数据，模拟sessionA进入锁等待状态，等待sessionB占用锁
     * @param deleteAge
     * @param entity
     * @return
     */
    UserInfoPO simulationDeadLockSessionA(int deleteAge,UserInfoPO entity);
    /**
     * 模拟死锁的sessionB
     * 1.sessionA删除数据之后，发起该请求，请求添加的电话数据假设为 B；
     * 2.休息5s，等待sessionA执行第三步骤；
     * 3.执行添加第二条数据，第二条数据年龄必须在sessionA第一步获取到的间隙范围内，此时应该抛出死锁异常
     * @param entityList
     * @return
     */
    UserInfoPO simulationDeadLockSessionB(List<UserInfoPO> entityList);

}
