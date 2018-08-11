package cn.llf.framework.services.order.enums;

import lombok.Getter;

/**
 * @author: eleven
 * @since: 2018/8/11 11:42
 * @description:
 */
public enum MasterOrderStatus {
    /**
     * 等待付款
     */
    WAITING_FOR_PAYMENT("等待付款", 1),

    /**
     * 等待卖家确认款项
     */
    WAITING_FOR_SELLER_AFFIRM("等待卖家确认款项", 2),

    /**
     * 支付成功
     */
    PAY_SUCCESS("支付成功", 3),

    /**
     * 部分发货
     */
    DELIVER_PART("部分发货", 4),

    /**
     * 卖家已发货
     */
    DELIVER_COMPLETE("卖家已发货", 5),

    /**
     * 交易成功
     */
    TRADE_SUCCESS("交易成功", 6),

    /**
     * 交易关闭
     */
    TRADE_CLOSE("交易关闭", 7),

    /**
     * 支付中
     */
    PAY_IN("支付中",8);


    /**
     * 类别文本
     */
    @Getter
    private String title;

    /**
     * 类别值
     */
    @Getter
    private int value;

    private MasterOrderStatus(String title, int value){
        this.title= title;
        this.value= value;
    }
}
