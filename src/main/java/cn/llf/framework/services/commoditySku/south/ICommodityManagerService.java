package cn.llf.framework.services.commoditySku.south;

import cn.llf.framework.model.mongo.CommoditySku;

import java.util.List;

/**
 * @author eleven
 * @date 2019/4/18
 * @description
 */

public interface ICommodityManagerService {

    /**
     * 根据sku属性值多汁匹配
     */
    List<CommoditySku> listBySkuProperty();
}
