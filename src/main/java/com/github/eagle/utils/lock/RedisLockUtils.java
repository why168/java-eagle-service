package com.github.eagle.utils.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.UUID;

/**
 * @author qinjp
 * @date 2019-05-31
 **/
public class RedisLockUtils implements RedisLockInterface {

    private Jedis jedis;

    public RedisLockUtils() {
        /*
         * Java操作Redis的库有两个:Jedis和Lettuce
         * 目前SpringBoot 2.x中已经将Jedis换成了Lettuce。
         */
        jedis = new Jedis("127.0.0.1", 6379);
//        jedis.auth("", "");
        jedis.select(0);
    }

    /**
     * 加锁
     */
    @Override
    public String lock(String key, long expireTime) {
        // 产生随机数
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // SET key value PX 3000 NX 成功返回值
        // SET key value [EX seconds|PX milliseconds|KEEPTTL] [NX|XX]
        String result = jedis.set(key, uuid, SetParams.setParams().px(expireTime).nx());
        // 随机数绑定线程
        if ("OK".equals(result)) {
            return uuid;
        } else {
            return null;
        }
    }

    /**
     * 释放分布式锁
     */
    @Override
    public boolean unLock(String key, String uuid) {
//        ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> null);
        //当前线程没有绑定uuid
        //直接返回
        if (uuid == null || "".equals(uuid)) {
            return false;
        }

        Object result = jedis.eval(UNLOCK_LUA_SCRIPT, Collections.singletonList(key), Collections.singletonList(uuid));
        return Long.valueOf(1).equals(result);
    }


//    public static void main(String[] args) {
//        RedisLockUtils redisLockUtils = new RedisLockUtils();
//
//        String lockKey = "kkkk";
//        String uuid = "";
//
//        for (int i = 1; i <= 10; i++) {
//            String ok = redisLockUtils.lock(lockKey, 10000);
//            if (ok != null) {
//                uuid = ok;
//            }
//            System.out.println("lock:" + ok);
//            if (i == 5) {
//                System.out.println("unlock:" + redisLockUtils.unLock(lockKey, uuid));
//                System.out.println("unlock:" + redisLockUtils.unLock(lockKey, uuid));
//                System.out.println("unlock:" + redisLockUtils.unLock(lockKey, uuid));
//            }
//        }
//    }
}



