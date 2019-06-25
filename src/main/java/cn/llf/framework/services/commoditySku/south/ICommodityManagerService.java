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
     * 根据sku属性值匹配,sku之间的匹配模式 and的关系
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
     * @return 2018年公需科目的商品
     */

    List<CommoditySku> listBySkuProperty();


    /**
     * 根据sku属性值匹配,sku之间的匹配模式 or的关系
     *
      {
         "propertyList":{
             "$in":[
                 {
                     "propertyId":"year",
                     "value":"2021"
                 },
                 {
                     "propertyId":"subject",
                     "value":"public"
                 }
             ]
         }
     }
     * @return: 2021年或者公需科目的商品
     */
    List<CommoditySku> lisCommodityBySkuOr();

}
