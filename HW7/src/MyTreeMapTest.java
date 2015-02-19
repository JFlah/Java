import junit.framework.TestCase;
import java.util.*;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 * Tests MyTreeMap methods METICULOUSLY!
 * Authors: Jack Flaherty and Alyssa Biggins
 */
public class MyTreeMapTest extends TestCase {

    /**
     * A test method.
     * (Replace "X" with a name describing the test.  You may write as
     * many "testSomething" methods in this class as you wish, and each
     * one will be called when running JUnit over this class.)
     */

    public void testPutAndGet() {
      TreeMap<Integer, String> standard = new TreeMap<Integer, String>();
      MyTreeMap<Integer, String> test = new MyTreeMap<Integer, String>();
      
      standard.put(0, "Hey");
      test.put(0, "Hey");
      standard.put(1, "Hello");
      test.put(1, "Hello");
      standard.put(2, "Hi");
      test.put(2, "Hi");
      
      assertEquals("Comparing standard to test:", standard.get(0), test.get(0));
      assertEquals("Comparing another standard to test", standard.get(2), test.get(2));
      
      standard.put(1, "Yo");
      test.put(1, "Yo");
      
      assertEquals(standard.get(1), test.get(1));
    }

    public void testRestructure() {
      MyTreeMap<Integer, String> test = new MyTreeMap<Integer, String>();
      
      // Left-Left Test
      test.put(3, "Hi");
      test.put(2, "Hey");
      test.put(1, "Yo");
      
      assertEquals("Height before (2), now after restructuring: ", 1, test.getHeight());
      
      // Right-Right Test
      test = new MyTreeMap<Integer, String>();
      
      test.put(1, "Hi");
      test.put(2, "Hey");
      test.put(3, "Yo");
      
      assertEquals("Height before (2), now after restructuring: ", 1, test.getHeight());
      
      // Left-Right Test
      test = new MyTreeMap<Integer, String>();
      
      test.put(3, "Hi");
      test.put(1, "Hey");
      test.put(2, "Yo");
      
      assertEquals("Height before (2), now after restructuring: ", 1, test.getHeight());
      
      // Right-Left Test
      test = new MyTreeMap<Integer, String>();
      
      test.put(1, "Hi");
      test.put(3, "Hey");
      test.put(2, "Yo");

      assertEquals("Height before (2), now after restructuring: ", 1, test.getHeight());
      
      
      
      
    }

}
