import junit.framework.TestCase;
import java.io.IOException;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 *
 * Class which thoroughly tests some methods of WebPageIndex
 * Authors: Jack Flaherty and Alyssa Biggins
 */
public class WebPageIndexTest extends TestCase {

    /**
     * A test method.
     * (Replace "X" with a name describing the test.  You may write as
     * many "testSomething" methods in this class as you wish, and each
     * one will be called when running JUnit over this class.)
     */
    public void testWebPageIndexAndGetUrl() throws IOException {
        String url = "http://www.google.com/";
        WebPageIndex test = new WebPageIndex(url);

        assertEquals("Constructor should have set URL correctly: ", "http://www.google.com/", test.getUrl());
    }

    public void testGetWordCount() throws IOException {
        String url = "http://www.google.com/";
        WebPageIndex test1 = new WebPageIndex(url);

        assertEquals("Google webpage has 43 words on it (from TestScanner test): ", 43, test1.getWordCount());
    }

    public void testContains() throws IOException {
        String url = "http://www.google.com/";
        String url2 = "http://www.bc.edu/";
        String s = "google";
        String s1 = "search";
        String s2 = "yahoo";

        String s3 = "libraries";
        String s4 = "boo";

        WebPageIndex tes = new WebPageIndex(url2);
        WebPageIndex tes2 = new WebPageIndex(url);

        assertTrue("Seeing if 'google' is on google.com: ", tes2.contains(s));
        assertTrue("Seeing if 'search' is on google.com: ", tes2.contains(s1));
        assertFalse("Seeing if 'yahoo' is not on google.com: ", tes2.contains(s2));

        assertTrue("Seeing if 'libraries' is on bc.edu: ", tes.contains(s3));
        assertFalse("Seeing if 'boo' is not on bc.edu: ", tes.contains(s4));
    }

    public void testGetCount() {

    }

    public void testGetFrequency() {

    }

    public void testGetLocations() {

    }

    public void testWords() {

    }

    public void testToString() {

    }


}
