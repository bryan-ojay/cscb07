package test;

import org.junit.*;
import static org.junit.Assert.*;
import a2.Directory;
import a2.ChangeDir;
import driver.JShell;
import mock.MockFS;

public class ChangeDirTest {
  ChangeDir cd = new ChangeDir();
  MockFS fileSystem = new MockFS();
  boolean fileSystemEnabled = false;
  
  public void setUp() {
    if (!fileSystemEnabled) {
      fileSystemEnabled = true;
      JShell.root = new Directory("");
      fileSystem.createMockFS(); //create a filesystem
    }
    JShell.curr = JShell.root;
  }
  
  @Test 
  public void testCdSingleRelativeDir() {
    setUp();

    Directory expected = JShell.curr.getDir("dir2");
    //cd into directory dir2
    cd.execute("dir2");
    
    assertEquals(expected, JShell.curr);
  }
  
  @Test 
  public void testCdMultipleRelativeDirs() {
    setUp();
    
    Directory expected = JShell.curr.getDir("dir2").getDir("dir5");
    //cd into directory dir2 in dir5
    cd.execute("dir2");
    cd.execute("dir5");
    
    assertEquals(expected, JShell.curr);
  }
  
  @Test 
  public void testCdSingleAbsoluteDir() {
    setUp();
    
    Directory expected = JShell.root.getDir("dir3");
    //cd into directory dir3
    cd.execute("/dir3");
    
    assertEquals(expected, JShell.curr);
  }
  
  @Test 
  public void testCdAbsolutePath() {
    setUp();
    
    Directory expected = JShell.root.getDir("dir3").getDir("dir6");
    //create a filesystem and cd into one of the dirs
    cd.execute("/dir3/dir6");
    
    assertEquals(expected, JShell.curr);
  }
  
  @Test 
  public void testCdRoot() {
    setUp();
    
    Directory expected1 = JShell.curr.getDir("dir1");
    //cd command, go into directory dir1
    cd.execute("dir1");
    Directory result1 = JShell.curr;
    
    Directory expected2 = JShell.root;
    //cd command, go into root directory
    cd.execute("/");
    Directory result2 = JShell.curr;
    assertEquals(true, expected1 == result1 && expected2 == result2);
  }
  
  @Test 
  public void testCdSameDirectory() {
    setUp();

    Directory expected = JShell.curr.getDir("dir2");
    //cd command, go into directory dir2    
    cd.execute("dir2");
    Directory result1 = JShell.curr;
    
    //cd command, go into the same directory
    cd.execute(".");
    Directory result2 = JShell.curr;
    assertEquals(true, expected == result1 && expected == result2);
  }
  
  @Test 
  public void testCdParent() {
    setUp();

    Directory expected1 = JShell.root.getDir("dir2").getDir("dir5");
    //cd command, go into directory dir2    
    cd.execute("/dir2/dir5");
    Directory result1 = JShell.curr;
    
    Directory expected2 = JShell.root.getDir("dir2");
    //cd command, go into the same directory
    cd.execute("..");
    Directory result2 = JShell.curr;
    assertEquals(true, expected1 == result1 && expected2 == result2);
  }
  
  @Test 
  public void testCdPathWithParent() {
    setUp();

    Directory expected = JShell.root.getDir("dir3");
    /* cd command, go into directory dir2, then dir2's parent, then dir3 in
     * dir2's parent */
    cd.execute("/dir2/../dir3");
    
    assertEquals(expected, JShell.curr);
  }
  
  @Test 
  public void testCdPathWithSingleDot() {
    setUp();

    Directory expected = JShell.root.getDir("dir2").getDir("dir5");
    /* cd command, go into directory dir2, then the same directory, 
     * then dir5 in dir2's parent */
    cd.execute("/dir2/./dir5");
    
    assertEquals(expected, JShell.curr);
  }
  
  @Test 
  public void testCdInvalidPath() {
    setUp();

    Directory expected = JShell.curr;
    //cd into directory dir2
    cd.execute("InvalidPath");
    
    assertEquals(expected, JShell.curr);
  }
  
  @Test 
  public void testCdInvalidParams() {
    setUp();

    Directory expected = JShell.curr;
    //should return an error
    cd.execute("dir1 dir2 dir3");
    
    assertEquals(expected, JShell.curr);
  }
  
}
