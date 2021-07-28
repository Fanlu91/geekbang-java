package io.kimmking.cache.demo;

import redis.clients.jedis.Jedis;

import java.util.Collections;

public class RedisTool {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * 加锁代码
     * 1. 当前没有锁（key不存在），就进行加锁操作，并对锁设置个有效期，同时value表示加锁的客户端。
     * 2. 已有锁存在，不做任何操作。
     *
     * @param jedis      Redis客户端
     * @param lockKey    锁
     * @param requestId  请求标识，解锁对象依据
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    private static final Long RELEASE_SUCCESS = 1L;
    /**
     * 释放分布式锁
     *
     * @param jedis     Redis客户端
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
        // 使用lua脚本保证操作的原子性
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }


    public static Long increaseCount(Jedis jedis, String key) {
        return jedis.incr(key);
    }
}