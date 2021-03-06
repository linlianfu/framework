package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.model.mybatis.UserInfo;
import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: eleven
 * @since:  2017/8/20 0020
 */
//@Slf4j
public class RedisClient {
    private static Jedis jedis;
    private static JedisPool jedisPool;
    private static ShardedJedis shardedJedis;
    private static ShardedJedisPool shardedJedisPool;



    public static void main(String arg[]){
        new RedisClient().show();
    }
    public RedisClient()
    {
        initialPool();
        initialShardedPool();
        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();

    }

    /**
     * 初始化非切片池
     */
    private static  void initialPool()
    {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        jedisPool = new JedisPool(config,Protocol.DEFAULT_HOST,Protocol.DEFAULT_PORT,Protocol.DEFAULT_TIMEOUT,"123456",Protocol.DEFAULT_DATABASE);
    }

    /**
     * 初始化切片池
     */
    private void initialShardedPool()
    {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));

        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    public static  void show() {
        KeyOperate();
        StringOperate();
        ListOperate();
        SetOperate();
        SortedSetOperate();
        HashOperate();
        jedisPool.returnResource(jedis);
        shardedJedisPool.returnResource(shardedJedis);
    }

    private static void KeyOperate() {
//        String mykey = jedis.get("mykey");
//        System.out.println(mykey);
//        log.info("mykey:{}",mykey);

        String key = "java";
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(20);
        userInfo.setName("林连福");

        jedis.set(key, JSONObject.toJSONString(userInfo));
        System.out.println(jedis.get(key));
//        Set<String> keys = jedis.keys("*");
//
//        for (String key:keys){
//            System.out.println(key);
//        }

//        jedis.

    }

    private static void StringOperate() {
    }

    private static void ListOperate() {
    }

    private static void SetOperate() {
    }

    private static void SortedSetOperate() {
    }

    private static void HashOperate() {
    }

}
