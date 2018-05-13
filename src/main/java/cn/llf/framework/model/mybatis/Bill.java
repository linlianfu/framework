package cn.llf.framework.model.mybatis;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author: eleven
 * @since: 2018/3/10 21:38
 * @description:
 */
@Data
@Alias("bill")
public class Bill implements Comparable<Bill>{

    private String id;

    private String no;

    private String state;

    private String objectId;

    private String address;

    private String remark;
    private int temp;


    @Override
    public int compareTo(Bill o) {
        return this.getTemp() == o.getTemp()? 0 : this.temp > o.getTemp() ? 1 : -1;
    }
}
