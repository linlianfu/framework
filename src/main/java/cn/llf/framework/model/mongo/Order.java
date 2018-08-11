package cn.llf.framework.model.mongo;

import cn.llf.framework.services.order.enums.MasterOrderStatus;
import cn.llf.framework.utils.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: eleven
 * @since: 2018/8/5 16:37
 * @description:
 */
@Data
public class Order implements Serializable{
    /**
     * id
     */
    private String id;
    /**
     * 买家id
     */
    private String buyerId;
    /**
     * 卖家id
     */
    private String sellerId;

    /**
     * 主订单状态
     */
    private MasterOrderStatus status = MasterOrderStatus.TRADE_SUCCESS;
    /**
     * 支付方式 1：线上支付 2：线下支付
     */
    private String payType = "1";
    /**
     * 下单人员所在单位地区ID路径
     */
    private String unitRegionPath;
    /**
     * 子订单集合
     */
    List<SubOrder> subOrderList;
    /**
     * 订单创建时间
     */
    private String date = DateUtil.toString(new Date(),DateUtil.PATTEN_TO_SECOND);
}
