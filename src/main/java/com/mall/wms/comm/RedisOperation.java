package com.mall.wms.comm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisOperation {

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    public <K,V> void setRedisData(K key,V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public <K> Object getRedisData(K key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 存入redis,设置过期时间
     * @param key key
     * @param value value
     * @param timeOut 过期时间
     * @param timeUnit 时间单位(天，时，分，秒)
     */
    public <K,V> void setRedisExpireAndData(K key,V value,long timeOut,TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
    }

    public <K> void removeRedisByKey(K key){
        redisTemplate.delete(key);
    }
}
