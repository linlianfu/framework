package cn.llf.framework.services.order.args;

import cn.llf.framework.QueryBean;
import cn.llf.framework.services.order.enums.MasterOrderStatus;
import lombok.Data;

/**
 * @author: eleven
 * @date: 2018/8/25 11:29
 * @description: 买家订单信息查询参数
 */
@Data
public class AggregateQuery implements QueryBean {
    /**
     * 买家id
     */
    private String buyerId;
    /**
     * 统计单位地区
     */
    private String unitRegionPath;
    /**
     * 主订单交易状态
     */
    private MasterOrderStatus masterOrderStatus;
}
