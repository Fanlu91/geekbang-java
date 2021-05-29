package conc0301;

public class Runner2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("进入Runner2运行状态——————————" + i);
        }

        // Tests whether this thread has been interrupted.
        // The interrupted status of the thread is unaffected by this method.

        boolean result = Thread.currentThread().isInterrupted();

        // Tests whether the current thread has been interrupted.
        // The interrupted status of the thread is cleared by this method.
        // In other words, if this method were to be called twice in succession,
        // the second call would return false
        // (unless the current thread were interrupted again,
        // after the first call had cleared its interrupted status
        // and before the second call had examined it).
        boolean result1 = Thread.interrupted(); // 重置状态
        
        boolean result3 = Thread.currentThread().isInterrupted();

        System.out.println("Runner2.run result ===>" + result);
        System.out.println("Runner2.run result1 ===>" + result1);
        System.out.println("Runner2.run result3 ===>" + result3);
        
        
    }
}
