package test;

import org.junit.*;
import static org.junit.Assert.*;
import a2.Directory;
import a2.Cat;
import driver.JShell;
import mock.MockFS;

public class CatTest {
  Cat cat = new Cat();
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
  public void testCatOneFile() {
    setUp();
    String expected = "/dir1/file1:\ntest contents\n\n\n";
    assertEquals(expected, cat.execute("/dir1/file1"));
  }
  
  @Test
  public void testCatMultipleFiles() {
    setUp();
    String expected = "dir2/file2:\nhello world\n\n\n"
        + "dir3/file3:\nfoo bar\n\n\n";
    assertEquals(expected, cat.execute("dir2/file2 dir3/file3"));
  }
  
  @Test
  public void testCatInvalidFile() {
    setUp();
    //Cat should raise an error and return nothing
    assertEquals(null, cat.execute("invalidFile"));
  }
  
  @Test
  public void testCatOnDirectory() {
    setUp();
    //Cat should raise an error and return nothing
    assertEquals(null, cat.execute("dir1"));
  }
   
  @Test
  public void testCatValidAndInvalidFiles() {
    setUp();
    String expected = "dir2/file2:\nhello world\n\n\n"
        + "dir3/file3:\nfoo bar\n\n\n";
    //Cat should return the 2 valid files and raise an error for the invalid
    assertEquals(expected, cat.execute("dir2/file2 invalidFile2 dir3/file3"));
  }
  
  @Test
  public void testCatMultipleInvalidFiles() {
    setUp();
    //Cat should raise erros for all the invalid files
    assertEquals(null, cat.execute("invalidFileA invalidFileB invalidFileC"));
  }
  
  @Test
  public void testCatEmptyParams() {
    assertEquals(null, cat.execute(""));
  }
  
  
}
