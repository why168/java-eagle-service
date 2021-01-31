package com.github.eagle.service;

import com.github.eagle.service.lock.SpringRedisLockUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EagleAppTests {

    @Autowired
    private SpringRedisLockUtils lockUtils;

    @Test
    void lockUtilsTest() {
        String lockKey = "kkkk";
        String uuid = "";

        for (int i = 1; i <= 5; i++) {
            String ok = lockUtils.lock(lockKey, 10000);
            if (ok != null) {
                uuid = ok;
            }
            System.out.println("lock:" + ok);
            if (i == 5) {
                System.out.println("unlock:" + lockUtils.unLock(lockKey, uuid));
            }
        }
    }

}
