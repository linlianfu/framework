package cn.llf.framework.services.order.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: eleven
 * @date: 2018/8/25 11:27
 * @description: 聚合买家的订单信息
 */
@Data
public class AggregateBuyerOrderInfo implements Serializable {
    /**
     * 买家id
     */
    private String id;
    /**
     * 买家购买的订单数
     */
    private int count;
    /**
     * 买家的订单总金额
     */
    private double totalAmount;
    /**
     * 买家订单平均金额
     */
    private double avg;
}
