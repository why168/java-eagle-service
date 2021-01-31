package com.github.eagle.utils.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Configuration
public class SpringRedisLockUtils implements RedisLockInterface {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String lock(String key, long expireTime) {

        // SET key value [EX seconds] [PX milliseconds] [NX|XX]
        // SET key value PX expireTime NX

        // 产生随机数
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        /*Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<?> valueSerializer = redisTemplate.getValueSerializer();
                RedisSerializer<?> keySerializer = redisTemplate.getKeySerializer();

                Object obj = connection.execute(
                        "set", key.getBytes(StandardCharsets.UTF_8), uuid.getBytes(StandardCharsets.UTF_8),
                        "PX".getBytes(StandardCharsets.UTF_8), String.valueOf(expireTime).getBytes(StandardCharsets.UTF_8),
                        "NX".getBytes(StandardCharsets.UTF_8));
                return obj != null;
            }
        });*/

        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, uuid, expireTime, TimeUnit.MILLISECONDS);
        if (result != null && result) {
            return uuid;
        } else {
            return null;
        }
    }

    @Override
    public boolean unLock(String key, String uuid) {
        if (uuid == null || "".equals(uuid)) {
            return false;
        }
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(UNLOCK_LUA_SCRIPT);
        redisScript.setResultType(Long.class);
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(key), uuid);
        return Long.valueOf(1).equals(result);
    }
}
