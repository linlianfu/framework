//package cn.llf.framework.gateway.interceptor;
//
//import cn.llf.framework.model.mybatis.UserInfoPO;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.convert.converter.Converter;
//
//import java.util.List;
//
///**
// * @author eleven
// * @date 2018/11/4
// * @description
// */
//@Slf4j
//public class JsonArrayToListConverter implements Converter<JSONArray,List<UserInfoPO>> {
//
//    @Override
//    public List<UserInfoPO> convert(JSONArray source) {
//        log.info(source.toJSONString());
//        return JSONObject.parseArray(JSON.toJSONString(source), UserInfoPO.class);
//    }
//}
