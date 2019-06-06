package com.zzus.springbook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author wangwei
 */
public class RedisConfig {
    public RedisTemplate<Serializable,Object> redisTemplate (RedisConnectionFactory connectionFactory){
        RedisTemplate<Serializable,Object> template = new RedisTemplate<Serializable, Object>();
        template.setConnectionFactory(connectionFactory);
        template.afterPropertiesSet();
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }
}
