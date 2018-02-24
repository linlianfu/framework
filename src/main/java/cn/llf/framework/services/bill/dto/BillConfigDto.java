package cn.llf.framework.services.bill.dto;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * 创建者：   linlf
 * 创建时间： 2018/2/24
 * 描述：
 */
@Data
@Builder
public class BillConfigDto {
    /**
     * id
     */
    private String billConfigId;
    /**
     * 发票代码
     */
    private String code;
    /**
     * 发票类型
     */
    private int billType;
}
