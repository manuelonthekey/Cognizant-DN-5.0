public class Q08_OperatorPrecedence {
    public static void main(String[] args) {
        int result = 10 + 5 * 2;
        System.out.println("Result of 10 + 5 * 2 is: " + result);
        System.out.println("Explanation: Multiplication (*) has higher precedence than addition (+), so 5 * 2 is evaluated first, then 10 is added.");
    }
}