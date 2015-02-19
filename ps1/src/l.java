import java.io.File;
import java.util.*;
import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class MyArrayListTest extends TestCase {

    /**
     * A test method.
     * (Replace "X" with a name describing the test.  You may write as
     * many "testSomething" methods in this class as you wish, and each
     * one will be called when running JUnit over this class.)
     */
    public void testMyArrayList() {
        MyArrayList<Integer> test = new MyArrayList<Integer>();
        ArrayList<Integer>   standard = new ArrayList<Integer>();
        assertEquals("Size after construction", 5, test.size()); // Standard.size() is len 0, will not work with this(5)
    }

    public void testSize() {
        MyArrayList<Integer> test = new MyArrayList<Integer>();
        ArrayList<Integer>   standard = new ArrayList<Integer>();
        assertEquals( "Size after construction", 5, test.size());
        test.add(0,5); // Standard.size() had no element [0] to add to so, this is why I used 5
        standard.add(0,5); // standard has no element 0 so I used 5 above instead, useless line
        assertEquals( "Size after add", 5, test.size());
    }

    public void testAdd() {
        MyArrayList<String> test = new MyArrayList<String>();
        ArrayList<String> standard = new ArrayList<String>();
        File test1 = new File("test1.txt");
        Scanner scan = new Scanner(test1);
        while (scan.hasNextLine()) {
            test.add(scan.nextLine());
            standard.add(scan.nextLine());
        }
        assertEquals("Size after construction", standard.size(), test.size());

        for (int i = 0; i < test.length; i++) {
            assertEquals("Each element of test and standard:", standard[i], test[i]);
        }

    }

    public void testAddFront() {
        continue;
    }

    public void testAddMiddle() {
        continue;
    }


}