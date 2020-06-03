package com.hollysys.tn.util;
/*
 * FilePath: null.java
 * Project: hydrogen
 * CreatedAt: 2020/6/2 10:14
 * CreatedBy: l_sy
 * Copyright: (c) 2019 HollySys
 * Task: #1
 * Write a description of the code here.
 */

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;

/**
 * @method RedisUtil
 * @auther user
 * @create 2020-06-02-10:14
 */
public class RedisUtil {

    @Autowired
    private static StringRedisTemplate stringRedisTemplate;

    /**
     * 添加缓存
     * @param key
     * @param value
     */
    public static void setValue( String key, Object value ) {
        stringRedisTemplate.opsForValue().set( key, JSONObject.toJSONString(value) );
    }

    /**
     * 根据key获取值
     * @param key
     * @return
     */
    public static String getValueByKey( String key ) {
        return stringRedisTemplate.opsForValue().get( key );
    }

    /**
     * 添加缓存Hash
     * @param key1
     * @param key2
     * @param value
     */
    public static void putValueHash( String key1, Object key2, Object value ) {
        stringRedisTemplate.opsForHash().put( key1, JSONObject.toJSONString(key2) , JSONObject.toJSONString(value) );
    }

    /**
     * 获取缓存Hash
     * @param key1
     * @param key2
     * @return
     */
    public static String getValueByKeyHash( String key1, Object key2  ) {
        Object object = stringRedisTemplate.opsForHash().get( key1, key2 );
        return JSONObject.toJSONString(object);
    }

    /**
     * 获取所有子key
     * @param key
     * @return
     */
    public static Set<Object> getKeys( String key ) {
        return stringRedisTemplate.opsForHash().keys( key );
    }
}
