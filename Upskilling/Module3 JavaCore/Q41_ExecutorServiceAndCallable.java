import java.util.concurrent.*;
public class Q41_ExecutorServiceAndCallable {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Callable<String> task1 = () -> { Thread.sleep(100); return "Result from Task 1"; };
        Callable<String> task2 = () -> { Thread.sleep(100); return "Result from Task 2"; };
        
        try {
            Future<String> future1 = executor.submit(task1);
            Future<String> future2 = executor.submit(task2);
            
            System.out.println(future1.get());
            System.out.println(future2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}