package com.maple.redisCache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis初始化
 * @author maple
 */
@EnableConfigurationProperties
//两个类都引用了该文件,项目运行时，是否重复导入,待验证
@PropertySource("classpath:redis.properties")
public class RedisInitialize {

    @Bean
    @ConfigurationProperties(prefix = "redis.poolconfig")
    public JedisPoolConfig getJedisPoolConfig(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        return poolConfig;
    }

    @Bean
    @ConfigurationProperties(prefix="redis.conn")
    public JedisConnectionFactory getConnectionFactory(){
       return commConnectionFactoryProp();
    }

    private JedisConnectionFactory commConnectionFactoryProp(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setUsePool(true);
        factory.setTimeout(3000);
        factory.setDatabase(0);
        JedisPoolConfig config = getJedisPoolConfig();
        factory.setPoolConfig(config);
        return factory;
    }

    @Bean
    public JedisConnectionFactory getCacheConnectionFactory(){
        return commConnectionFactoryProp();
    }

    @Bean
    public RedisTemplate<?, ?> getRedisTemplate(){
        RedisTemplate<?,?> template = new StringRedisTemplate(getConnectionFactory());
        return template;
    }
}
