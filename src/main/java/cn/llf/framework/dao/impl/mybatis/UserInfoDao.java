package cn.llf.framework.dao.impl.mybatis;

import cn.eleven.common.utils.CheckUtls;
import cn.llf.framework.model.mybatis.UserInfoPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import priv.llf.mybatis.dao.BaseMybatisDaoImpl;

import java.util.List;

/**
 * @author eleven
 * @date 2018/10/28
 * @description
 */
@Slf4j
@Repository("userInfoDao")
public class UserInfoDao extends BaseMybatisDaoImpl<String,UserInfoPO> {



    public UserInfoPO add(UserInfoPO entity){
        getSqlSession().insert("add", entity);
        log.info("插入返回的主键id：{}",entity.getId());
        return entity;
    }

    public void deleteByAge(int age){
        int deleteCount = getSqlSession().delete("deleteByAge", age);
        log.info("delete rowCount:{}",deleteCount);

    }
    public void deleteByTel(String tel){
        CheckUtls.notBlank(tel,"电话删除条件不能为空");
        int deleteCount = getSqlSession().delete("deleteByTel", tel);
        log.info("delete rowCount:{}",deleteCount);
    }

    public void update(UserInfoPO entity){
        int update = getSqlSession().update("updateByAge", entity);
        log.info("update total count :{}",update);
    }

    public List<UserInfoPO> list(UserInfoPO query){
        List<UserInfoPO> list  = getSqlSession().selectList("list",query);
        return list;
    }
}
