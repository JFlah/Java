import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Jack on 10/26/2015.
 */
public class Fib {
    static ArrayList<Integer> fibCache = new ArrayList<Integer>();
    static ArrayList<Integer> fib = new ArrayList<Integer>();

    public int cacheFib(int position){
        // Checks if it is already in our cache
        if (fibCache.get(position) != null){
            int returnVal = fibCache.get(position);
            return returnVal;
        }
        // If not, call our work function and add it to the cache
        int returnVal = calcFib(position);
        fibCache.add(returnVal);
        return returnVal;
    }

    public int calcFib(int position){
        if (position == 0 || position == 1) {
            return position;
        }

        cacheFib(position);

        return calcFib(position-1) + calcFib(position-2);
    }

    public static void main(String[] args) {

    }
 }
