import junit.framework.TestCase;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class MyPriorityQueueTest extends TestCase {
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testAddAndRemove() {
    StringComparator sc = new StringComparator();
    MyPriorityQueue<String> test = new MyPriorityQueue<String>(sc);
   
    test.add("x");
    test.add("c");
    test.add("h");

      assertEquals("These should equal:",test.remove(), "x");
      assertEquals("These should equal:",test.remove(), "h");
      assertEquals("These should equal:",test.remove(), "c");  
  }
  
      public void testSiftUp() {
        StringComparator sc = new StringComparator();
        MyPriorityQueue<String> test = new MyPriorityQueue<String>(sc);
        
        test.add("a");
        test.add("b");
        test.add("c");
        test.add("d");


        assertEquals("These will sift up:", test.remove(),"d");
        assertEquals("These will sift up:", test.remove(),"c");
        assertEquals("These will sift up:", test.remove(),"b");
        assertEquals("These will sift up:", test.remove(),"a");
        
        
    }
  public void testSiftDown() {
        StringComparator sc = new StringComparator();
        MyPriorityQueue<String> test = new MyPriorityQueue<String>(sc);

        test.add("d");
        test.add("c");
        test.add("b");
        test.add("a");

        test.remove();
        assertEquals("These will sift down:", test.remove(), "c");
        assertEquals("These will sift down:", test.remove(), "b");
        assertEquals("These will sift down:", test.remove(), "a");



    }
}
