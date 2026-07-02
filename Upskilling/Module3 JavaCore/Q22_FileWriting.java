import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Q22_FileWriting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string to save to file: ");
        String data = sc.nextLine();
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(data);
            System.out.println("Data successfully written to output.txt");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}