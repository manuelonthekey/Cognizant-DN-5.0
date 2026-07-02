public class Q30_PatternMatchingSwitch {
    public static void checkObject(Object obj) {
        switch (obj) {
            case Integer i -> System.out.println("It's an Integer: " + i);
            case String s -> System.out.println("It's a String: " + s);
            case Double d -> System.out.println("It's a Double: " + d);
            default -> System.out.println("Unknown type");
        }
    }
    public static void main(String[] args) {
        checkObject(10);
        checkObject("Hello Java 21");
        checkObject(99.99);
    }
}