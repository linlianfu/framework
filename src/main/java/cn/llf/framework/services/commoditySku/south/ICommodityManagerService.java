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
     * 根据sku属性值匹配
     * @see note.youdao.com mongodb nosql使用
     * <code>对应的query执行语句</code>
     * <code>
     *
     * db.commoditySku.find(
         {
             "propertyList":{
                 "$all":[
                     {
                         "$elemMatch":{
                             "propertyId":"year",
                             "value":"2018"
                         }
                     },
                     {
                         "$elemMatch":{
                             "propertyId":"subject",
                             "value":"public"
                         }
                     }
                 ]
             }
         }
        )
     * </code>
     */
    List<CommoditySku> listBySkuProperty();
}
