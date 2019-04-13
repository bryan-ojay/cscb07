package test;

import org.junit.*;
import static org.junit.Assert.*;
import a2.Directory;
import a2.ListContents;
import driver.JShell;
import mock.MockFS;

public class ListContentsTest {
  ListContents ls = new ListContents();
  MockFS fileSystem = new MockFS();
  boolean fileSystemEnabled = false;
  
  @Before
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
  public void testLsNoParams() {
    setUp();

    /* there are 6 possibilities for the outcome of the result due to the 3
     * hashed contents in the contents of the current directory */
    String expected1 = "/\n\t/dir1\n\t/dir2\n\t/dir3\n";
    String expected2 = "/\n\t/dir1\n\t/dir3\n\t/dir2\n";
    String expected3 = "/\n\t/dir2\n\t/dir1\n\t/dir3\n";
    String expected4 = "/\n\t/dir2\n\t/dir3\n\t/dir1\n";
    String expected5 = "/\n\t/dir3\n\t/dir1\n\t/dir2\n";
    String expected6 = "/\n\t/dir3\n\t/dir2\n\t/dir1\n";
    
    String result = ls.execute("");
   
    //the result must be equal to one of the possibilities
    boolean cases = expected1.equals(result) || expected2.equals(result)
        || expected3.equals(result) || expected4.equals(result)
        || expected5.equals(result) || expected6.equals(result);
    

    assertEquals(true, cases);
  }
  
  @Test 
  public void testLsOnDir() {
    setUp();

    /* there are 2 possibilities for the outcome of the result due to the 2
     * hashed contents in the contents of the called directory */
    String expected1 = "/dir1\n\t/dir4\n\tfile1\n";
    String expected2 = "/dir1\n\tfile1\n\t/dir4\n";
    
    String result = ls.execute("dir1");
   
    //the result must be equal to one of the possibilities
    boolean cases = expected1.equals(result) || expected2.equals(result);

    assertEquals(true, cases);
  }
  
  @Test
  public void testLsOnFile() {
    setUp();
    
    String expected = "file2\n";
    //should return back the file name with a new line
    String result = ls.execute("dir2/file2");
    
    assertEquals(expected, result);
  }
  
  @Test
  public void testLsMultipleDirs() {
    setUp();
    
    /* there are 2 possiblities for each directory string, resulting in 4
     * cases total */
    String expected1a = "/dir1\n\t/dir4\n\tfile1\n";
    String expected1b = "/dir1\n\tfile1\n\t/dir4\n";
    
    String expected2a = "/dir2\n\t/dir5\n\tfile2\n";
    String expected2b = "/dir2\n\tfile2\n\t/dir5\n";
    
    String result = ls.execute("dir1 dir2");
    
    /* the resulting string should be equal to a concatenation of 2 of the
     * expected strings */
    boolean cases = result.equals(expected1a + expected2a)
        || result.equals(expected1a + expected2b)
        || result.equals(expected1b + expected2a)
        || result.equals(expected1b + expected2b);
    
    assertEquals(true, cases);
  }
  
  @Test
  public void testLsMultipleFiles() {
    setUp();
    
    String expected = "file1\nfile2\n";
    //should return back both file names on two lines, followed by a new line
    String result = ls.execute("dir1/file1 dir2/file2");
    
    assertEquals(expected, result);
  }
  
  @Test
  public void testLsDirAndFile() {
    setUp();
    
    String expected = "/dir7\n\t/dir8\nfile3\n";
    //should return back the contents of dir7 followed by the name of file3
    String result = ls.execute("dir3/dir6/dir7 dir3/file3");
    
    assertEquals(expected, result);
  }
  
  @Test
  public void testLsRecursiveDir() {
    setUp();
    
    //there are 2 possibilities due to there being 2 first children in dir3
    String expected1 = "/dir3\n\tfile3\n\t/dir6\n\t\t/dir7\n\t\t\t/dir8\n";
    String expected2 = "/dir3\n\t/dir6\n\t\t/dir7\n\t\t\t/dir8\n\tfile3\n";

    //should return all contents of dir3
    String result = ls.execute("dir3 -R");

    //the result should be equal to one of the expected strings
    boolean cases = expected1.equals(result) || expected2.equals(result);
    
    assertEquals(true, cases);
  }
  
  @Test
  public void testLsInvalidPath() {
    setUp();
    
    String expected = null;
    //should return back nothing
    String result = ls.execute("InvalidPath");
    
    assertEquals(expected, result);
  }
  
  @Test
  public void testLsInvalidPathAndValidDir() {
    setUp();
    
    String expected = "/dir7\n\t/dir8\n";
    //should only return back the contents of dir7
    String result = ls.execute("InvalidPath /dir3/dir6/dir7");
    
    assertEquals(expected, result);
  }
  
  @Test
  public void testLsInvalidPathAndValidFile() {
    setUp();
    
    String expected = "file3\n";
    //should only return back the name of file3
    String result = ls.execute("InvalidPath /dir3/file3");
    
    assertEquals(expected, result);
  }

  
  
}
