package threadPoolTest;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static ThreadPoolExecutor requestExcutor = new ThreadPoolExecutor(
            5,
            100,
            100,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r);
                }
            });

    public static void main(String[] args) {
        requestExcutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });
    }
}
