import junit.framework.TestCase;
import java.io.*;
import java.util.LinkedList;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class SieveTest extends TestCase {
  
  public void testSieve() throws FileNotFoundException {
      DoublyLinkedList<Integer> test = new DoublyLinkedList<Integer>();
      LinkedList<Integer> standard = new LinkedList<Integer>();
      DoublyLinkedList<Integer> test2 = new DoublyLinkedList<Integer>();
      LinkedList<Integer> standard2 = new LinkedList<Integer>();
      
      
      standard.add(2);     
      standard.add(3);
      standard.add(5);
      standard.add(7);
      standard.add(11);      
      standard.add(13);    
      standard.add(17); 
      standard.add(19); 
      standard.add(23); 
      standard.add(29); 
      standard.add(31); 
      standard.add(37); 
      standard.add(41); 
      standard.add(43); 
      standard.add(47); 
      
      
      
      
      for(int i =2; i <=50; i++){
        test.add(i);
      }
      
      standard2.add(907);
      standard2.add(911);
      standard2.add(919);
      standard2.add(929);
      standard2.add(937);
      standard2.add(907);
      standard2.add(941);
      standard2.add(947);
      standard2.add(953);
      standard2.add(967);
      standard2.add(971);
      standard2.add(977);
      standard2.add(983);
      standard2.add(991);
      standard2.add(997);
      
      for(int i =900; i <=1000; i++){
        test2.add(i);
      }
      
      test = test.Sieve(2,50);
      test2 = test2.Sieve(900,1000);

      for(int i = 0; i < test.size(); i++){
        assertEquals("The list should be:", standard.get(i), test.get(i));
      }
      
      for(int i = 0; i < test2.size(); i++){
        assertEquals("The list should be:", standard.get(i), test.get(i));
      }
      
      
    }
  }