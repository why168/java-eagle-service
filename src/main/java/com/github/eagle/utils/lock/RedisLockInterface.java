package com.github.eagle.utils.lock;

/**
 * @author Edwin Wu
 * @since 1.0.0
 */
public interface RedisLockInterface {
    /*
     * RedisLock的正确姿势
     * 加锁：
     * 通过setnx 向特定的key写入一个随机数，并设置失效时间，写入成功即加锁成功
     * 注意点：
     *  必须给锁设置一个失效时间            ----->    避免死锁
     *  加锁时，每个节点产生一个随机字符串    ----->    避免锁误删
     *  写入随机数与设置失效时间必须是同时    ----->    保证加锁的原子性
     *  使用：
     *      SET key value NX PX 3000
     *
     * 解锁：
     *  匹配随机数，删除redis上的特定的key数据，
     *  要保证获取数据，判断一致以及删除数据三个操作是原子性
     *  执行如下lua脚本：
     *      if redis.call('get', KEYS[1]) == ARGV[1] then
     *          return redis.call('del', KEYS[1])
     *      else
     *          return 0
     *      end
     *
     */


    /**
     * lua脚本
     **/
    String UNLOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    String lock(String key, long expireTime);

    boolean unLock(String key, String uuid);

    /**
     * 尝试加锁，间隔循环100ms
     *
     * @param key
     * @param acquireTimeout 尝试加锁的超时时间
     * @param expireTimeout  设置锁的过期时间
     * @return
     */
    default boolean TryLock(String key, long acquireTimeout, long expireTimeout) {
        for (; ; ) {
            if (System.currentTimeMillis() <= acquireTimeout) {
                // lock timeout
                return false;
            }
            String uuid = lock(key, expireTimeout);
            if (uuid == null) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                return true;
            }
        }
    }
}
