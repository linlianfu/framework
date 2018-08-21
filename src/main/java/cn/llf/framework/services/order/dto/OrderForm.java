package cn.llf.framework.services.order.dto;

import cn.llf.framework.FormBean;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: eleven
 * @date: 2018/8/21 21:51
 * @description:
 */
@Data
public class OrderForm implements FormBean{
    /**
     * 书包购买数量
     */
    @NotNull(message = "请输入书包购买数量")
    private Integer bagCount;
    /**
     * 书本购买数量
     */
    private Integer bookCount;
    /**
     * 铅笔购买数量
     */
    private Integer pencilCount;
    /**
     * 其他购买数量
     */
    private Integer otherCount;
}
