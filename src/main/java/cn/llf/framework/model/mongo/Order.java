package cn.llf.framework.model.mongo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: eleven
 * @since: 2018/8/5 16:37
 * @description:
 */
@Data
public class Order implements Serializable{
    /**
     * id
     */

    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 子订单集合
     */
    List<SubOrder> subOrderList;
}
