import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 5;
        int attemptsLimit = 7;
        int rounds = 0;
        int totalAttempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        int userGuess;  // Move the declaration outside the inner do-while loop

        do {
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;

            System.out.println("\nRound " + (rounds + 1) + ": Guess the number between " + minRange + " and " + maxRange);

            do {
                System.out.print("Enter your guess: ");
                userGuess = scanner.nextInt();  // Assign user input to the existing variable
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                } else if (userGuess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

            } while (attempts < attemptsLimit && randomNumber != userGuess);

            totalAttempts += attempts;
            rounds++;

            System.out.println("\nRound Summary: You took " + attempts + " attempts in this round.");

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();

            if (!playAgain.equals("yes")) {
                break;
            }

        } while (true);

        System.out.println("\nGame Over! You played " + rounds + " rounds and took a total of " + totalAttempts + " attempts.");
        scanner.close();
    }
}
