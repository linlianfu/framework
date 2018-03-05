package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.dao.impl.mongo.UserDaoImpl;
import cn.llf.framework.model.mybatis.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Author：calvin
 * Date: 2017/8/17 0017
 */
@Slf4j
@Controller
@RequestMapping("paper")
public class PaperAction {
    @Autowired
    UserDaoImpl userDaoImpl;
    @RequestMapping("getPaper")
    public String getPaper(){
        SqlSession sqlSession = userDaoImpl.getSqlSession();
        if (sqlSession == null){
            log.info("sqlsession == null");
        }else {
            List<UserInfo> temp = userDaoImpl.selectList("findUser","id",null);
//            Map<String,OrderBillShowDto> orderBillShowDtoMap = orderBillShowDtos.stream().collect(Collectors.toMap(p->p.getOrderNo(), p->p));
//              Map<String,UserInfo> userMap = temp.stream().collect(Collectors.toMap(p->p.getId(),p->p));
//              for (UserInfo userInfo : temp){
//                  log.info(userInfo.toString());
//              }

        }
        return "success";
    }
}
