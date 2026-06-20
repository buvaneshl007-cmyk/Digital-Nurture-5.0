import java.util.Arrays;

/**
 * Manages employee records using a manually managed array (not ArrayList),
 * to demonstrate raw array mechanics: fixed capacity, manual resizing, and
 * O(n) search/delete due to lack of indexing.
 */
public class EmployeeArray {
    private Employee[] employees;
    private int size;

    public EmployeeArray(int initialCapacity) {
        employees = new Employee[initialCapacity];
        size = 0;
    }

    // O(1) amortized; O(n) when a resize is triggered
    public void add(Employee employee) {
        if (size == employees.length) {
            resize();
        }
        employees[size++] = employee;
    }

    private void resize() {
        employees = Arrays.copyOf(employees, employees.length * 2);
    }

    // O(n) - linear scan since the array isn't indexed by id
    public Employee search(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return employees[i];
            }
        }
        return null;
    }

    // O(n) - must visit every element
    public void traverse() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    // O(n) - find the element, then shift subsequent elements left
    public boolean delete(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }
}
