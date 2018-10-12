package com.kaishengit.config;


import com.kaishengit.prop.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class CacheConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setUsePrefix(true); //定义key的前缀，官方推荐

        /* Map<String, Long> expires = new HashMap<>();
        expires.put("movie",10L);*/

        cacheManager.setExpires(redisProperties.getExpires());
        return cacheManager;
    }

    /*@Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager();
    }*/
}
