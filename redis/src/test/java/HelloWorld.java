import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class HelloWorld {

    @Test
    public void hello(){
        //如果要连接远程的redis，需要修改redis的配置文件 bind=指定ip或0.0.0.0(生成环境勿用)
        Jedis jedis = new Jedis("192.168.159.116",6379);
        String pong = jedis.ping();
        System.out.println(pong);
        jedis.close();
    }

    @Test
    public void string(){
        Jedis jedis = new Jedis("192.168.159.116",6379);
        jedis.set("user","wawawa","NX");
        String value = jedis.get("user");
        System.out.println(value);
        jedis.close();
    }

    @Test
    public void hash(){
        Jedis jedis = new Jedis("192.168.159.116",6379);
        jedis.hset("java:hash","name","jack");
        String value1 = jedis.hget("java:hash","name");


        jedis.close();
        System.out.println(value1);
    }

    @Test
    public void lpush(){
        Jedis jedis = new Jedis("192.168.159.116",6379);
        jedis.lpush("java:list","小刘","小王");
        List<String> result = jedis.lrange("java:list",0L,-1L);
        for (String str:result) {
            System.out.println(str);
        }
    }

    @Test
    public void zadd(){
        Jedis jedis = new Jedis("192.168.159.116",6379);
        jedis.zadd("java:zset",88,"jack");
        Double aDouble = jedis.zscore("java:zset","jack");
        System.out.println(aDouble);
    }


    @Test
    public void pool(){
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(10);
        genericObjectPoolConfig.setMinIdle(5);

        JedisPool jedisPool = new JedisPool(genericObjectPoolConfig,"192.168.159.116",6379);
        Jedis jedis = jedisPool.getResource();

        jedis.del("user");

        jedis.close();
        jedisPool.destroy();
    }
}
