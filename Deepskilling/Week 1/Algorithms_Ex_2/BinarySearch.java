
public class BinarySearch {

    // Searches a SORTED array of products by productId
    // Time complexity: O(log n)
    public static Product search(Product[] sortedProducts, int targetId) {
        int low = 0;
        int high = sortedProducts.length - 1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;
            int midId = sortedProducts[mid].getProductId();

            if (midId == targetId) {
                System.out.println("Binary Search: Found after " + comparisons + " comparisons.");
                return sortedProducts[mid];
            } else if (midId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Search: Not found after " + comparisons + " comparisons.");
        return null;
    }
}