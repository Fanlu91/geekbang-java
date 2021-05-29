package conc0303;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class Homework03 {

    private static volatile int tmp;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        int result = -1;
        // 1. 主线程自己执行
//        result = sum(); //这是得到的返回值

        // 2. FutureTask + Callable
//        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return sum();
//            }
//        });
//        new Thread(futureTask).start();
//        try {
//            result = futureTask.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        // 3. Future + ExecutorService
/*
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        ExecutorService executorService = Executors.newCachedThreadPool();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        Future<Integer> future = executorService.submit(Homework03::sum);
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }*/

        // 4. runnable join + static volatile int
        /*Thread worker = new Thread(new Runnable() {
            @Override
            public void run() {
                tmp = sum();
            }
        });
        worker.start();
        try {
            worker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = tmp;*/

        // 5.CountDownLatch  + static volatile int
/*        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new CountDownLatchDemo(countDownLatch)).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = tmp;*/

        // 6. Semaphore
       /* Semaphore semaphore = new Semaphore(1);
        try {
            semaphore.acquire();
            new Thread(new SemaphoreDemo(semaphore)).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        result = tmp;*/

        new Thread(new LockSupportDemo(Thread.currentThread())).start();

        LockSupport.park();

        result =tmp;
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程

    }

    static class CountDownLatchDemo implements Runnable {
        private CountDownLatch latch;

        public CountDownLatchDemo(CountDownLatch countDownLatch) {
            this.latch = countDownLatch;
        }

        @Override
        public void run() {
            synchronized (this) {
                tmp = sum();
                latch.countDown();
            }
        }
    }

    static class SemaphoreDemo implements Runnable {
        private Semaphore semaphore;

        public SemaphoreDemo(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            tmp = sum();
            semaphore.release();
        }
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {

        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

    private static class LockSupportDemo implements Runnable {
        private Thread main;

        public LockSupportDemo(Thread main) {
            this.main = main;
        }

        @Override
        public void run() {
            tmp = sum();
            LockSupport.unpark(main);
        }
    }
}
