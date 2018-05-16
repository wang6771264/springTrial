package com.maple.spring_cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class CacheConfig {
    @Bean
    public TowLevelCacheManager cacheManager(RedisTemplate redisTemplate) {
        //RedisCache需要一个RedisCacheWriter来实现读写Redis
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        /*SerializationPair用于Java和Redis之间的序列化和反序列化，我们这里使用自带的JdkSerializationRedisSerializer,并在反序列化过程中，使用当前的ClassLoader*/
        RedisSerializationContext.SerializationPair pair = RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer(this.getClass().getClassLoader()));
        /*构造一个RedisCache的配置，比如是否使用前缀，比如Key和Value的序列化机制（*/
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        /*创建CacheManager，并返回给Spring 容器*/
        TowLevelCacheManager cacheManager = new TowLevelCacheManager(redisTemplate, writer, config);
        return cacheManager;
    }
}