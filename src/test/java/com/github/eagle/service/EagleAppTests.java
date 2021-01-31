package com.github.eagle.service;

import com.github.eagle.utils.lock.SpringRedisLockUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

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

    @Test
    public void s() {
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(3);

//        Future<String>
//        CompletableFuture<String>

        //1.获取首页banner信息
        Future<String> t1 = executorService.submit(() -> {
            // TODO HTTP
            Thread.sleep(100);
            countDownLatch.countDown();
            return "首页banner信息";
        });

        //2.获取feed运营内容
        Future<String> t2 = executorService.submit(() -> {
            // TODO HTTP
            Thread.sleep(200);
            countDownLatch.countDown();
            return "feed运营内容";
        });

        //3.获取feed推荐内容
        Future<String> t3 = executorService.submit(() -> {
            // TODO HTTP
            Thread.sleep(350);
            countDownLatch.countDown();
            return "feed推荐内容";
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("首页banner信息：" + t1.get());
            System.out.println("feed运营内容：" + t2.get());
            System.out.println("feed推荐内容：" + t3.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("time:" + (end - start));


    }

    @Test
    public void s1() {
//        final Executor customExecutor = ForkJoinPool.commonPool();
//
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                isInterrupted();
//                isAlive();
//                isDaemon();
//            }
//        };
//        thread.interrupt();
        System.out.println("Entering main Method");

        DaemonThread t = new DaemonThread();
        t.setDaemon(false);
        t.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException x) {}

        System.out.println("Leaving main method");


    }

    public class DaemonThread extends Thread {
        public void run() {
            System.out.println("Entering run method");

            try {
                System.out.println("In run Method: currentThread() is" + Thread.currentThread());

                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException x) {
                    }

                    System.out.println("In run method: woke up again");
                }
            } finally {
                System.out.println("Leaving run Method");
            }
        }
    }

}
