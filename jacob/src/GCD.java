/**
 * Created by Jack on 1/26/2015.
 */
public class GCD {
    public static String gcd(int a, int b) {

        while (a > b) {
            System.out.println("HERE, a is" + a + "B is: "+ b);
            return gcd(a-b, b);
        }

        return "a is: " + a + " b is: " + b;
    }

    public static void main(String[] args) {

        System.out.println(gcd(15, 5));

    }

}
