package cn.llf.framework.model.mongo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

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
     * 类型
     */
    private int type;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 状态
     */
    private String status;
}
