import java.util.ArrayList;
import java.util.Scanner;
public class Ex24_ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter student names (type 'done' to stop):");

        while (true) {
            System.out.print("Name: ");
            String name = sc.nextLine().trim();
            if (name.equalsIgnoreCase("done")) break;
            names.add(name);
        }

        System.out.println("\nAll Student Names:");
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". " + names.get(i));
        }
    }
}