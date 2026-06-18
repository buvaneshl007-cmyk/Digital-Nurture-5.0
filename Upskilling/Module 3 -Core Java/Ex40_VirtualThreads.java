import java.util.concurrent.atomic.AtomicInteger;

public class Ex40_VirtualThreads {

    static final int THREAD_COUNT = 100_000;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);

        long startVirtual = System.currentTimeMillis();
        Thread[] virtualThreads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            int id = i;
            virtualThreads[i] = Thread.startVirtualThread(() -> {
            
                counter.incrementAndGet();
                if (id < 3) {
                    System.out.println("Virtual thread #" + id + " → " + Thread.currentThread());
                }
            });
        }

        for (Thread t : virtualThreads) t.join();
        long virtualTime = System.currentTimeMillis() - startVirtual;

        System.out.println("\nAll " + THREAD_COUNT + " virtual threads completed.");
        System.out.println("Counter = " + counter.get());
        System.out.println("Virtual threads time: " + virtualTime + " ms");

        counter.set(0);
        int PLATFORM_COUNT = 1_000;
        long startPlatform = System.currentTimeMillis();
        Thread[] platformThreads = new Thread[PLATFORM_COUNT];

        for (int i = 0; i < PLATFORM_COUNT; i++) {
            platformThreads[i] = new Thread(() -> counter.incrementAndGet());
            platformThreads[i].start();
        }
        for (Thread t : platformThreads) t.join();
        long platformTime = System.currentTimeMillis() - startPlatform;

        System.out.println("\n" + PLATFORM_COUNT + " platform threads time: " + platformTime + " ms");
        System.out.println("(Virtual threads are far more scalable for I/O-bound workloads.)");
    }
}