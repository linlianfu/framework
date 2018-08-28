package cn.llf.framework.model.mongo;

import cn.llf.framework.DataBaseBean;
import cn.llf.framework.services.order.enums.MasterOrderStatus;
import cn.llf.framework.utils.DateUtil;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @author: eleven
 * @since: 2018/8/5 16:37
 * @description: 订单mongo持久化对象
 */
@Data
@Document(collection = "order")
public class Order implements DataBaseBean{
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
    /**
     * 主订单总金额
     */
    private double totalAmount;
    /**
     * 订单说明
     */
    private String marker;

    public void setTotalAmount(){
        double totalAmount = 0;
        for (SubOrder subOrder : subOrderList) {
            totalAmount += subOrder.getTotalAmount();
        }
        this.totalAmount = totalAmount;

    }
}
