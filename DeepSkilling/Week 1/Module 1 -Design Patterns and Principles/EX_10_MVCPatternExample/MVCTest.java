public class MVCTest {
    public static void main(String[] args) {
        Student student = new Student("John Doe", "S001", "B+");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);

        controller.updateView();

        controller.setStudentName("Jane Doe");
        controller.setStudentGrade("A");

        controller.updateView();
    }
}
