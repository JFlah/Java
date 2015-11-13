import java.util.Scanner;

/**
 * Created by Jack on 6/30/2015.
 */
public class Redactor {
    public static void main(String[] args) {
        String[] wordlist = args;

        Scanner doc = new Scanner(System.in);

        while (doc.hasNextLine()) {
            String line = doc.nextLine();

            for (String s : wordlist){
                line = line.replace(s, "XXXXX");
            }
            System.out.println(line);
        }

    }
}
