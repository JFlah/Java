import java.util.*;
import java.io.*;

class ListTimer {
    // default values for number of iterations and interval size
    static final int NITER = 5, INTERVAL = 5000, REPS = 1;
    static private int reps;

    public static void doWork(Collection<String> lexicon, List<String> words, int numIter, int interval, String lfile, String wfile)
            throws FileNotFoundException {

        System.out.printf("Lexicon: %s  Document: %s  Class: %s\n", lfile, wfile, lexicon.getClass().getCanonicalName());
        System.out.println("==========================================");
        System.out.println(" words      elapsed    pct of");
        System.out.println("searched   time (ms) found words");
        for (int k = 1; k <= numIter; k++) {
            Scanner lexScanner = new Scanner(new File(lfile));
            lexicon.clear();
            int count = 0;
            while (lexScanner.hasNext()) {
                lexicon.add(lexScanner.next().toLowerCase());
            }

            long startTime = System.currentTimeMillis();
            int foundcount = 0, totalwords = 0;
            for (int j = 0; j < reps; j++) {
                int wordcount = 0;
                Iterator<String> i = words.iterator();
                while (wordcount < interval * k && i.hasNext()) {
                    String word = i.next().toLowerCase();
                    wordcount++;
                    if (lexicon.contains(word)) {
                        foundcount++;
                    }
                }
                totalwords += wordcount;
            }
            long endTime = System.currentTimeMillis();
            long elapsed = endTime - startTime;
            double pct = Math.round((foundcount / (double) totalwords) * 1000) / 10.0;
            System.out.printf("%8d    %8d    %6.1f%%\n", totalwords, elapsed, pct);
        }
    }


    public static void main(String[] args) throws FileNotFoundException {

        if (args.length < 2) {
            System.out.println("Usage: java CheckApp <lex-file> <test-file>");
            System.exit(1);
        }
        List<String> wordList = new ArrayList<String>();

        Scanner docScanner = new Scanner(new File(args[1]));
        while (docScanner.hasNext()) {
            wordList.add(docScanner.next());
        }

        int niter = NITER, interval = INTERVAL;
        reps = REPS;

        if (args.length > 2) {
            try {
                interval = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.out.println("Usage: java CheckApp <lex-file> <test-file> <step-size>");
                System.exit(1);
            }
        }

        if (args.length > 3) {
            try {
                niter = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                System.out.println("Usage: java CheckApp <lex-file> <test-file> <step-size> <number-of-iterations>");
                System.exit(1);
            }
        }

        if (args.length > 4) {
            try {
                reps = Integer.parseInt(args[4]);
            } catch (NumberFormatException e) {
                System.out.println("Usage: java CheckApp <lex-file> <test-file> <step-size> <number-of-iterations>");
                System.exit(1);
            }
        }


        doWork(new MyTreeSet<String>(), wordList, niter, interval, args[0], args[1]);
        doWork(new TrieSet(), wordList, niter, interval, args[0], args[1]);
        doWork(new java.util.HashSet<String>(), wordList, niter, interval, args[0], args[1]);
    }
}
      