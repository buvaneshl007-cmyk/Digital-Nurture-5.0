import java.util.List;
import java.util.stream.Collectors;

record Person(String name, int age) {}

public class Ex29_Records {
    public static void main(String[] args) {
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Bob",   17);
        Person p3 = new Person("Charlie", 25);
        Person p4 = new Person("Diana",   15);

        System.out.println("All persons:");
        List<Person> people = List.of(p1, p2, p3, p4);
        people.forEach(System.out::println);
        List<Person> adults = people.stream()
                                    .filter(p -> p.age() >= 18)
                                    .collect(Collectors.toList());

        System.out.println("\nAdults (age >= 18):");
        adults.forEach(System.out::println);
    }
}