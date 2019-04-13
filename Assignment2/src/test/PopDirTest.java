package test;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import a2.Directory;
import a2.PopDir;
import driver.JShell;
import mock.MockFS;

public class PopDirTest {
  PopDir popd = new PopDir();
  MockFS fileSystem = new MockFS();
  boolean fileSystemEnabled = false;

  public void setUp() {
    if (!fileSystemEnabled) {
      fileSystemEnabled = true;
      JShell.root = new Directory("");
      fileSystem.createMockFS(); //create a filesystem
    }
    JShell.curr = JShell.root;
    JShell.directoryStack = new Stack<String>();
    fileSystem.addSavedDirs();
  }

  @Test
  public void testPopdSingleDir() {
    setUp();

    Directory expectedDir = JShell.root.getDir("dir2").getDir("dir5");
    String expectedStack = "[/dir1/dir4/, /, /dir2/, /dir3/dir6/dir7/]";
    popd.execute("");
    boolean result = expectedDir == JShell.curr &&
        expectedStack.equals(JShell.directoryStack.toString());

    //popd should pop the last directory
    assertEquals(true, result);    
  }

  @Test
  public void testPopdMultipleDirs() {
    setUp();

    Directory expectedDir1 = JShell.root.getDir("dir2").getDir("dir5");
    String expectedStack1 = "[/dir1/dir4/, /, /dir2/, /dir3/dir6/dir7/]";
    popd.execute("");
    boolean result1 = expectedDir1 == JShell.curr &&
        expectedStack1.equals(JShell.directoryStack.toString());

    Directory expected2 = JShell.root.getDir("dir3").getDir("dir6")
        .getDir("dir7");
    String expectedStack2 = "[/dir1/dir4/, /, /dir2/]";
    popd.execute("");
    boolean result2 = expected2 == JShell.curr &&
        expectedStack2.equals(JShell.directoryStack.toString());

    Directory expected3 = JShell.root.getDir("dir2");
    String expectedStack3 = "[/dir1/dir4/, /]";
    popd.execute("");
    boolean result3 = expected3 == JShell.curr &&
        expectedStack3.equals(JShell.directoryStack.toString());

    //popd should match all expected dirs in the stack
    assertEquals(true, result1 && result2 && result3);    
  }

  @Test
  public void testPopdTooManyDirs() {
    setUp();

    Directory expected = JShell.root.getDir("dir1").getDir("dir4");
    for (int i = 0; i < 8; i++) {
      popd.execute("");
    }
    /* popd should return an error on the last 3 pops,
     * and should be in the last saved dir */
    assertEquals(expected, JShell.curr);    
  }

  @Test
  public void testPopdInvalidParams() {
    setUp();

    Directory expectedDir = JShell.curr;
    String expectedStack = "[/dir1/dir4/, /, /dir2/, /dir3/dir6/dir7/, " +
        "/dir2/dir5/]";
    popd.execute("invalid");
    
    boolean result = expectedDir == JShell.curr &&
        expectedStack.equals(JShell.directoryStack.toString());
    //popd should raise an error and stay at the current dir
    assertEquals(true, result);    
  }
}
