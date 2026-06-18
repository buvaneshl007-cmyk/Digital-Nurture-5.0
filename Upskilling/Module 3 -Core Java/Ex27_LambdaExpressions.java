import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ex27_LambdaExpressions {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("Mango", "Apple", "Banana", "Cherry", "Date");

        System.out.println("Before sorting: " + fruits);

    
        Collections.sort(fruits, (a, b) -> a.compareTo(b));
        System.out.println("After sorting (A-Z): " + fruits);

     
        fruits.sort((a, b) -> b.compareTo(a));
        System.out.println("Reverse sorted (Z-A): " + fruits);
    }
}