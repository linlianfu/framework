package cn.llf.framework.services.order.args;

import cn.llf.framework.QueryBean;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * @author: eleven
 * @date: 2018/8/31 22:57
 * @description: 随机取样条件
 */
@Data
public class SampleQuery implements QueryBean {
    /**
     * 买家id
     */
    private String buyerId;
    /**
     * 抽取数量
     */
    @Range(max = 10,min = 1,message = "随机取样数介于1-10")
    private int count;
}
