package test;

import org.junit.*;
import static org.junit.Assert.*;
import a2.Directory;
import a2.Copy;
import a2.Cat;
import a2.Tree;
import driver.JShell;
import mock.MockFS;

public class CopyTest {
  Copy copy = new Copy();
  Tree tree = new Tree();
  Cat cat = new Cat();
  MockFS fileSystem = new MockFS();
  boolean fileSystemEnabled = false;

  public void setUp() {
    //configured so the filesystem is only created once
    if (!fileSystemEnabled) {
      fileSystemEnabled = true;
      JShell.root = new Directory("");
      JShell.curr = JShell.root;
      fileSystem.createSimpleMockFS();  //create a filesystem
    }
  }

  @Test
  public void testCopyWithSameDir(){
    setUp();
    // the file system should stay the same
    String expected = "/\n\t/dir2\n\t\tfile2\n\t/dir1\n\t\tfile1\n";
    // result of running tree
    copy.execute("/dir1 dir1");
    String result = tree.execute("");
    assertEquals(expected, result);
  }

  @Test
  public void testCopyWithRoot(){
    setUp();
    // the file system should stay the same
    String expected = "/\n\t/dir2\n\t\tfile2\n\t/dir1\n\t\tfile1\n";
    // result of running tree
    copy.execute("/ dir1");
    String result = tree.execute("");
    assertEquals(expected, result);
  }

  @Test
  public void testCopyWithDir1() {
    setUp();
    // the file system should stay the same
    String expected = "/\n\t/dir2\n\t\tfile2"
        + "\n\t/dir1\n\t\tfile1\n\t\t/dir2\n\t\t\tfile2\n";
    // result of running tree
    copy.execute("/dir2 dir1");
    String result = tree.execute("");
    assertEquals(expected, result);
  }

  @Test
  public void testCopyWithFileToDir(){
    setUp();
    // the file system should stay the same
    String expected = "/\n\t/dir2\n\t\tfile2\n\t\tfile1\n" + 
        "\t/dir1\n\t\tfile1\n";
    // result of running tree
    copy.execute("dir1/file1 dir2");
    String result = tree.execute("");
    assertEquals(expected, result);
  }

  @Test
  public void testCopyWithFileToFile(){
    setUp();
    // the file system should stay the same
    String expected = "dir2/file2:\ntest contents\n\n\n";
    // result of running tree
    copy.execute("dir1/file1 dir2/file2");
    String result = cat.execute("dir2/file2");
    assertEquals(expected, result);
  }
}
