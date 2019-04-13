package test;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import a2.Directory;
import a2.PushDir;
import driver.JShell;
import mock.MockFS;

public class PushDirTest {
  PushDir pushd = new PushDir();
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
  }
  
  @Test
  public void testPushdSingleRelativeDir() {
    setUp();

    String expectedStack = "[/]";
    Directory expectedDir = JShell.curr.getDir("dir1");
    pushd.execute("dir1");
    
    //pushd should push the root to the stack and cd into dir1
    boolean result = expectedStack.equals(JShell.directoryStack.toString()) &&
        expectedDir == JShell.curr;
    
    assertEquals(true, result);
  }
  
  @Test
  public void testPushdMultipleRelativeDirs() {
    setUp();
    String expectedStack = "[/, /dir2/]";
    Directory expectedDir = JShell.curr.getDir("dir2").getDir("dir5");
    pushd.execute("dir2");
    pushd.execute("dir5");
    
    //pushd should push the root and dir2 to the stack and cd into dir2/dir5
    boolean result = expectedStack.equals(JShell.directoryStack.toString()) &&
        expectedDir == JShell.curr;
    
    assertEquals(true, result);
  }
  
  @Test 
  public void testPushdSingleAbsoluteDir() {
    setUp();
    //change the current directory
    JShell.curr = JShell.curr.getDir("dir1");
    
    String expectedStack = "[/dir1/]";
    Directory expectedDir = JShell.root.getDir("dir3");
    pushd.execute("/dir3");
    
    //pushd should push the root and dir2 to the stack and cd into dir2/dir5
    boolean result = expectedStack.equals(JShell.directoryStack.toString()) &&
        expectedDir == JShell.curr;
    
    assertEquals(true, result);
  }
  
  @Test 
  public void testPushdAbsolutePath() {
    setUp();
    //change the current directory
    JShell.curr = JShell.curr.getDir("dir1").getDir("dir4");
    
    String expectedStack = "[/dir1/dir4/]";
    Directory expectedDir = JShell.root.getDir("dir3").getDir("dir6");
    pushd.execute("/dir3/dir6");
    
    boolean result = expectedStack.equals(JShell.directoryStack.toString()) &&
        expectedDir == JShell.curr;
    
    assertEquals(true, result);
  }
  
  @Test 
  public void testPushdRoot() {
    setUp();
    
    String expectedStack1 = "[/]";
    Directory expectedDir1 = JShell.curr.getDir("dir1");
    //pushd should push in the root and cd into dir1
    pushd.execute("dir1");
    boolean result1 = expectedStack1.equals(JShell.directoryStack.toString())
        && expectedDir1 == JShell.curr;
    
    String expectedStack2 = "[/, /dir1/]";
    Directory expectedDir2 = JShell.root;
    //pushd should push in dir1 and cd into the root
    pushd.execute("/");
    boolean result2 = expectedStack2.equals(JShell.directoryStack.toString())
        && expectedDir2 == JShell.curr;
    
    assertEquals(true, result1 && result2);
  }
  
  @Test 
  public void testPushdSameDirectory() {
    setUp();

    String expectedStack1 = "[/]";
    Directory expectedDir1 = JShell.curr.getDir("dir2");
    //pushd should push in the root and cd into dir2    
    pushd.execute("dir2");
    boolean result1 = expectedStack1.equals(JShell.directoryStack.toString())
        && expectedDir1 == JShell.curr;
    
    String expectedStack2 = "[/, /dir2/]";
    Directory expectedDir2 = JShell.curr;
    //pushd should push in dir2 and cd into the same directory
    pushd.execute(".");
    boolean result2 = expectedStack2.equals(JShell.directoryStack.toString())
        && expectedDir2 == JShell.curr;
    
    assertEquals(true, result1 && result2);
  }
  
  @Test 
  public void testPushdParent() {
    setUp();

    String expectedStack1 = "[/]";
    Directory expectedDir1 = JShell.root.getDir("dir2").getDir("dir5");
    //pushd should push in the root and cd into dir2/dir5   
    pushd.execute("/dir2/dir5");
    boolean result1 = expectedStack1.equals(JShell.directoryStack.toString())
        && expectedDir1 == JShell.curr;
    
    String expectedStack2 = "[/, /dir2/dir5/]";
    Directory expectedDir2 = JShell.root.getDir("dir2");
    //pushd should push in dir2/dir5 and cd into the parent directory
    pushd.execute("..");
    boolean result2 = expectedStack2.equals(JShell.directoryStack.toString())
        && expectedDir2 == JShell.curr;
    
    assertEquals(true, result1 && result2);
  }
  
  @Test 
  public void testPushdPathWithParent() {
    setUp();
    //change the current directory
    JShell.curr = JShell.curr.getDir("dir3").getDir("dir6");

    String expectedStack = "[/dir3/dir6/]";
    Directory expectedDir = JShell.root.getDir("dir3");
    // pushd should push the current dir and cd into dir3
    pushd.execute("/dir2/../dir3");
    
    boolean result = expectedStack.equals(JShell.directoryStack.toString()) &&
        expectedDir == JShell.curr;
    
    assertEquals(true, result);
  }
  
  @Test 
  public void testPushdPathWithSingleDot() {
    setUp();
    //change the current directory
    JShell.curr = JShell.root.getDir("dir2").getDir("dir5");
    
    String expectedStack = "[/dir2/dir5/]";
    Directory expectedDir = JShell.root.getDir("dir3").getDir("dir6");
    //pushd should push the current dir and cd into dir3/dir6
    pushd.execute("/dir3/./dir6");
    
    boolean result = expectedStack.equals(JShell.directoryStack.toString()) &&
        expectedDir == JShell.curr;
    
    assertEquals(true, result);
  }
  
  @Test 
  public void testPushdInvalidPath() {
    setUp();

    String expectedStack = "[]";
    Directory expectedDir = JShell.curr;
    /* push with an invalid path, should raise an error and nothing should be
    /  pushed */
    pushd.execute("InvalidPath");
    
    boolean result = expectedStack.equals(JShell.directoryStack.toString()) &&
        expectedDir == JShell.curr;
    
    assertEquals(true, result);
  }
  
  @Test 
  public void testPushdInvalidParams() {
    setUp();

    String expectedStack = "[]";
    Directory expectedDir = JShell.curr;
    //should return an error
    pushd.execute("dir1 dir2 dir3");
    
    boolean result = expectedStack.equals(JShell.directoryStack.toString()) &&
        expectedDir == JShell.curr;
    
    assertEquals(true, result);
  }
}
