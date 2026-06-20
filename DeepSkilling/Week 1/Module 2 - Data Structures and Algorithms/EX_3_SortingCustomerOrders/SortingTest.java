import java.util.Arrays;

public class SortingTest {
    public static void main(String[] args) {
        Order[] bubbleOrders = {
                new Order("O001", "Alice", 250.50),
                new Order("O002", "Bob", 89.99),
                new Order("O003", "Charlie", 430.00),
                new Order("O004", "Dave", 15.75),
                new Order("O005", "Eve", 199.99)
        };

        Order[] quickOrders = Arrays.copyOf(bubbleOrders, bubbleOrders.length);

        System.out.println("Before sorting:");
        for (Order o : bubbleOrders) {
            System.out.println(o);
        }

        SortingAlgorithms.bubbleSort(bubbleOrders);
        System.out.println("\nAfter Bubble Sort:");
        for (Order o : bubbleOrders) {
            System.out.println(o);
        }

        SortingAlgorithms.quickSort(quickOrders);
        System.out.println("\nAfter Quick Sort:");
        for (Order o : quickOrders) {
            System.out.println(o);
        }
    }
}
