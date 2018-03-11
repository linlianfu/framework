package cn.llf.framework.dao.impl.mongo;

import cn.llf.framework.model.mybatis.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import priv_llf_mybatis.dao.BaseMybatisDaoImpl;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@Slf4j
@Repository("userDaoImpl")
public class UserDaoImpl extends BaseMybatisDaoImpl<UserInfo,String> {

}
