package com.future.config;

import com.future.cache.RedisMybatisCache;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

//因为RedisMybatisCache使用了redisTemplate来实现缓存，
// 所以我们需要在MybatisConfiguration初始化时将redisTemplate注入到RedisMybatisCache中。
@Configuration
public class MybatisConfiguration {
    @Resource
    RedisTemplate<Object,Object> redisTemplate; //这是一个注入RedisTemplate对象的注解。
    // 使用@Resource注解，我们可以将redisTemplate注入到MybatisConfiguration中。

    @PostConstruct
    public void init(){ //这是一个初始化方法，使用@PostConstruct注解来标记它。
        // 在该方法中，我们将redisTemplate对象传递给RedisMybatisCache类的静态方法setTemplate()。
        RedisMybatisCache.setTemplate(redisTemplate);
    }
}
