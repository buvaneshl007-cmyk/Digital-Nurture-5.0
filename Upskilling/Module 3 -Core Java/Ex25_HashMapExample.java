import java.util.HashMap;
import java.util.Scanner;
public class Ex25_HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> students = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Add students (enter 0 as ID to stop):");

        while (true) {
            System.out.print("Student ID: ");
            int id = sc.nextInt();
            if (id == 0) break;
            sc.nextLine(); 
            System.out.print("Student Name: ");
            String name = sc.nextLine();
            students.put(id, name);
        }

        System.out.print("\nEnter ID to look up: ");
        int lookupId = sc.nextInt();

        if (students.containsKey(lookupId)) {
            System.out.println("Found: ID " + lookupId + " → " + students.get(lookupId));
        } else {
            System.out.println("No student found with ID " + lookupId);
        }
    }
}