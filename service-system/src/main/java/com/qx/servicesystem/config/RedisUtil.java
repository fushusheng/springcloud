package com.qx.servicesystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RedisUtil {
    private static final String LOCK_PREFIX="redis_lock";
    //加锁失效时间(毫秒)
    private static final int LOCK_EXPIRE=10*1000;
    @Autowired
    private RedisTemplate redisTemplate;
    public boolean tryLock(String key){
        String lock=LOCK_PREFIX+key;
        return (Boolean) redisTemplate.execute((RedisCallback)  redisConnection -> {
            long expireAt=System.currentTimeMillis()+LOCK_EXPIRE+1;
            Boolean acquire = redisConnection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
            if(acquire){
                return true;
            }else{
                byte[] value = redisConnection.get(lock.getBytes());
                if(Objects.nonNull(value)&&value.length>=0){
                    long expireTime = Long.parseLong(new String(value));
                    if(expireTime<System.currentTimeMillis()){
                        byte[] oldValue = redisConnection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE + 1).getBytes());
                        if (oldValue != null) {
                            return Long.parseLong(new String(oldValue))<System.currentTimeMillis();
                        }
                    }
                }
            }
            return false;
        });
    }
}
