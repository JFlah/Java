import java.io.*;
import java.util.*;

public class TextGenerator {

    public static void main(String[] args) throws IOException{
        //TODO: REMOVE
        args = new String[] {"2","6","src/mytest.txt"};
        //TODO: END REMOVE

        MapADT<String,List<Character>> myMap = new MyHashMap<String, List<Character>>();
        if(args.length != 3){
            System.out.println("Incorrect amount of arguments.");
            System.exit(1);
        }
        int k = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        String file = args[2];
        StringBuilder txt = new StringBuilder();
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        }
        catch (FileNotFoundException exception) {
            System.out.println("The file you are searching for was not found.");
            System.exit(2);

        }
        int nextChar;
        String currentInput = "";
        String beginning = "";
        try {
            while ((nextChar = reader.read()) != -1)  {
                char c = (char) nextChar;
                if(currentInput.length() == k){
                    List<Character> currList = myMap.get(currentInput);
                    if(currList == null){
                        List<Character> temp = new ArrayList<Character>();
                        temp.add(c);
                        myMap.put(currentInput, temp);
                    } else {
                        currList.add(c);
                    }
                    currentInput = currentInput.substring(1)+c;
                } else {
                    currentInput += c;
                    beginning += c;
                }

            }
        } catch (IOException e) {
            System.err.println("Error reading from file "+file+": "+e.getMessage());
            System.exit(4);
        }
        System.out.println("Printing " + m + " characters.");
        Random rng = new Random();
        String output = beginning;
        for (int i = 0; i < m; i++) {
            List<Character> squ = myMap.get(beginning);
            int rand = rng.nextInt(squ.size());
            char p= squ.get(rand);
            output += p;
            beginning = beginning.substring(1) + p;
        }
        System.out.println(output);



    }
}
