package cn.llf.framework.dao.impl;

import cn.llf.framework.dao.dto.UserInfo;
import cn.llf.mybatis.dao.impl.BaseMybatisDaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@Slf4j
@Repository("userDaoImpl")
public class UserDaoImpl extends BaseMybatisDaoImpl<UserInfo,String> {

}
