import com.kaishengit.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-data-redis.xml")
public class JedisSpringData {


    private RedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        //this.redisTemplate.setValueSerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));

    }


    @Test
    public void setStringValue(){
        redisTemplate.opsForValue().set("hello","hello,spring data");
    }

    @Test
    public void getStringValue(){
        String value = (String) redisTemplate.opsForValue().get("hello");
        System.out.println(value);
    }

    @Test
    public void saveUserToRedis(){
        User user = new User(007,"faker","koera");
        redisTemplate.opsForValue().set("user:007",user);
    }

    @Test
    public void getUserFromRedis(){
        User user = (User) redisTemplate.opsForValue().get("user:007");
        System.out.println(user);
    }



}
