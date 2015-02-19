/**
 * This class contains the code to compute the relevance score of a
 * web page, in response to a query.  It also contains a method to
 * compare the scores of two web pages.
 *
 * @author John Donaldson (Fall 2014)
 *
 * Edited by Jack Flaherty and ALyssa Biggins
 */

import java.io.IOException;
import java.util.*;

class URLScorer implements Comparator<WebPageIndex> {

    List<String> query;

    URLScorer(List<String> query) {
        this.query = query;
    }

    public int score(WebPageIndex idx) {
        int count = 0;
        for(String squa : query){
            int c = idx.getLocations(squa).size();
            count += c;
        }
        return count;
    }

    public int compare(WebPageIndex idx1, WebPageIndex idx2) {
        int ap = score(idx1);
        int bp = score(idx2);
        if (ap > bp) {
            return 1;
        } if (ap < bp) {
            return -1;
        }
        return 0;
    }

    public static void main(String[] args) throws IOException{
        WebPageIndex wpx = new WebPageIndex("http://google.com");
        System.out.println(wpx);
        List<String> urls = new ArrayList<String>();
        urls.add("google");
        urls.add("advertising");
        urls.add("youtube");
        URLScorer hh = new URLScorer(urls);
        System.out.println(hh.score(wpx));


    }

}
