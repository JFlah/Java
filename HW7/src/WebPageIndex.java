import com.sun.webkit.WebPage;

import java.io.IOException;
import java.util.*;

/**
 * Class which creates a MyTreeMap of the tokens (words) on a webpage and has various methods to manipulate and peek
 * at the instantiation of this treemap.
 * Authors: Jack Flaherty and Alyssa Biggins
 */

public class WebPageIndex {

    private MyTreeMap<String, List<Integer>> myTree = new MyTreeMap<String, List<Integer>>();
    private String baseURL;
    private int wordCount = 0;


    WebPageIndex(String url) throws IOException {
        this.baseURL = url;
        doIndex();
    }

    public void doIndex() throws IOException {
        HTMLScanner scan = new HTMLScanner(this.baseURL);
        int index = 0;
        while (scan.hasNext()) {
            wordCount++;
            String word = scan.next().toLowerCase();
            if (!contains(word)) {
                List<Integer> currentValue = new LinkedList<Integer>();
                currentValue.add(index);
                myTree.put(word, currentValue);
            } else {
                myTree.get(word).add(index);
            }
            index++;
        }
    }

    public int getWordCount() {
        // Returns the instance variable we have
        return wordCount;
    }

    public String getUrl() {
        // Returns the instance variable
        return baseURL;
    }

    public boolean contains(String s) {
        // get(s) returns null if s is not in the MyTreeMap
        return myTree.get(s) != null;
    }

    public int getCount(String s) {
        // get(s) gives the List<Integer> of the string specified, the size() of that list is the number of times it appears
        return myTree.get(s).size();
    }

    public double getFrequency(String s) {
        // Get the number of times the word has appeared and divide it by the total num of words on page
        double count = getCount(s);
        return count / wordCount;
    }

    public List<Integer> getLocations(String s) {
        if (contains(s)) {
            return myTree.get(s);
        }
        return new ArrayList<Integer>();
    }

    public Iterator<String> words() {
        // keys() returns all of the keys of myTree, which are the tokens (words) of the webpage
        return myTree.keys();
    }

    public void formattedPrint(){
        Iterator<String> keys = words();
        while(keys.hasNext()){
            String word = keys.next();
            System.out.format("%14s %.5f", word, getFrequency(word));
            System.out.println(" " + getLocations(word));
        }
    }

    public String toString() {
        // returns MyTreeMaps toString() value
        return myTree.toString();
    }

   /*
    * additional features you might add to support multi-word phrases
    *
    * work on these after you have the previous methods working correctly
    */

    public boolean containsPhrase(String s) {
        // TODO - implement me! - add some comments too!
        return false;
    }

    public int getPhraseCount(String s) {
        // TODO - implement me! - add some comments too!
        return -1;
    }

    public double getPhraseFrequency(String s) {
        // TODO - implement me! - add some comments too!
        return -1.0;
    }

    public List<Integer> getPhraseLocations(String s) {
        // TODO - implement me! - add some comments too!
        return null;
    }

    public static void main(String[] args) throws IOException {
        String url = "http://bc.edu";
        args = new String[] {url};

        if (args.length < 1) {
            System.out.println("Usage: java WebPageIndex <url>");
            System.exit(1);
        }
        try {
            String URL = args[0];
            WebPageIndex wpi = new WebPageIndex(URL);
            System.out.println("Frequency and index of words in " + args[0]);
            wpi.formattedPrint();

        } catch (IOException e) {
            System.out.println("Improper URL.");
        }
    }
}
