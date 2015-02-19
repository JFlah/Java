import java.util.Scanner;
import java.util.Random;

/*
Program generates random number, then prompts user to guess, giving "too high" "too low" until
number is correct.
Author: Jack Flaherty
 */

public class HiLo {

    public static void main(String[] args) {

        System.out.println("Let's play a game!\nI'm thinking of a number between 1 and 1000\nTry to guess what it is!");

        Random rnd = new Random();
        int target = rnd.nextInt(1000)+1;
        int userGuess;

        Scanner input = new Scanner(System.in);

        int numGuesses=0;

        while (input.hasNextLine()) {
            String line = input.nextLine();
            Scanner s2 = new Scanner(line);

            if (s2.hasNextInt()) {
                numGuesses++;
                userGuess = s2.nextInt();
            } else {
                System.out.print("That's not a number, try again.");
                continue;
            }

            if (userGuess > target) {
                System.out.println("Too high!");
                continue;
            }

            if (userGuess < target) {
                System.out.println("Too low!");
                continue;
            }

            if (userGuess == target) {
                System.out.println("You guessed my number! It took you " + numGuesses + " tries"); // How many tries?
                break;
            }
        }
    }
}