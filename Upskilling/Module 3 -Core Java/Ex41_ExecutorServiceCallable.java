import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Ex41_ExecutorServiceCallable {

    static class SquareTask implements Callable<String> {
        private final int number;

        SquareTask(int number) {
            this.number = number;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(100); 
            int result = number * number;
            return Thread.currentThread().getName()
                + " → " + number + "² = " + result;
        }
    }

    public static void main(String[] args) {
        int numTasks   = 10;
        int numThreads = 4; 

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<String>> futures = new ArrayList<>();

        System.out.println("Submitting " + numTasks + " tasks to a pool of "
            + numThreads + " threads...\n");

   
        for (int i = 1; i <= numTasks; i++) {
            Future<String> future = executor.submit(new SquareTask(i));
            futures.add(future);
        }

       
        System.out.println("Results:");
        for (Future<String> future : futures) {
            try {
                System.out.println(future.get()); 
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Task error: " + e.getMessage());
            }
        }

        executor.shutdown(); 
        System.out.println("\nAll tasks completed. Executor shut down.");
    }
}