package test;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import a2.History;
import driver.JShell;
import mock.MockFS;

public class HistoryTest {
  History history = new History();
  MockFS fileSystem = new MockFS();
  boolean historyAdded = false;
  
  public void setUp() {
    if (!historyAdded) {
      historyAdded = true;
      JShell.history = new ArrayList<String>();
      fileSystem.addHistory(); //create a filesystem
    }
  }
  
  @Test
  public void testHistoryNoParams() {
    setUp();
    String expected = "1. hello\n2. world\n3. foo\n4. bar\n"
        + "5. invalid command\n6. valid command\n7. test contents\n"
        + "8. for the history cmd\n9. ok \n10. /done/";
    //should print all the contents of the history
    assertEquals(expected, history.execute(""));
  }
  
  @Test
  public void testHistoryGivenNumber() {
    setUp();
    String expected = "6. valid command\n7. test contents\n"
        + "8. for the history cmd\n9. ok \n10. /done/";
    //should print the last find contents of the history
    assertEquals(expected, history.execute("5"));
  }
  
  @Test
  public void testHistoryLargeNumber() {
    setUp();
    String expected = "1. hello\n2. world\n3. foo\n4. bar\n"
        + "5. invalid command\n6. valid command\n7. test contents\n"
        + "8. for the history cmd\n9. ok \n10. /done/";
    
    //should print all the contents of the history
    assertEquals(expected, history.execute("99"));
  }
  
  @Test
  public void testHistoryNegativeNumber() {
    setUp();
    //should print an error
    assertEquals(null, history.execute("-1"));
  }
  
  @Test
  public void testHistoryInvalidParam() {
    setUp();
    //should print an error
    assertEquals(null, history.execute("invalid"));
  }
  
  @Test
  public void testHistoryTooManyParams() {
    setUp();
    //should print an error
    assertEquals(null, history.execute("1 2 3"));
  }
  
  
}
