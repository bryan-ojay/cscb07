package test;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import a2.Directory;
import a2.LoadFile;
import driver.JShell;
import mock.MockSaveFile;

public class LoadFileTest {
  public LoadFile load = new LoadFile();
  public MockSaveFile mockSave = new MockSaveFile();

  public void setUp() {
    JShell.history = new ArrayList<String>();
    JShell.directoryStack = new Stack<String>();
    JShell.root = new Directory("");
    JShell.curr = JShell.root;
  }

  @Test
  public void TestLoadDirs() {
    setUp();
    //save and load a file that contains directories    
    boolean[] testDirs = {true, false, false, false}; 
    mockSave.createFile(testDirs);
    load.execute("testfile2.txt");
    String result;

    //check that the directories were correctly loaded
    try {
      result = JShell.curr.getDir("dir1").getDir("dir2")
          .getDir("dir3").getDir("dir4")
          .getContents().toString();
    }
    catch(Exception exception) {
      result = null;
    }

    assertEquals("{}", result);

  }

  @Test
  public void TestLoadFiles() {
    setUp();
    //save and load a file that contains directories and files
    boolean[] testFiles = {true, true, false, false};
    mockSave.createFile(testFiles);
    load.execute("testfile2.txt");

    //check that the files were correctly loaded
    boolean result;
    boolean file1 = false;
    boolean file2 = false;
    try {
      file1 = JShell.curr.getDir("dir1").getFile("file1")
          .getContents().equals("");
      file2 = JShell.curr.getDir("dir1").getDir("dir2")
          .getFile("file1").getContents().equals("test string");
    }
    catch(Exception exception) {
      result = false;
    }
    result = file1 && file2;

    assertEquals(true, result);
  }

  @Test
  public void TestLoadSavedDirs() {
    setUp();
    //save and load a file that contains saved directories
    boolean[] testDirStack = {true, false, true, false};
    mockSave.createFile(testDirStack);
    load.execute("testfile2.txt");
    //check that the saved directories were correctly loaded
    String expected = "[/dir1/dir2/, /dir1/dir2/dir3/dir4/]";
    assertEquals(expected, JShell.directoryStack.toString());
  }

  @Test 
  public void TestLoadHistory() {
    setUp();
    //save and load a file that contains a shell's history
    boolean[] testHistory = {false, false, false, true};
    mockSave.createFile(testHistory);
    load.execute("testfile2.txt");
    //check that the history was correctly loaded
    String expected = "[valid output, invalid output, hello world, " +
        "mkdir dir1 dir1/dir2 dir1/dir2/dir3, save testfile2.txt]";
    assertEquals(expected, JShell.history.toString());
  }

  @Test
  public void TestLoadNonExistentFile() {
    setUp();
    //load a nonexistent file
    load.execute("NonexistentFile.txt");
    /* Check that LoadFile did not load the file contents and all of the shell's
     * contents are empty */
    boolean noContents = JShell.root.getContents().toString().equals("{}");
    boolean noSavedDirs = JShell.directoryStack.toString().equals("[]");
    boolean noHistory = JShell.history.toString().equals("[]");
    boolean result = noContents && noSavedDirs && noHistory;
    assertEquals(true, result);
  }

  @Test
  public void TestLoadInvalidFile() {
    setUp();
    //create and load a file of invalid format
    mockSave.createInvalidFile();
    load.execute("testfile2.txt");
    /* Check that LoadFile did not load the file contents and all of the shell's
     * contents are empty */
    boolean noContents = JShell.root.getContents().toString().equals("{}");
    boolean noSavedDirs = JShell.directoryStack.toString().equals("[]");
    boolean noHistory = JShell.history.toString().equals("[]");
    boolean result = noContents && noSavedDirs && noHistory;
    assertEquals(true, result);
  }

  @Test
  public void TestLoadAfterCommand() {
    setUp();
    //save and load a file
    boolean[] test = {true, true, true, true};
    mockSave.createFile(test);
    JShell.command("mkdir a");
    JShell.command("load testfile2.txt");
    /* Check that LoadFile did not load the file contents and the shell's
     * contents stayed the same */
    boolean contents = JShell.root.getContents().toString().equals("{a=/a}");
    boolean savedDirs = JShell.directoryStack.toString().equals("[]");
    boolean history = JShell.history.toString().equals("[mkdir a, " + 
        "load testfile2.txt]");
    boolean result = contents && savedDirs && history;
    assertEquals(true, result);
  }

}
