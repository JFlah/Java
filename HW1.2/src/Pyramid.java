/**
 * Created by Jack on 6/13/2015.
 */
public class Pyramid {
    public static void main(String[] args) {

        if (args.length != 1){
            System.out.println("Usage: java Pyramid <height>");
            System.exit(1);
        }
        int height = Integer.parseInt(args[0]);

        for (int i = 1; i < height+1; i++) {
            getSpaces(height, i);
            getStars(i);
            System.out.println();
        }
    }

    public static void getSpaces(int height, int level){
        for (int i = 0; i < height-level; i++) {
            System.out.print(" ");
        }
    }

    public static void getStars(int level){
        for (int i = 0; i < 2*level-1; i++) {
            System.out.print("*");
        }
    }
}
