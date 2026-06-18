import java.util.Scanner;
public class Ex20_TryCatchExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter numerator: ");
        int a = sc.nextInt();

        System.out.print("Enter denominator: ");
        int b = sc.nextInt();

        try {
            int result = a / b;
            System.out.println("Result: " + a + " / " + b + " = " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero. (" + e.getMessage() + ")");
        }
    }
}