package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.services.thread.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eleven
 * @date 2019/3/18
 * @description
 */
@RestController
@RequestMapping("springMultiThreadAction")
public class SpringMultiThreadAction {


    @Autowired
    IStatisticsService statisticsService;
    /**
     * 测试多线程
     * @return
     */
    @GetMapping(value = "statistics")
    public String statistics(){
        return statisticsService.print("test");
    }
}
