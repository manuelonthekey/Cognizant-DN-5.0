import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Q27_LambdaExpressions {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("Banana", "Apple", "Cherry"));
        Collections.sort(list, (s1, s2) -> s1.compareTo(s2));
        System.out.println("Sorted List: " + list);
    }
}