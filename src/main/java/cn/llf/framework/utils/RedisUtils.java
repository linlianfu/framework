package cn.llf.framework.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * 创建者：   linlf
 * 创建时间： 2017/8/21
 * 描述：
 */
public class RedisUtils{

    private RedisUtils(){};

    private static int MAX_WAIT = 10000;

    private static int MAX_TOTAL = 1024;

    private static int MAX_IDLE = 200;

    private static JedisPool jedisPool = null;

    private static Jedis jedis = null;

    public  static  void initJedisPool(){
        System.out.println(">>>>>初始化jedispool");
        JedisPoolConfig config =  new JedisPoolConfig();
        config.setMaxWaitMillis(MAX_WAIT);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxTotal(MAX_TOTAL);
        config.setTestOnBorrow(true);
        jedisPool = new JedisPool(config,Protocol.DEFAULT_HOST,Protocol.DEFAULT_PORT,60000,"123456",Protocol.DEFAULT_DATABASE);
    }


    public static synchronized Jedis getJedis(){
        if (jedisPool != null){
            if (jedis==null) {
                jedis = jedisPool.getResource();
            }
            return  jedis;
        }else {
            initJedisPool();
            jedis = jedisPool.getResource();
            return  jedis;
        }
    }



}
