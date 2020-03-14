package com.mall.wms.comm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisOperation {

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    public <K,V> void setRedisData(K key,V value){
        redisTemplate.opsForValue().set(key,value);
    }
}
