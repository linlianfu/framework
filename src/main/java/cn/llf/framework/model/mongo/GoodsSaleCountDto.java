package cn.llf.framework.model.mongo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: eleven
 * @since: 2018/8/9 20:23
 * @description:
 */
@Data
public class GoodsSaleCountDto {
    /**
     * 物品类目
     */
    private String type;
    /**
     * 销售数量
     */
    private int purchaseQuantity;
    /**
     * 销售金额
     */
    private BigDecimal totalAmount;
}
