public class EmployeeTest {
    public static void main(String[] args) {
        EmployeeArray employees = new EmployeeArray(2);

        employees.add(new Employee("E001", "Alice Johnson", "Manager", 75000));
        employees.add(new Employee("E002", "Bob Smith", "Developer", 65000));
        employees.add(new Employee("E003", "Carol White", "Designer", 60000)); // triggers resize

        System.out.println("All employees:");
        employees.traverse();

        System.out.println("\nSearch E002: " + employees.search("E002"));

        employees.delete("E001");
        System.out.println("\nAfter deleting E001:");
        employees.traverse();

        System.out.println("\nTotal employees: " + employees.size());
    }
}
