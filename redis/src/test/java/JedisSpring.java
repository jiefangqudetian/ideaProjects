import com.google.gson.Gson;
import com.kaishengit.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class JedisSpring {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void string(){
        Jedis jedis = jedisPool.getResource();
        jedis.set("name","他信");
        String value = jedis.get("name");
        System.out.println(value);

        jedis.close();
    }

    @Test
    public void saveUserToRedis(){
        User user = new User(1001,"tom","beijing");
        String json = new Gson().toJson(user);

        Jedis jedis = jedisPool.getResource();
        jedis.set("user:1001",json);

        jedis.close();
    }


    @Test
    public void getUserFromRedis(){
        Jedis jedis = jedisPool.getResource();
        String json = jedis.get("user:1001");

        User user = new Gson().fromJson(json,User.class);

        System.out.println(user);
        jedis.close();
    }
}
