public class Logger {

    // Step 2: private static instance of itself
    private static Logger instance;

    // Optional: track number of log calls to prove single instance behavior
    private int logCount;

    // Private constructor prevents instantiation from outside the class
    private Logger() {
        logCount = 0;
        System.out.println("Logger instance created.");
    }

    // Step 2 & 3: public static method to get the single instance
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // A simple logging method to demonstrate usage
    public void log(String message) {
        logCount++;
        System.out.println("[LOG #" + logCount + "] " + message);
    }
}