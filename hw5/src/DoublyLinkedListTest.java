import java.io.*;
import java.util.*;
import junit.framework.TestCase;

/**
 * JUnit test file for DoublyLinkedList Class
 * Uses test-style methods to perform alleged actions of methods from DoublyLinkedList
 * Uses assertions from JUnit to determine whether or not actions were performed as required
 * Author: Jack Flaherty
 */
public class DoublyLinkedListTest extends TestCase {

    public void testDoublyLinkedList() {
        DoublyLinkedList<Integer> test = new DoublyLinkedList<Integer>();
        LinkedList<Integer>   standard = new LinkedList<Integer>();
        assertEquals("Size after construction", standard.size(), test.size());
    }

    public void testSize() {
        DoublyLinkedList<Integer> test = new DoublyLinkedList<Integer>();
        LinkedList<Integer>   standard = new LinkedList<Integer>();
        assertEquals( "Size after construction", standard.size(), test.size());
        test.add(0,5);
        standard.add(0,5);
        assertEquals( "Size after add", standard.size(), test.size());
    }

    public void testAdd() throws FileNotFoundException {
        DoublyLinkedList<String> test = new DoublyLinkedList<String>();
        LinkedList<String> standard = new LinkedList<String>();
        File test1 = new File("test1.txt");
        Scanner scan = new Scanner(test1);

        while (scan.hasNextLine()) {
            String word = scan.nextLine();
            test.add(word);
            standard.add(word);
        }
        assertEquals("Size after construction", standard.size(), test.size());

        for (int i = 0; i < standard.size(); i++) {
            assertEquals("Each element of test and standard:", standard.get(i), test.get(i));
        }

    }

    public void testAddFront() throws FileNotFoundException {
        DoublyLinkedList<String> test = new DoublyLinkedList<String>();
        LinkedList<String> standard = new LinkedList<String>();
        File test1 = new File("test1.txt");
        Scanner scan = new Scanner(test1);

        while (scan.hasNextLine()) {
            String s = scan.nextLine();
            test.add(0, s);
            standard.add(0, s);
        }
        assertEquals("Size after construction", standard.size(), test.size());
    }

    public void testAddMiddle() throws FileNotFoundException {
        DoublyLinkedList<String> test = new DoublyLinkedList<String>();
        LinkedList<String> standard = new LinkedList<String>();
        File test1 = new File("test1.txt");
        Scanner scan = new Scanner(test1);

        while (scan.hasNextLine()) {
            String s = scan.nextLine();
            test.add(test.size()/2, s);
            standard.add(standard.size()/2, s);
        }
        assertEquals("Size after construction", standard.size(), test.size());
    }

    public void testSet() throws FileNotFoundException {
        DoublyLinkedList<String> test = new DoublyLinkedList<String>();
        LinkedList<String> standard = new LinkedList<String>();
        File test1 = new File("test1.txt");
        Scanner scan = new Scanner(test1);

        while (scan.hasNextLine()) {
            String s = scan.nextLine();
            test.add(s);
            standard.add(0, s);
        }

        for (int i = 0; i < test.size()/2; i++) {
            String lol = test.get(i);
            String end = test.set(test.size()-1-i, lol);
            test.set(i, end);

        }

        assertEquals("Size after reversal: ", standard.size(), test.size());

        for (int i = 0; i < test.size(); i++) {
            assertEquals("Each element: ", standard.get(i), test.get(i));
        }
    }

    public void testRemove() throws FileNotFoundException {
        DoublyLinkedList<String> test = new DoublyLinkedList<String>();
        LinkedList<String> standard = new LinkedList<String>();
        File test1 = new File("test1.txt");
        Scanner scan = new Scanner(test1);

        while (scan.hasNextLine()) {
            String s = scan.nextLine();
            test.add(0,s);
            standard.add(0,s);
        }

        DoublyLinkedList<String> testEvens = new DoublyLinkedList<String>();
        LinkedList<String> standardEvens = new LinkedList<String>();

        for (int i = 0; i < test.size()/2; i++) {
            testEvens.add(test.remove(i));
            standardEvens.add(standard.remove(i));
        }
    }
    
    public void testContains() {
      DoublyLinkedList<Integer> test = new DoublyLinkedList<Integer>();
      
      assertFalse("Actual has nothing: ", test.contains(5));
      
      test.add(5);
      
      assertTrue("After add, contains: ", test.contains(5));
      
    }

    public void testIsEmpty() throws FileNotFoundException {
        DoublyLinkedList<String> test = new DoublyLinkedList<String>();
        LinkedList<String> standard = new LinkedList<String>();

        assertTrue("If actual is empty: ", test.isEmpty());
        assertTrue("If expected is empty: ", standard.isEmpty());

        File test1 = new File("test1.txt");
        Scanner scan = new Scanner(test1);

        while (scan.hasNextLine()) {
            String s = scan.nextLine();
            test.add(s);
            standard.add(s);
        }


        for (int i = 0; i < test.size(); i++) {
            assertFalse("If actual is now not empty: ", test.isEmpty());
            assertFalse("If expected is now not empty: ", standard.isEmpty());
        }
    }

    public void testClear() throws FileNotFoundException {
        DoublyLinkedList<String> test = new DoublyLinkedList<String>();
        LinkedList<String> standard = new LinkedList<String>();

        File test1 = new File("test1.txt");
        Scanner scan = new Scanner(test1);

        while (scan.hasNextLine()) {
            String s = scan.nextLine();
            test.add(s);
            standard.add(s);
        }

        assertFalse("If actual is not empty: ", test.isEmpty());
        assertFalse("If expected is not empty: ", standard.isEmpty());

        test.clear();
        standard.clear();

        assertTrue("If actual is empty: ", test.isEmpty());
        assertTrue("If expected is empty: ", standard.isEmpty());

    }
}
  

    
    
     

