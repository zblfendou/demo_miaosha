package com.example.demo.service;

import com.example.demo.common.KeyPrefix;
import com.example.demo.util.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/11/29
 */
@Service
public class RedisService {
    @Autowired
    private static ObjectMapper objectMapper;
    @Autowired
    private RedisUtil redisUtil;

    public <T> boolean set(KeyPrefix prefix, String key, T value) {

        Object v = beanToObject(value);
        if (v == null) return false;

        String realKey = getRealKey(prefix, key);

        int expiredSeconds = prefix.expiredSeconds();
        if (expiredSeconds <= 0) {
            redisUtil.set(realKey, v);
        } else {
            redisUtil.set(realKey, v, expiredSeconds);
        }
        return true;
    }

    /**
     * 获取当个对象
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        String realKey = getRealKey(prefix, key);
        String str = (String) redisUtil.get(realKey);
        return stringToBean(str, clazz);
    }

    /**
     * 判断key是否存在
     */
    public <T> boolean exists(KeyPrefix prefix, String key) {
        String realKey = getRealKey(prefix, key);
        return redisUtil.hasKey(realKey);
    }


    /**
     * 删除
     */
    public void delete(KeyPrefix prefix, String key) {
        String realKey = getRealKey(prefix, key);
        redisUtil.del(realKey);
    }

    /**
     * 增加值
     */
    public <T> Long incr(KeyPrefix prefix, String key, long delta) {
        String realKey = getRealKey(prefix, key);
        return redisUtil.incr(realKey, delta);
    }

    /**
     * 减少值
     */
    public <T> Long decr(KeyPrefix prefix, String key, long delta) {
        String realKey = getRealKey(prefix, key);
        return redisUtil.decr(realKey, delta);
    }

    private String getRealKey(KeyPrefix prefix, String key) {
        //生成真正的key
        return prefix.getPrefix() + key;
    }

    public static <T> Object beanToObject(T value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Integer || value instanceof Long || value instanceof Double || value instanceof Float || value instanceof String) {
            return value;
        } else {
            try {
                return objectMapper.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }
       /* Class<?> clazz = value.getClass();
        if (value instanceof Integer.) {
            return value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            try {
                return objectMapper.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }*/
    }

    @SuppressWarnings("unchecked")
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            try {
                return objectMapper.readValue(str, clazz);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
