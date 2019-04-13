package test;

import org.junit.*;
import static org.junit.Assert.*;
import a2.Directory;
import a2.MakeDir;
import driver.JShell;
import mock.MockFS;

public class MakeDirTest {
  MakeDir mkdir = new MakeDir();
  MockFS fileSystem = new MockFS();
  
  public void setUp() {
    JShell.root = new Directory("");
    JShell.curr = JShell.root;
  }
  
  @Test 
  public void testMkdirSingleDirectory() {
    setUp();
    //mkdir command, create directory gg
    mkdir.execute("gg");
    //the directory gg should exist in root
    Directory result = JShell.root.getDir("gg"); 
    assertEquals("/gg/", result.getPath());
  }
  
  @Test 
  public void testMkdirMultipleDirectories() {
    setUp();
    //mkdir command, create directories q, t in q and b in t
    mkdir.execute("q q/t q/t/b");
    /*
     * the directory b should exist in t, which exists in q, which exists in
     * root
     */
    Directory result = JShell.root.getDir("q").getDir("t").getDir("b");
    assertEquals("/q/t/b/", result.getPath());
  }
  
  @Test 
  public void testMkdirInvalid() {
    setUp();
    //invalid mkdir command, nothing should be created
    mkdir.execute("");
    String result = JShell.root.getContents().toString();
    assertEquals("{}", result);
  }
  
  @Test 
  public void testMkdirInvalidDirNames() {
    setUp();
    /* mkdir commands with invalid directory names, the root should be empty
     * after all the commands */
    mkdir.execute("! @ # $ % ^ & * ( )");
    String result = JShell.root.getContents().toString();
    assertEquals("{}", result);
  }
  
  @Test
  public void testMkdirExistingDir() {
    setUp();
    /* create a filesystem and try to create a dir with the same path as an
     * existing dir in the filesystem */
    fileSystem.createMockFS();
    mkdir.execute("dir1");
    /* check that the directory was not overwritten and the contents are still
     * inside */
    String expected1 = "{dir4=/dir4, file1=file1}";
    String expected2 = "{file1=file1, dir4=/dir4}";
    String result = JShell.root.getDir("dir1").getContents().toString();
    assertEquals(true, expected1.equals(result) || expected2.equals(result));    
  }
  
  @Test
  public void testMkdirExistingFile() {
    setUp();
    /* create a filesystem and try to create a dir with the same path as a file
     * in the filesystem */
    fileSystem.createMockFS();
    mkdir.execute("dir1/file1");
    /* check that the file was not overwritten and the contents are still
     * inside */
    String expected = "test contents";
    String result = JShell.root.getDir("dir1").getFile("file1").getContents();
    assertEquals(expected, result);
  }
}
