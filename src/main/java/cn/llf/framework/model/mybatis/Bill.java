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
public class Bill {

    private String id;

    private String no;

    private String state;

    private String objectId;

    private String address;

    private String remark;
}
