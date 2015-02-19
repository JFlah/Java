/**
 * Created by Alyssa and Jack on 11/9/14.
 * Class should go through a series of urls, count the words, and make an index.
 */
import java.io.*;
import java.util.*;

public class ProcessQueries {
    public static final boolean DEBUG = false;
    private String hap;
    private int count;

    public ProcessQueries(String file, int count){
        this.hap = file;
        this.count=count;
        doProcess();
    }

    public void doProcess(){
        Scanner sca = new Scanner(System.in);
        System.out.print("Enter a query (-1 to exit): ");
        while(sca.hasNext()) {
            String searchTerm = sca.nextLine();
            if (searchTerm.equals("-1")){
                System.exit(0);
            }
            List<String> queryList = Arrays.asList(searchTerm.toLowerCase().split(" "));
            URLScorer comp = new URLScorer(queryList);
            MyPriorityQueue<WebPageIndex> mpq = new MyPriorityQueue<WebPageIndex>(comp);
            try {
                List<WebPageIndex> jk = createIndex();
                for (WebPageIndex kag : jk) {
                    mpq.add(kag);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            displayResults(mpq, comp);
            System.out.print("Enter another query (-1 to exit): ");
        }
    }

    public List<WebPageIndex> createIndex() throws IOException{
        List<WebPageIndex> indexes = new ArrayList<WebPageIndex>();
        File fileIn = new File(hap);
        Scanner sc = new Scanner(fileIn);
        while(sc.hasNext()) {
            String line = sc.nextLine();
            if (DEBUG) {
                System.out.println("Scanning: " + line);
            }
            WebPageIndex wpx = new WebPageIndex(line);
            indexes.add(wpx);
        }
        return indexes;
    }

    public void displayResults(MyPriorityQueue<WebPageIndex> mpq, URLScorer comp){
        System.out.println("Top " + count + " results:");
        System.out.println("Num Score URL" );
        for (int i = 0; i < count; i++) {
            WebPageIndex idx = mpq.remove();
            System.out.println(i + "\t" + comp.score(idx) + "\t" + idx.getUrl());
        }
    }

    public static void main(String[] args) {
        args = new String[]{"urls/urls-test", "10"};

        // If not specified, show 5 files.

        if (args.length < 1){
            //Write how to use it
            System.out.println("To use: java ProcessQueries url-file [count]");
            System.exit(1);
        }

        int count = 5;
        if (args.length == 2){
            count = Integer.parseInt(args[1]);
        }
        String searchFile = args[0];

        ProcessQueries p = new ProcessQueries(searchFile, count);
    }
}
