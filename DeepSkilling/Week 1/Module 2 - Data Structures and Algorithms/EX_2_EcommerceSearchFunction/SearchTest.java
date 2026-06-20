public class SearchTest {
    public static void main(String[] args) {
        Product[] products = {
                new Product("P004", "Yoga Mat", "Fitness"),
                new Product("P001", "Wireless Mouse", "Electronics"),
                new Product("P003", "USB-C Hub", "Electronics"),
                new Product("P002", "Mechanical Keyboard", "Electronics"),
                new Product("P005", "Blender", "Kitchen")
        };

        System.out.println("Linear search for 'Blender':");
        System.out.println(SearchAlgorithms.linearSearch(products, "Blender"));

        SearchAlgorithms.sortByName(products);
        System.out.println("\nSorted products:");
        for (Product p : products) {
            System.out.println(p);
        }

        System.out.println("\nBinary search for 'USB-C Hub':");
        System.out.println(SearchAlgorithms.binarySearch(products, "USB-C Hub"));

        System.out.println("\nBinary search for 'Nonexistent Item':");
        System.out.println(SearchAlgorithms.binarySearch(products, "Nonexistent Item"));
    }
}
