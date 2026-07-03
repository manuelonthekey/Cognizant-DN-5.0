
public class LinearSearch {

    // Searches an unsorted array of products by productId
    // Time complexity: O(n)
    public static Product search(Product[] products, int targetId) {
        int comparisons = 0;

        for (int i = 0; i < products.length; i++) {
            comparisons++;
            if (products[i].getProductId() == targetId) {
                System.out.println("Linear Search: Found after " + comparisons + " comparisons.");
                return products[i];
            }
        }

        System.out.println("Linear Search: Not found after " + comparisons + " comparisons.");
        return null;
    }
}