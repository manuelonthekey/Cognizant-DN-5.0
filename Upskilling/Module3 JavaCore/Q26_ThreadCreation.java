class MyThread extends Thread {
    private String message;
    public MyThread(String message) { this.message = message; }
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(message);
        }
    }
}
public class Q26_ThreadCreation {
    public static void main(String[] args) {
        MyThread t1 = new MyThread("Thread 1 executing");
        MyThread t2 = new MyThread("Thread 2 executing");
        t1.start();
        t2.start();
    }
}