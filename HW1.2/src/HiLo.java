import java.util.Random;
import java.util.Scanner;

/**
 * Created by Jack on 6/13/2015.
 */
public class HiLo {
    public static void main(String[] args) {
        Random rnd = new Random();
        int target = rnd.nextInt(1000)+1;

        //System.out.println("Guess = " + target);

        System.out.println("Let's play a game!\nI'm thinking " +
                "of a number between 1 and 1000\n" +
                "Try to guess what it is!\n");
        System.out.println("Enter a guess: ");

        int userGuess = -1;
        int guesses = 0;

        Scanner input = new Scanner(System.in); // I'm reading what the user typed

        while ( input.hasNextLine() ) {         // keep looping for each guess, use 'break' to exit

            //System.out.println("Enter a guess: ");

            String line = input.nextLine();     // Read the next line of input from the user

            Scanner s2 = new Scanner(line);     // s2 will let me break 'line' apart

            if  ( s2.hasNextInt() ) {           // check to see if s2 would next see an integer number
                // Yay! do something with this
                userGuess = s2.nextInt();       // read in that number
                if (userGuess==target){
                    guesses++;
                    System.out.println("You guessed my number! It took you " + guesses + " tries.");
                    break;
                }

                else if (userGuess > target){
                    System.out.println("Too high!\n");
                    guesses++;
                    System.out.println("Enter a guess: ");
                    continue;
                } else {
                    System.out.println("Too low!\n");
                    guesses++;
                    System.out.println("Enter a guess: ");
                    continue;
                }

            } else {
                // So sad, print a message and then...
                System.out.println("That's not a number, try again. +1 guess penalty.\n");
                guesses++;
                continue;                       // jump back to the top of the while loop
            }

        /* probably more here! This is just an example, you know? */

        }
    }
}
