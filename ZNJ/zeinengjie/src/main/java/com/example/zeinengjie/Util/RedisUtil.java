package com.example.zeinengjie.Util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    // 存值
    public void set(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }
    // 取值
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public <C> List<C>  getList(String key) {
        String json = redisTemplate.opsForValue().get(key);
        return  new Gson().fromJson(json, new TypeToken<List<C>>(){}.getType());
    }

    // 删除
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    // 判断key是否存在
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
