package test;

import org.junit.*;
import static org.junit.Assert.*;
import a2.Directory;
import a2.PrintDir;
import driver.JShell;
import mock.MockFS;

public class PrintDirTest {
  PrintDir pwd = new PrintDir();
  MockFS fileSystem = new MockFS();
  boolean fileSystemEnabled = false;
  
  public void setUp() {
    //configured so the filesystem is only created once
    if (!fileSystemEnabled) {
      fileSystemEnabled = true;
      JShell.root = new Directory("");
      fileSystem.createMockFS();  //create a filesystem
    }
    JShell.curr = JShell.root;
  }
  
  @Test
  public void testPwdRootDir() {
    setUp();
    assertEquals("/", pwd.execute(""));
  }
  
  @Test 
  public void testPwdNestedDir() {
    setUp();
    JShell.curr = JShell.root.getDir("dir3").getDir("dir6").getDir("dir7");
    assertEquals("/dir3/dir6/dir7/", pwd.execute(""));
  }
  
  @Test
  public void testPwdInvalidParams() {
    setUp();
    assertEquals(null, pwd.execute("invalidparam"));
  }
}
