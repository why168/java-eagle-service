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

        for (int i = 0; i < 20; i++) {
            System.out.println(lockUtils.lock("key00", 10000));
        }
    }

}
