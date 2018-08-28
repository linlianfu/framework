package cn.llf.framework.services.order.dto;

import cn.llf.framework.DataBaseBean;
import cn.llf.framework.services.order.enums.CategoryType;
import lombok.Data;

/**
 * @author: eleven
 * @date: 2018/8/28 21:34
 * @description: 订单统计对象
 */
@Data
public class OrderStatisticsDto implements DataBaseBean {
    /**
     * 类目
     */
    private CategoryType type;
    /**
     * 数量
     */
    private int count;
    /**
     * 总金额
     */
    private double totalAmount;
}
