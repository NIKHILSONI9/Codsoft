import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        String playAgain;
        
        do {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 10;
            boolean hasWon = false;
            
            System.out.println("\nNew Round: Guess the number between 1 and 100!");
            
            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + "/" + maxAttempts + ": Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;
                
                if (guess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    hasWon = true;
                    break;
                } else if (guess > numberToGuess) {
                    System.out.println("Your guess is too high.");
                } else {
                    System.out.println("Your guess is too low.");
                }
            }
            
            if (!hasWon) {
                System.out.println("Sorry! You've used all attempts. The correct number was: " + numberToGuess);
            } else {
                score++;
            }
            
            System.out.println("Your current score: " + score);
            System.out.print("Do you want to play again? (y/n): ");
            playAgain = scanner.next();
            
        } while (playAgain.equalsIgnoreCase("y"));
        
        System.out.println("Thanks for playing! Your final score: " + score);
        scanner.close();
    }
}
