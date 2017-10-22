package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.dao.dto.UserInfo;
import cn.llf.framework.dao.mapper.UserInfoMapper;
import cn.llf.framework.model.Paper;
import cn.llf.mybatis.dao.impl.BaseMybatisDaoImpl;
import cn.llf.mybatis.support.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author：calvin
 * Date: 2017/8/17 0017.
 */
@Slf4j
@Controller
@RequestMapping("userInfo")
public class UserInfoAction {
    @Autowired
    BaseMybatisDaoImpl baseMybatisDao;
    @RequestMapping("listUserInfo")
    public void listUserInfo(){

        log.info("获取人员信息");
        SqlSession sqlSession =  baseMybatisDao.getSqlSession();
        UserInfoMapper userInfoMapper =sqlSession.getMapper(UserInfoMapper.class);
        Assert.notNull(userInfoMapper,"mapper 为空");
        //获取所有人员信息
        List<UserInfo> resultList = userInfoMapper.findAll(new UserInfo());
        for (UserInfo userInfo:resultList){
            log.info("all:"+userInfo.toString());
        }
//        //条件搜索
        UserInfo condition = new UserInfo();
        condition.setUserName("林连福");
        condition.setUserAge(24);
        log.info(condition.toString());
        List<UserInfo> resultList1 = userInfoMapper.findByCondition(condition);
        log.info("符合条件的共有【{}】条",resultList1.size());
        for (UserInfo userInfo:resultList1){
            log.info("condition:"+userInfo.toString());
        }

//        保存数据
//        UserInfo entity = new UserInfo();
//        entity.setUserName("林连福");
//        entity.setUserAge(24);
//        entity.setSalary(new BigDecimal(4600));
//        log.info(entity.toString());
//        int total = userInfoMapper.save(entity);
//        log.info("成功保存【{}】条",total);



//      更新数据
//        UserInfo updateInfo = new UserInfo();
//        updateInfo.setUserAge(20);
//        updateInfo.setSalary(new BigDecimal(3306));
////        updateInfo.setUserName("小巧流水");
//        updateInfo.setId("6fcb0d0abcee4679bda8fa6ac6c7314d");
//        log.info(updateInfo.toString());
//        int total = userInfoMapper.upDate(updateInfo);
//        log.info("成功更新【{}】条",total);


//       删除数据
//        UserInfo deleteInfo = new UserInfo();
//        deleteInfo.setId("3120210122");
//        log.info(deleteInfo.toString());
//        int total = userInfoMapper.deleteById(deleteInfo);
//        log.info("成功删除【{}】条",total);

//       统计数据
//        UserInfo countInfo = new UserInfo();
//        log.info(countInfo.toString());
//        int total = userInfoMapper.count(countInfo);
//        log.info(">>>>>统计结果->total：【{}】条",total);


        // 分页搜索
        UserInfo pageUserInfo = new UserInfo();

        Page page = new Page();
        page.setPageNo(3);
        page.setPageSize(2);
        log.info(">>>>>入参："+pageUserInfo.toString());
        List<UserInfo> resultList2 = userInfoMapper.findPage(pageUserInfo,page);
        log.info(">>>>>符合分页条件的共有【{}】条",resultList2.size());
        for (UserInfo userInfo:resultList1){
            log.info(">>>>>pageUserInfo:"+userInfo.toString());
        }
    }


    public static void main(String[] arg){
        System.out.println(Paper.LIBRARY.name());
        UserInfo u1 = new UserInfo();
        u1.setUserName("林连福");
        u1.setId("31202120");
        u1.setUserAge(20);
        u1.setSalary(new BigDecimal(2000));
        UserInfo u2 = new UserInfo();
        u2.setUserName("陈任");
        u2.setId("123456789");
        u2.setUserAge(50);
        u2.setSalary(new BigDecimal(5002));
        List<UserInfo> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        List<String> userNameList =  list.stream().map(UserInfo::getUserName).collect(Collectors.toList());
        for (String s : userNameList) {
            System.out.println(s);
            System.out.println(s);
        }
        Map<String,UserInfo> userMap = list.stream().collect(Collectors.toMap(p->p.getUserName(),p->p));
        System.out.println(userMap.get("林连福")+".....");
    }

}
