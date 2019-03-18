package cn.llf.framework.gateway.web.admin;

import cn.eleven.common.dao.Page;
import cn.llf.framework.model.mybatis.UserInfo;
import cn.llf.framework.services.thread.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;

/**
 * @author eleven
 * @date 2019/3/18
 * @description
 */
@Controller
@RequestMapping("springMultiThreadAction")
public class SpringMultiThreadAction {


    @Autowired
    IStatisticsService statisticsService;
    /**
     * 测试多线程
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "statistics",method = RequestMethod.GET)
    public boolean statistics(){
        return true;
    }
    /**
     * 测试多线程
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public Page page(){
        Page page = new Page(1,10);
        UserInfo userInfo = new UserInfo();
        userInfo.setIdentity("35051132");
        page.setCurrentPageData(Collections.singleton(userInfo));
        return page;
    }
    /**
     * 测试多线程
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "statisticsUser",method = RequestMethod.GET)
    public UserInfo statisticsUser(){
        UserInfo result = new UserInfo();
        result.setAge(223);
        return result;
    }
}
