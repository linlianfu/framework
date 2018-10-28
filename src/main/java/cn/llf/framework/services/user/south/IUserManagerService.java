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
     * 删除用户
     * @param age
     */
    void deleteByAge(int age);

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
}
