package io.kimmking.cache.demo;

import redis.clients.jedis.Jedis;

public class LockDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("服务正在运行: " + jedis.ping());
//        jedis.set("test", "www.fanlucloud.com");
//        System.out.println("redis 存储的字符串 test 为: " + jedis.get("test"));

        String randomLock = String.valueOf(Math.random());
        Thread t1 = new TryLockThread(randomLock, "t1");
        Thread t2 = new TryLockThread(randomLock, "t2");
        Thread t3 = new TryLockThread(randomLock, "t3");
        Thread t4 = new TryLockThread(randomLock, "t4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class TryLockThread extends Thread {
    private Jedis jedis;
    private String lock;
    private String requestId;

    public TryLockThread(String lock, String requestId) {
        this.jedis = new Jedis("localhost");
        this.lock = lock;
        this.requestId = requestId;
    }

    @Override
    public void run() {
        boolean result = RedisTool.tryGetDistributedLock(jedis, lock, requestId, 30000);
        System.out.println(requestId + " tried to get lock " + lock + " " + result);
        result = RedisTool.releaseDistributedLock(jedis, lock, requestId);
        System.out.println(requestId + " tried to release lock " + lock + " " + result);
        System.out.println("try lock " + lock + " " + RedisTool.increaseCount(jedis, lock) + " times.");
    }
}
