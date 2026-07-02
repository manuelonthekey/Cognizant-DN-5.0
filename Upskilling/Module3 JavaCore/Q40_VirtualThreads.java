public class Q40_VirtualThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 100_000; i++) {
            final int taskId = i;
            Thread.startVirtualThread(() -> {
                if (taskId % 10000 == 0) {
                    System.out.println("Virtual thread message from task: " + taskId);
                }
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}