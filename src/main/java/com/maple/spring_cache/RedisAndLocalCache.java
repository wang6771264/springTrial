package com.maple.spring_cache;

import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class RedisAndLocalCache implements Cache {
    // 本地缓存提供
    ConcurrentHashMap<Object, Object> local = new ConcurrentHashMap<>();
    RedisCache redisCache;
    TowLevelCacheManager cacheManager;

    public RedisAndLocalCache(TowLevelCacheManager cacheManager, RedisCache redisCache) {
        this.redisCache = redisCache;
        this.cacheManager = cacheManager;
    }

    @Override
    public String getName( ) {
        return redisCache.getName();
    }

    @Override
    public Object getNativeCache( ) {
        return redisCache.getNativeCache();
    }

    @Override
    public ValueWrapper get(Object key) {
        // 一级缓存先取
        ValueWrapper wrapper = (ValueWrapper) local.get(key);
        if (wrapper != null) {
            return wrapper;
        } else {
            // 二级缓存取
            wrapper = redisCache.get(key);
            if (wrapper != null) {
                local.put(key, wrapper);
            }
            return wrapper;
        }
    }

    @Override
    public void put(Object key, Object value) {
        System.out.println(value.getClass().getClassLoader());
        redisCache.put(key, value);
        //通知其他节点缓存更新
        clearOtherJVM();
    }

    @Override
    public void evict(Object key) {
        redisCache.evict(key);
        //通知其他节点缓存更新
        clearOtherJVM();
    }

    protected void clearOtherJVM( ) {
        cacheManager.publishMessage(redisCache.getName(), redisCache.getName());
    }

    // 提供给CacheManager清空一节缓存
    public void clearLocal( ) {
        this.local.clear();
    }


    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }


    @Override
    public void clear( ) {

    }
}