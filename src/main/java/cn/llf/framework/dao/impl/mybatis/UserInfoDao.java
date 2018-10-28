package cn.llf.framework.dao.impl.mybatis;

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
        int delete = getSqlSession().delete("deleteByAge", age);
        if (delete > 0 )
            log.info("delete success");
        else

            log.info("delete failure");
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
