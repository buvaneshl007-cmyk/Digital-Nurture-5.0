public class SortingAlgorithms {

    /**
     * Bubble Sort by totalPrice ascending.
     * Time complexity: O(n^2) average/worst, O(n) best (already sorted, with early exit).
     */
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break; // already sorted, exit early
            }
        }
    }

    /**
     * Quick Sort by totalPrice ascending.
     * Time complexity: O(n log n) average, O(n^2) worst case.
     */
    public static void quickSort(Order[] orders) {
        quickSort(orders, 0, orders.length - 1);
    }

    private static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                swap(orders, i, j);
            }
        }
        swap(orders, i + 1, high);
        return i + 1;
    }

    private static void swap(Order[] orders, int i, int j) {
        Order temp = orders[i];
        orders[i] = orders[j];
        orders[j] = temp;
    }
}
