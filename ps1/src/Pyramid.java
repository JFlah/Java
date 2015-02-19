import java.util.Random;

/**
 * Program builds a pyramid of a height specified by the user
 * Created by Jack Flaherty
 */
public class Pyramid {
    public String getBuffer(int height, int level) {
        int spaces = height-level-1;
        String buffer = "";
        for (int i=0; i<spaces; i++){
            buffer += " ";
        }
        return buffer;

    }

    public String getStars(int level){
        String stars = "";
        int loops = level*2+1;
        for (int i=0; i < loops; i++){
            stars += "*";
        }
        return stars;
    }

    public static void main(String[] args) {
        Pyramid p = new Pyramid();
        if (args.length != 1) {

            System.out.println("Usage: java Pyramid <height>");
            System.exit(1);

        }

    int height = Integer.parseInt(args[0]);

        for (int i = 0; i < height; i++) {
            String buffer = p.getBuffer(height, i);
            String stars = p.getStars(i);
            System.out.println(buffer+stars);
        }

    }
}
