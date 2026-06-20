import java.util.Arrays;
import java.util.Comparator;

public class SearchAlgorithms {

    /**
     * Linear search by productName. Works on any (sorted or unsorted) array.
     * Time complexity: O(n)
     */
    public static Product linearSearch(Product[] products, String name) {
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Binary search by productName. Requires the array to be sorted by name.
     * Time complexity: O(log n)
     */
    public static Product binarySearch(Product[] sortedProducts, String name) {
        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = sortedProducts[mid].getProductName().compareToIgnoreCase(name);

            if (comparison == 0) {
                return sortedProducts[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void sortByName(Product[] products) {
        Arrays.sort(products, Comparator.comparing(Product::getProductName, String::compareToIgnoreCase));
    }
}
