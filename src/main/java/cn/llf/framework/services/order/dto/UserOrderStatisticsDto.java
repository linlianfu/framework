package cn.llf.framework.services.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: eleven
 * @date: 2018/8/28 21:40
 * @description: 用户订单统计
 */
@Data
public class UserOrderStatisticsDto implements Serializable {
    /**
     * 用户id
     */
    private String id;
    /**
     * 购买的订单数量
     */
    private List<Integer> count;
    /**
     * 用户订单统计详情
     */
    List<OrderStatisticsDto> list;
}
