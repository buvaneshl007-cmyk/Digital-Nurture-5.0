import java.util.List;
import java.util.ArrayList;

public class Ex38_DecompileClassFile {

  
    static <T> List<T> repeat(T item, int times) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            list.add(item);
        }
        return list;
    }

 
    static void trwDemo() {
        try (AutoCloseable ac = () -> System.out.println("Resource closed.")) {
            System.out.println("Using resource...");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        System.out.println(repeat("Java", 3));
        trwDemo();
        String name = "World";
        System.out.println("Hello, " + name + "!");
    }
}