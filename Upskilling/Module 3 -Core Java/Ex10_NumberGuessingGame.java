import java.util.Random;
import java.util.Scanner;

public class Ex10_NumberGuessingGame {
    public static void main(String[] args) {
        Random rand = new Random();
        int secret  = rand.nextInt(100) + 1; 
        Scanner sc  = new Scanner(System.in);
        int attempts = 0;

        System.out.println("Guess the number between 1 and 100!");

        while (true) {
            System.out.print("Your guess: ");
            int guess = sc.nextInt();
            attempts++;

            if      (guess < secret) System.out.println("Too low!  Try again.");
            else if (guess > secret) System.out.println("Too high! Try again.");
            else {
                System.out.println("Correct! You guessed it in " + attempts + " attempt(s).");
                break;
            }
        }
    }
}