import junit.framework.TestCase;
import java.io.IOException;
import java.util.*;
/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class URLScorerTest extends TestCase {
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
public void testCompare() throws IOException {
      WebPageIndex test = new WebPageIndex("http://google.com");
      WebPageIndex standard = new WebPageIndex("http://bc.edu");
      
      List<String> queries = new ArrayList<String>();
      queries.add("google");
      URLScorer score = new URLScorer(queries);
     
      assertEquals("This compares:", 1, score.compare(test, standard));
      

  }
  
}
