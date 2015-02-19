/**
 * Compare two string objects using lexicographic ordering.
 */

import java.util.Comparator;

class StringComparator implements Comparator<String> {

    public int compare(String s1, String s2){
        return s1.compareTo(s2);
    }

}
