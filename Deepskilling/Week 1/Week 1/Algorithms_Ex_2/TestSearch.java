
import java.util.Arrays;
import java.util.Comparator;

public class TestSearch {
    public static void main(String[] args) {

        // Create an unsorted array of products for Linear Search
        Product[] unsortedProducts = {
            new Product(105, "Bluetooth Speaker", "Electronics"),
            new Product(102, "Running Shoes", "Footwear"),
            new Product(110, "Yoga Mat", "Fitness"),
            new Product(101, "Wireless Mouse", "Electronics"),
            new Product(108, "Coffee Maker", "Appliances"),
            new Product(103, "Backpack", "Accessories"),
            new Product(107, "Desk Lamp", "Home"),
            new Product(104, "Water Bottle", "Fitness"),
            new Product(106, "Notebook Set", "Stationery"),
            new Product(109, "Headphones", "Electronics")
        };

        // Create a sorted copy for Binary Search (sorted by productId)
        Product[] sortedProducts = Arrays.copyOf(unsortedProducts, unsortedProducts.length);
        Arrays.sort(sortedProducts, Comparator.comparingInt(Product::getProductId));

        int targetId = 107;

        System.out.println("--- Linear Search (unsorted array) ---");
        long startLinear = System.nanoTime();
        Product resultLinear = LinearSearch.search(unsortedProducts, targetId);
        long endLinear = System.nanoTime();
        System.out.println("Result: " + resultLinear);
        System.out.println("Time taken: " + (endLinear - startLinear) + " ns");

        System.out.println("\n--- Binary Search (sorted array) ---");
        long startBinary = System.nanoTime();
        Product resultBinary = BinarySearch.search(sortedProducts, targetId);
        long endBinary = System.nanoTime();
        System.out.println("Result: " + resultBinary);
        System.out.println("Time taken: " + (endBinary - startBinary) + " ns");

        // Search for a product that doesn't exist
        System.out.println("\n--- Searching for non-existent product (ID 999) ---");
        LinearSearch.search(unsortedProducts, 999);
        BinarySearch.search(sortedProducts, 999);
    }
}