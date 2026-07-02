public class Q07_TypeCastingExample {
    public static void main(String[] args) {
        double d = 9.78;
        int i = (int) d;
        System.out.println("Double to int: " + d + " -> " + i);
        
        int i2 = 15;
        double d2 = (double) i2;
        System.out.println("Int to double: " + i2 + " -> " + d2);
    }
}