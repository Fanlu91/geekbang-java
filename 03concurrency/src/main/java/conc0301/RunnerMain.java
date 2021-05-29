
package conc0301;

import java.io.IOException;

public class RunnerMain {

    public static void main(String[] args) throws IOException {

        Runner1 runner1 = new Runner1();
        Thread thread1 = new Thread(runner1);

        Runner2 runner2 = new Runner2();
        Thread thread2 = new Thread(runner2);

        thread1.start();
        thread2.start();

        thread2.interrupt();  // i = true

        //Returns an estimate of the number of active threads in the current thread's thread group and its subgroups.
        // Recursively iterates over all subgroups in the current thread's thread group.
        System.out.println("threads in thread group: "+Thread.activeCount());
        
        Thread.currentThread().getThreadGroup().list();
        //Returns an estimate of the number of active groups in this thread group and its subgroups.
        // Recursively iterates over all subgroups in this thread group.
        System.out.println("subgroups in thread group: "+Thread.currentThread().getThreadGroup().getParent().activeGroupCount());
        Thread.currentThread().getThreadGroup().getParent().list();
        
    }
}
