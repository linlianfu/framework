package cn.llf.framework.services.bill.south;

import org.springframework.validation.annotation.Validated;

/**
 * Author：calvin
 * Date:  2017/9/3 0003
 */
@Validated
public interface BillConfigService {
    /**
     * 获取发票配置
     * @return
     */
    String getBillConfig(String name);
}
