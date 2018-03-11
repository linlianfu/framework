package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.dao.impl.mybatis.BillConfigImpl;
import cn.llf.framework.model.mybatis.OcBillConfig;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 创建者：   linlf
 * 创建时间： 2017/8/21
 * 描述：
 */
@Slf4j
@Controller
@RequestMapping("redisLearningAction")
public class RedisLearningAction {

    @Resource(name = "billConfig")
    BillConfigImpl billConfigImpl;

//    @Resource(name = "jedisConnectionFactory")
    JedisConnectionFactory jedisConnectionFactory;

    Jedis jedis = null;
    @RequestMapping("redisStringOpera")
    public void redisStringOpera(){
        RedisConnection redisConnection =  jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) redisConnection.getNativeConnection();
//        jedis.append("name","work in fjhb");//拼接
//        jedis.del("2133c175d1c940e19a4ce9196f51fa86");// 删除
//        String[] keys =new String[3];
//        keys[0] = "402899725c3d7792015c3d877dcc0006";
//        keys[1] = "6c34a9949dc24b7691efd9ca055cb39b";
//        keys[2] = "1111";
//        Long delCount = jedis.del(keys);
//        log.info("批量删除{}条",delCount);a0c4e389bd4a4436ad00ed1afdcb9a57

//        String statu = jedis.mset("newarea","fuzhou1","company","fjhb1","age","201","name","112");
//        log.info(statu);

//        jedis.set("timeout","10后过期","NX","EX",20);//NX不存在才set，XX存在才set；EX单位为秒，PX单位为毫秒
//        log.info(jedis.get("timeout"));
    }

    @RequestMapping("redisMapOpera")
    public void redisMapOpera() {
        //save data
        jedis = getJedis();
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("type", "myType is hasnMap");
        userInfo.put("name", "calvin");
        userInfo.put("age", "24");
        userInfo.put("salary", "5000");
        jedis.hmset("userInfo",userInfo);


        List<String> name = jedis.hmget("userInfo","name");
        log.info("hmget value："+name.get(0));

        jedis.hdel("userInfo","name");
        Set<String> keys = jedis.hkeys("userInfo");
        for (String str : keys){
            log.info("keys:"+str);
        }
    }

    @RequestMapping("redisListOpera")
    public void redisListOpera() {
        //save data
        jedis = getJedis();
        jedis.lpush("userName","myType is list");
        jedis.lpush("userName","伟恒");
        jedis.lpush("userName","B哥");
        jedis.lpush("userName","calvin");
        log.info(jedis.lrange("userName",0,2).toString());
    }

    @RequestMapping("redisSetOpera")
    public void redisSetOpera() {
        //save data
        jedis = getJedis();
        jedis.sadd("user","mytype is set");
        jedis.sadd("user","liuling");
        jedis.sadd("user","xinxin");
        jedis.sadd("user","ling");
        jedis.sadd("user","zhangxinxin");
        jedis.sadd("user","who");
        jedis.srem("user","who");//删除set 元素
//            jedis.incr("name");
        log.info(jedis.smembers("user").toString());
    }

    @RequestMapping("redisExpireOpera")
    public void redisExpireOpera() {
        jedis = getJedis();
//        jedis.expire("newarea",10);
//
//        log.info("name的生存时间：{}"+jedis.persist("name").toString());
//        jedis.expire("name",300);
//        log.info("name的生存时间：{}"+jedis.("name").toString());
//        log.info("name的剩余生存时间：{}"+jedis.ttl("name").toString());
//        jedis.persist("name");//移除key的生存时间
//        log.info("name的剩余生存时间：{}"+jedis.ttl("name").toString());

        log.info("查看key的类型{}",jedis.type("user"));
        log.info("查看key的类型{}",jedis.type("userInfo"));
        log.info("查看key的类型{}",jedis.type("userName"));
        log.info("查看key的类型{}",jedis.type("area"));

    }


    @RequestMapping("saveBillConfigToRedis")
    public void saveBillConfigToRedis(){
        List<OcBillConfig> result = billConfigImpl.selectList("findBillConfigSimple","OBC_ID",null);

        RedisConnection redisConnection =  jedisConnectionFactory.getConnection();
        Jedis jedis = (Jedis) redisConnection.getNativeConnection();
        if (result!=null && result.size()>0){
            for (OcBillConfig ocBillConfig :result){
                String saveStatu =  jedis.set(ocBillConfig.getId(), JSONObject.toJSONString(ocBillConfig));
                log.info("保存结果saveStatu:{}",saveStatu);
            }
        }
    }

    @RequestMapping("redis")
    public  void redis(){
        if (jedisConnectionFactory == null){
            log.info("redis配置失败");
        }else {
            log.info("redis配置成功");

            RedisConnection redisConnection =  jedisConnectionFactory.getConnection();
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
        }
    }


    public Jedis getJedis(){
        if (jedis == null){
            RedisConnection redisConnection =  jedisConnectionFactory.getConnection();
            jedis = (Jedis) redisConnection.getNativeConnection();
            return jedis;
        }else {
            return jedis;
        }
    }
}
