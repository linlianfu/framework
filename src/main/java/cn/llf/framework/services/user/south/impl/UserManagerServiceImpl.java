package cn.llf.framework.services.user.south.impl;

import cn.llf.framework.dao.impl.mybatis.UserInfoDao;
import cn.llf.framework.model.mybatis.UserInfoPO;
import cn.llf.framework.services.user.south.IUserManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public UserInfoPO add(UserInfoPO po) {
        po = dao.add(po);
        log.warn("第一天数据增加成功，主键id：{}",po.getId());
        po.setTel(po.getTel()+"-1");
        po = dao.add(po);
        log.warn("第二条数据增加成功，主键id：{}",po.getId());
        return po;
    }


    @Override
    public void deleteByAge(int age) {
        dao.deleteByAge(age);
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
}
