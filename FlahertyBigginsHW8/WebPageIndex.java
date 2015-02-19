import com.sun.tools.doclets.internal.toolkit.util.SourceToHTMLConverter;

import java.util.*;
import java.io.*;

public class WebPageIndex {
    private MyTreeMap<String, List<Integer>> workMap = new MyTreeMap<String, List<Integer>>();
    private String url;
    private Integer wordCount = 0;


    public WebPageIndex(String url) throws IOException {
        this.url = url;

        doIndex();
    }

    public void doIndex() throws IOException {
        HTMLScanner hsc = new HTMLScanner(this.url);
        int index = 0;
        while (hsc.hasNext()) {
            wordCount++;
            String word = hsc.next().toLowerCase();
            if (!contains(word)) {
                List<Integer> currValue = new LinkedList<Integer>();
                currValue.add(index);
                workMap.put(word, currValue);

            } else {
                workMap.get(word).add(index);
            }
            index++;
        }
    }

    public int getWordCount() {
        return wordCount;
    }

    public String getUrl() {
        return url;
    }

    public boolean contains(String s) {
        return workMap.get(s) != null;
    }

    public int getCount(String s) {
        return workMap.get(s).size();
    }

    public double getFrequency(String s) {
        double k = getCount(s);
        return k / wordCount;
    }

    public List<Integer> getLocations(String s) {
        if (!contains(s)) {
            return new ArrayList<Integer>();
        }
        return workMap.get(s);
    }

    public Iterator<String> words() {

        return workMap.keys();
    }

    public void formattedPrint(){
        Iterator<String> keys = words();
        while(keys.hasNext()){
            String word = keys.next();
            System.out.format("%14s %.5f",word, getFrequency(word));
            System.out.println(" "+getLocations(word));
//            System.out.printf(word + "\t\t" + String.format("%.5f",) + "\t" + getLocations(word) + "\n");
        }
    }

    public String toString() {
        return workMap.toString();
    }
   
   /* 
    * additional features you might add to support multi-word phrases 
    *
    * work on these after you have the previous methods working correctly
    */

    public boolean containsPhrase(String s) {
        ArrayList<String> splitList = new ArrayList<String>();
        s = s.toLowerCase();
        String[] words = s.split("//s+");
        int index = -10;
        for(int i = 0; i < words.length-1; i++){
            List<Integer> locs = getLocations(words[i]);
            List<Integer> nextLoc = getLocations(words[i+1]);
            boolean inList = false;
            for(Integer wordLocation : locs){
                if (nextLoc.contains(wordLocation)){
                    inList=true;
                }
            }
            if (!inList)
                return false;
        }
        return true;
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
//        String url = "http://bc.edu";
        String url = "test.txt";
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
            System.out.println("CONTAINS: " + wpi.containsPhrase("hi hi"));

        } catch (IOException e) {
            System.out.println("Improper URL.");
        }
    }

}
