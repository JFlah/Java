import java.util.*;
import java.io.*;

/**
 * Program detects how many digits in each year of each provided state are present in file. Then it gives a frequency
 * of these digits, and there is one * provided for each percent.
 * Author: Jack Flaherty
 */


public class Benford {
    public static final int MAXWIDTH = 50;

    public static String getStars(int numStars){
        String stars = "";
        for (int i=0; i < numStars; i++){
            stars+="*";
        }
        return stars;
    }

    public static void main(String[] args) {



        Scanner input = new Scanner(System.in);

        int [] count = new int [10];



        System.out.println("Welcome to the Benford Analysis Program");

        while(input.hasNextLine()){
            String line = input.nextLine();
            Scanner word = new Scanner(line);

            while(word.hasNext()){
                String newWord = word.next();

                if(Character.isDigit(newWord.charAt(0))){
                    String digit = newWord.substring(0,1);
                    int num = Integer.parseInt(digit);
                    count[num]++;
                }

            }

        }

        int total = 0;
        for(int i = 0; i< count.length;i++){
            total += count[i];
        }

        for(int i=0; i<count.length;i++){
            double freq = ((count[i])*1.0/total)*100.0;
            long numStars = Math.round(((count[i]*1.0)/total)*MAXWIDTH);
            String stars = getStars((int)numStars);
            System.out.printf("%d %8d %4.1f%% : ", i, count[i], freq );
            System.out.println(stars);

        }
    }
}