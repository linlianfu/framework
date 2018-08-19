package cn.llf.framework.model.mongo;

import cn.llf.framework.services.order.enums.CategoryType;
import cn.llf.framework.services.order.enums.SubOrderStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author: eleven
 * @since: 2018/8/5 16:37
 * @description:
 */
@Data
public class SubOrder implements Serializable {
    /**
     * 子订单id
     */
    private String id;
    /**
     * 商品id
     */
    private String productId = UUID.randomUUID().toString().replaceAll("","");

    /**
     * 商品名字
     */
    private String productName = "线下培训班";
    /**
     * 购买数量
     */
    private int purchaseQuantity = 3;
    /**
     * 成交单价
     */
    private double dealPrice = 25;
    /**
     * 实付总价
     */
    private double totalAmount = 75;
    /**
     * 子订单状态
     */
    private SubOrderStatus status = SubOrderStatus.RECEIVED;
    /**
     * 物品所属类型
     */
    private CategoryType type = CategoryType.BOOK;
}
