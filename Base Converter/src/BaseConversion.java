import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Jack on 11/13/2015.
 */
public class BaseConversion {
    private static int initialDecimal;
    private static int desiredBase;

    private static int currentQuotient;
    private static int currentRemainder;

    private static String currRemainString = "";

    private static ArrayList<String> result = new ArrayList<String>();

    public static void main(String[] args) {
        getNumbers();
        convert(initialDecimal, desiredBase);
        printResult(result);
    }

    public static int convert(int decimal, int base){
        currentQuotient = decimal/base;
        currentRemainder = decimal%base;

        if (currentRemainder > 9) {
            currRemainString = convertNum(currentRemainder);
        }

        if (currentQuotient == 0) {
            if (currentRemainder > 9) {
                result.add(0, currRemainString);
            } else {
                result.add(0, Integer.toString(currentRemainder));
            }
            return currentRemainder;
        }

        if (currentRemainder > 9){
            result.add(0, currRemainString);
        } else {
            result.add(0, Integer.toString(currentRemainder));
        }
        return convert(currentQuotient, base);
    }

    public static String convertNum(int i) {
        switch (i) {
            case 10: return "A";
            case 11: return "B";
            case 12: return "C";
            case 13: return "D";
            case 14: return "E";
            case 15: return "F";
            case 16: return "G";
            case 17: return "H";
            case 18: return "I";
            case 19: return "J";
            case 20: return "K";
            case 21: return "L";
            case 22: return "M";
            case 23: return "N";
            case 24: return "O";
            case 25: return "P";
            case 26: return "Q";
            case 27: return "R";
            case 28: return "S";
            case 29: return "T";
            case 30: return "U";
            case 31: return "V";

            // For good measure

            case 32: return "W";
            case 33: return "X";
            case 34: return "Y";
            case 35: return "Z";
            default: return "";
        }
    }

    public static void printResult(ArrayList<String> result) {
        System.out.print("Result in base " + desiredBase + ": ");
        for (String s : result) {
            System.out.print(s + " ");
        }
    }

    public static void getNumbers() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter your initial decimal:");
        initialDecimal = reader.nextInt();
        System.out.println("Enter your desired base (1-32):");
        desiredBase = reader.nextInt();
        System.out.println("Your decimal is " + initialDecimal + ". Converting to base " + desiredBase + ":");
    }
}
