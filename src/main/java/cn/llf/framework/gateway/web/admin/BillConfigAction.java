package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.services.bill.BillConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Author：calvin
 * Date:  2017/9/3 0003
 */
@Slf4j
@Controller
@RequestMapping("billConfig")
public class BillConfigAction {
    BillConfigAction(){
        int i = 0;
        log.info("BillConfigAction实例化");
    }
    @Resource(name = "billConfigService")
    BillConfigService billConfigService;

    @ResponseBody
    @RequestMapping("getBillConfig")
    public String getBillConfig(){
        log.info("action调用");
        return billConfigService.getBillConfig("linlf");
    }
}
