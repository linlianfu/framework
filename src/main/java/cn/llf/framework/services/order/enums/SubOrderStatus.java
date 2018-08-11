package cn.llf.framework.services.order.enums;

import lombok.Getter;

/**
 * @author: eleven
 * @since: 2018/8/11 11:43
 * @description:
 */
public enum SubOrderStatus {
    /**
     * 待付款
     */
    WAIT_PAY("待付款", 1),

    /**
     * 未发货
     */
    NOT_DELIVER("未发货", 2),

    /**
     * 发货中
     */
    DELIVER_ING("发货中", 3),

    /**
     * 已发货
     */
    DELIVERED("已发货", 4),

    /**
     * 已签收
     */
    RECEIVED("已签收", 5),

    /**
     * 已换货
     */
    SWAPED("换货成功", 6),

    /**
     * 退款中
     */
    REFUND_ING("退款中", 7),

    /**
     * 已退货
     */
    RECYCLE_SUCCESS("退货成功", 8),

    /**
     * 已取消
     */
    CANCEL("已取消", 9),

    /**
     * 发货失败
     */
    DELIVERY_FAIL("发货失败",10),
    /**
     * 已退款(指退货并退款)
     */
    REFUND_SUCCESS("退货并退款成功",11),
    /**
     * 退货时商品回收失败
     */
    RECYCLE_FAIL("退货失败",12),
    /**
     * 退货中
     */
    RECYCLE_ING("退货中",13),
    /**
     * 换货单退货中
     */
    SWAP_RECYCLE_ING("换货单原商品退货中",14),
    /**
     * 换货单退货中
     */
    SWAP_RECYCLE_SUCCESS("换货单原商品已退货",15),
    /**
     * 换货单退货中
     */
    SWAP_RECYCLE_FAIL("换货单原商品退货失败",16),
    /**
     * 退货单退款失败
     */
    REFUND_FAIL("退款失败",17),
    /**
     * 退货不退款成功
     */
    ONLY_RECYCLE_SUCCESS("退货不退款成功",18),
    /**
     * 退款不退货成功
     */
    ONLY_REFUND_SUCCESS("退款不退货成功",19);


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

    private SubOrderStatus(String title, int value){
        this.title= title;
        this.value= value;
    }
}
