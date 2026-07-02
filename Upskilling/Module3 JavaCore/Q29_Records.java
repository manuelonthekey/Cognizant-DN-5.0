import java.util.List;
record Person(String name, int age) {}
public class Q29_Records {
    public static void main(String[] args) {
        List<Person> people = List.of(
            new Person("Alice", 25),
            new Person("Bob", 15),
            new Person("Charlie", 30)
        );
        people.stream()
              .filter(p -> p.age() >= 18)
              .forEach(System.out::println);
    }
}