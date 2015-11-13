import java.math.BigInteger;

/**
 * Created by Jack on 11/7/2015.
 */
public class Sissa {
    /*
        Change SQUARES to test different sized boards

        Recursive works for: 64+ squares
        Iterative works for: 63 squares
        Closed works for:    62 squares, off by 1 at 63 squares, wrong at 64
     */
    public static int SQUARES = 8;

    private static final BigInteger START_GRAINS = new BigInteger("1");
    private static final int START_GRAINS_LONG = 1;
    private static final BigInteger TWO = new BigInteger("2"); // For ease

    // Recursive
    public static BigInteger getTotalGrains(int squares, BigInteger grains){
        if (squares==0) {
            return BigInteger.ZERO;
        }
        if (squares==1){
            return grains;
        }
        return grains.add(getTotalGrains(squares-1, grains.multiply(TWO)));
    }

    // Iterative
    public static long getTotalGrainsIterative(int squares, long grains){
        long totalGrains = 1;

        if (squares == 0){
            return 0;
        }

        for (int i = 0; i < squares-1; i++) {
            grains *= 2;
            totalGrains += grains;
        }
        return totalGrains;
    }

    // Closed
    public static long getTotalGrainsClosed(int squares){
        return (long) Math.pow(2, squares)-1;
    }

    public static void main(String[] args) {
        System.out.println("For " + SQUARES + " square(s):");
        System.out.println("R: " + getTotalGrains(SQUARES, START_GRAINS));
        System.out.println("I: " + getTotalGrainsIterative(SQUARES, START_GRAINS_LONG) );
        System.out.println("C: " + getTotalGrainsClosed(SQUARES));
    }
}
