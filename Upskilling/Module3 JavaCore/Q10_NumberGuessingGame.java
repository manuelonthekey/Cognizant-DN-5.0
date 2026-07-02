import java.util.Scanner;
import java.util.Random;
public class Q10_NumberGuessingGame {
    public static void main(String[] args) {
        Random rand = new Random();
        int target = rand.nextInt(100) + 1;
        Scanner sc = new Scanner(System.in);
        int guess = 0;
        
        while (guess != target) {
            System.out.print("Guess a number between 1 and 100: ");
            guess = sc.nextInt();
            if (guess > target) System.out.println("Too high!");
            else if (guess < target) System.out.println("Too low!");
            else System.out.println("Correct! The number was " + target);
        }
    }
}