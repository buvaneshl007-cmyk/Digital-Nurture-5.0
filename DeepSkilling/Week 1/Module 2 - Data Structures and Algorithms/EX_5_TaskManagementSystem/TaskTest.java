public class TaskTest {
    public static void main(String[] args) {
        TaskLinkedList tasks = new TaskLinkedList();

        tasks.add(new Task("T001", "Design database schema", "In Progress"));
        tasks.add(new Task("T002", "Write API documentation", "Pending"));
        tasks.add(new Task("T003", "Set up CI pipeline", "Completed"));

        System.out.println("All tasks:");
        tasks.traverse();

        System.out.println("\nSearch T002: " + tasks.search("T002"));

        tasks.delete("T001");
        System.out.println("\nAfter deleting T001:");
        tasks.traverse();

        System.out.println("\nTotal tasks: " + tasks.size());
    }
}
