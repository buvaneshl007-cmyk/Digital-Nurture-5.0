import java.util.Scanner;
public class Ex14_ArraySumAndAverage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many elements? ");
        int count = sc.nextInt();
        double[] arr = new double[count];

        for (int i = 0; i < count; i++) {
            System.out.print("Element [" + (i + 1) + "]: ");
            arr[i] = sc.nextDouble();
        }

        double sum = 0;
        for (double val : arr) sum += val;

        double average = sum / count;

        System.out.println("Sum     = " + sum);
        System.out.println("Average = " + average);
    }
}