
import java.util.Scanner;

/**
 * Program takes in a text file and replaces all words specified with 6 X's
 * Created by Jack Flaherty
 */
public class Redactor {
    public static void main(String[] args) {
        String[] wordlist = args;
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()){
            String line = scan.nextLine();
            for (String s : wordlist){
                line = line.replace(s, "XXXXXX");
            }
            System.out.println(line);
        }
    }
}
