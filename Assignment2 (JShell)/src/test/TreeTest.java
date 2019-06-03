package test;

import org.junit.*;
import static org.junit.Assert.*;
import a2.Directory;
import a2.Tree;
import driver.JShell;
import mock.MockFS;

public class TreeTest {
  Tree tree = new Tree();
  MockFS fileSystem = new MockFS();
  boolean fileSystemEnabled = false;

  public void setUp() {
    //configured so the filesystem is only created once
    if (!fileSystemEnabled) {
      fileSystemEnabled = true;
      JShell.root = new Directory("");
      JShell.curr = JShell.root;
      fileSystem.createMockFS();  //create a filesystem
    }
  }

  @Test 
  public void testTreeEmptyRoot() {
    JShell.root = new Directory("");
    JShell.curr = JShell.root;
    // only the root should be printed
    String expected = "/\n";
    // result of running tree
    String result = tree.execute("");
    assertEquals(expected, result);
  }

  @Test 
  public void testTreeNested() {
    setUp();
    // only the root should be printed
    String expected = "/\n\t/dir3\n\t\t/dir6\n\t\t\t/dir7\n\t\t\t\t/dir8\n"
        + "\t\tfile3\n\t/dir2\n\t\t/dir5\n\t\tfile2\n\t/dir1\n"  
        + "\t\t/dir4\n\t\tfile1\n";
    // result of running tree
    System.out.println(tree.execute(""));
    String result = tree.execute("");
    assertEquals(expected, result);
  }
}
