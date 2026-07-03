public class TestSingleton {
    public static void main(String[] args) {

        // Get first reference to Logger
        Logger logger1 = Logger.getInstance();
        logger1.log("First message from logger1");

        // Get second reference to Logger
        Logger logger2 = Logger.getInstance();
        logger2.log("Second message from logger2");

        // Verify both references point to the same instance
        if (logger1 == logger2) {
            System.out.println("SUCCESS: logger1 and logger2 are the same instance.");
        } else {
            System.out.println("FAILURE: Different instances were created.");
        }

        // Extra proof: hashCode will be identical for both references
        System.out.println("logger1 hashCode: " + logger1.hashCode());
        System.out.println("logger2 hashCode: " + logger2.hashCode());
    }
}