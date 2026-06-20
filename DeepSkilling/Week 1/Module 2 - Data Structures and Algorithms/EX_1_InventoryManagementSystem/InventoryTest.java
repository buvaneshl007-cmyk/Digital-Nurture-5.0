public class InventoryTest {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        inventory.addProduct(new Product("P001", "Wireless Mouse", 50, 19.99));
        inventory.addProduct(new Product("P002", "Mechanical Keyboard", 30, 79.99));
        inventory.addProduct(new Product("P003", "USB-C Hub", 100, 24.99));

        System.out.println("Initial inventory:");
        inventory.printAll();

        inventory.updateProduct("P001", 45, 17.99);
        inventory.deleteProduct("P003");

        System.out.println();
        System.out.println("After update and delete:");
        inventory.printAll();

        System.out.println();
        System.out.println("Lookup P002: " + inventory.getProduct("P002"));
    }
}
