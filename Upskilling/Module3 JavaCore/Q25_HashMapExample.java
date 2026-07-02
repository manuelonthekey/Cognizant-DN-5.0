import java.util.HashMap;
import java.util.Scanner;
public class Q25_HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> students = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        students.put(101, "Alice");
        students.put(102, "Bob");
        
        System.out.print("Enter student ID (101 or 102) to search: ");
        int id = sc.nextInt();
        if (students.containsKey(id)) {
            System.out.println("Student Name: " + students.get(id));
        } else {
            System.out.println("Student not found.");
        }
    }
}