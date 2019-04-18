package cn.llf.framework.model.mongo;

import cn.eleven.common.bean.superbean.PersistentBean;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author eleven
 * @date 2019/4/18
 * @description
 */
@Data
@Document(collection = "commoditySku")
public class CommoditySku implements PersistentBean {

    private String name;

    private List<SkuProperty> propertyList;
}
