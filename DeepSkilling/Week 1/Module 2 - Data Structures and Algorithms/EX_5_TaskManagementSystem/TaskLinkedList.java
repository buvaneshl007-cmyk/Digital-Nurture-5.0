/**
 * A singly linked list of Tasks with head and tail pointers.
 */
public class TaskLinkedList {

    private static class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    // O(1) - add at the tail, using the tail pointer
    public void add(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // O(n) - must traverse from head
    public Task search(String taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId().equals(taskId)) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    // O(n) - visits every node
    public void traverse() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    // O(n) - find the node (and track its predecessor) before unlinking
    public boolean delete(String taskId) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.task.getTaskId().equals(taskId)) {
                if (previous == null) {
                    head = current.next; // deleting the head
                } else {
                    previous.next = current.next;
                }
                if (current == tail) {
                    tail = previous;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }
}
