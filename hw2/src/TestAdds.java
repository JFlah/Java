/**
 * File instantiates MyArrayList object of type Integer
 * Adds integers 1 through 1 million in loop, prints the value every 10k adds
 * Demonstrates difference in time-consumption of .add(i) and .add(0,1)
 * Author: Jack Flaherty
 * */

public class TestAdds {

    public static void main(String[] args) {

        MyArrayList<Integer> testAdd = new MyArrayList<Integer>();

        for (int i = 1; i < 1000001; i++) {
            testAdd.add(0,i);
            if (i%10000 == 0) {
                System.out.println(i);
            }
        }
    }
}
