package conc0301;

public class DaemonThread {
    
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
                try {
                    Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                Thread t = Thread.currentThread();
                System.out.println("当前线程:" + t.getName());
        };
        Thread thread = new Thread(task);
        thread.setName("test-thread-1");
        thread.setDaemon(true);
        thread.start();
        thread.wait();

        // 如果当前运行的线程只有Daemon thread，jvm会直接终止。
        Thread.sleep(2000);
    }
    
    
}
