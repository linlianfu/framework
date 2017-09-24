package cn.llf.framework.services.bill.impl;

import cn.llf.framework.annotation.ImportField;
import cn.llf.framework.services.bill.BillConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Author：calvin
 * Date:  2017/9/3 0003
 */
@Slf4j
@Service("billConfigService")
public class BillConfigServiceImpl implements BillConfigService {
    @Override
    public String getBillConfig(String name) {
        log.info(">>>>>获取发票配置service层输入");
        return "提供发票";
    }
}
