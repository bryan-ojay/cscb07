package test;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import a2.Directory;
import a2.EvaluateInput;
import driver.JShell;
import mock.*;

/**
 * These test cases assume all of the commands work as intended.
 * The test cases also use redirection for classes that output data.
 */
public class EvaluateInputTest {
  EvaluateInput eval = EvaluateInput.getInstance();
  MockFS fileSystem = new MockFS();

  public void setUp() {
    JShell.root = new Directory("");
    JShell.curr = JShell.root;
    JShell.directoryStack = new Stack<String>();
    JShell.history = new ArrayList<String>();
    fileSystem.createMockFS(); //create a filesystem
  }

  public void simpleSetUp() {
    JShell.root = new Directory("");
    JShell.curr = JShell.root;
    JShell.directoryStack = new Stack<String>();
    JShell.history = new ArrayList<String>();
    fileSystem.createSimpleMockFS();
  }

  //commands.put("exit","ExitShell");
  @Test
  public void TestEvalExit() {
    JShell.exitFlag = true;
    eval.command("exit");
    assertEquals(false, JShell.exitFlag);
  }

  //commands.put("mkdir","MakeDir");
  //commands.put("cd","ChangeDir");
  @Test
  public void testEvalMkdirAndCd() {
    simpleSetUp();
    //mkdir and cd into a directory
    eval.command("mkdir testDir");
    eval.command("cd testDir");
    Directory expected = JShell.root.getDir("testDir");

    assertEquals(expected, JShell.curr);
  }

  //commands.put("ls", "ListContents");
  @Test
  public void testEvalLS() {
    simpleSetUp();
    //write the shell's contents into the testfile
    eval.command("ls > testFile");
    String expected1 = "/\n\t/dir1\n\t/dir2\n";
    String expected2 = "/\n\t/dir2\n\t/dir1\n";
    String result = JShell.root.getFile("testFile").getContents();

    assertEquals(true, result.equals(expected1) || result.equals(expected2));
  }

  @Test
  public void testEvalLS2() {
    simpleSetUp();
    //append the shell's contents into the testfile
    eval.command("ls dir1 >> testFile");
    String expected = "\n/dir1\n\tfile1\n";
    String result = JShell.root.getFile("testFile").getContents();

    assertEquals(expected, result);
  }

  //commands.put("pwd", "PrintDir");
  @Test
  public void testEvalPwd() {
    setUp();
    /* - write the current dir path into a testfile 
     * - cd into dir1 in the current dir 
     * - append dir1's path into the testfile */
    eval.command("pwd > testfile");
    eval.command("cd dir1");
    eval.command("pwd >> /testfile");
    String expected = "/\n/dir1/";
    String result = JShell.root.getFile("testfile").getContents();

    assertEquals(expected, result);

  }

  //commands.put("pushd", "PushDir");
  @Test
  public void testEvalPushd() {
    setUp();

    Directory expectedDir = JShell.curr.getDir("dir1").getDir("dir4");
    String expectedStack = "[/]";
    eval.command("pushd /dir1/dir4/");
    boolean result = expectedDir == JShell.curr && 
        expectedStack.equals(JShell.directoryStack.toString());

    assertEquals(true, result);
  }

  //commands.put("popd", "PopDir");
  @Test
  public void testEvalPopd() {
    setUp();
    fileSystem.addSavedDirs();

    Directory expectedDir = JShell.curr.getDir("dir2").getDir("dir5");
    String expectedStack = "[/dir1/dir4/, /, /dir2/, /dir3/dir6/dir7/]"; 
    eval.command("popd");
    boolean result = expectedDir == JShell.curr &&
        expectedStack.equals(JShell.directoryStack.toString());

    assertEquals(true, result);
  }

  //commands.put("history", "History");
  @Test
  public void testEvalHistory() {
    simpleSetUp();
    fileSystem.addHistory();

    eval.command("history > testFile");
    String expected = "1. hello\n2. world\n3. foo\n4. bar\n"
        + "5. invalid command\n6. valid command\n7. test contents\n"
        + "8. for the history cmd\n9. ok \n10. /done/";
    String result = JShell.root.getFile("testFile").getContents();

    assertEquals(expected, result);
  }

  //commands.put("cat", "Cat");
  @Test
  public void testEvalCat() {
    simpleSetUp();
    eval.command("cat dir1/file1 dir2/file2 > testFile");
    String expected = "dir1/file1:\ntest contents\n\n\n" +
        "dir2/file2:\nhello world\n\n\n";
    String result = JShell.root.getFile("testFile").getContents();
    assertEquals(expected, result);
  }

  //commands.put("echo", "Echo");
  @Test
  public void testEvalEcho() {
    simpleSetUp();
    eval.command("echo \"testing\" > dir2/testFile");
    String expected = "testing";
    String result = JShell.root.getDir("dir2").getFile("testFile")
        .getContents();
    assertEquals(expected, result);
  }

  //commands.put("man", "Manual");
  @Test
  public void testEvalMan() {
    simpleSetUp();
    eval.command("man man >> manFile");
    String expected = "\nman CMD: displays the manual for CMD";
    String result = JShell.root.getFile("manFile").getContents();
    assertEquals(expected, result);
  }

  //commands.put("save", "SaveFile");
  @Test
  public void testEvalSave() {
    simpleSetUp();
    fileSystem.addHistory();
    eval.command("save testfile1.txt");
    MockLoadFile mockLoad = new MockLoadFile();

    String dirs1 = "/dir1 /dir2 ";
    String dirs2 = "/dir2 /dir1 ";
    String files1 = "/dir1/file1 \"test contents\" "
        + "/dir2/file2 \"hello world\" ";
    String files2 = "/dir2/file2 \"hello world\" "
        + "/dir1/file1 \"test contents\" ";
    String history = "hello\nworld\nfoo\nbar\ninvalid command\nvalid command\n"
        + "test contents\nfor the history cmd\nok \n/done/";


    String expected1 = dirs1 + "\n\"\"\"\n" + files1 + "\n\"\"\"\n\n\"\"\"\n" 
        + history;
    String expected2 = dirs2 + "\n\"\"\"\n" + files2 + "\n\"\"\"\n\n\"\"\"\n"
        + history;

    String result = mockLoad.getFile("testfile1.txt");

    assertEquals(true, expected1.equals(result) || expected2.equals(result));
  }

  //commands.put("load", "LoadFile");
  @Test
  public void testEvalLoad() {
    JShell.root = new Directory("");
    JShell.curr = JShell.root;

    boolean[] testFiles = {true, true, false, false};
    MockSaveFile mockSave = new MockSaveFile();
    mockSave.createFile(testFiles);
    eval.command("load testfile2.txt");

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

  //commands.put("cp", "Copy");
  @Test
  public void testEvalCopy() {
    simpleSetUp();
    eval.command("cp dir2 dir1");

    boolean result;
    boolean dirExists = false;
    boolean fileExists = false;
    try {
      dirExists = JShell.curr.getDir("dir1").getDir("dir2").getContents()
          .toString().equals("{file2=file2}");
      fileExists = JShell.curr.getDir("dir1").getDir("dir2").getFile("file2")
          .getContents().equals("hello world");
    }
    catch(Exception exception) {
      result = false;
    }
    result = dirExists && fileExists;

    assertEquals(true, result);
  }

  //commands.put("mv", "Move");
  @Test
  public void testEvalMove() {
    simpleSetUp();
    eval.command("mv dir2 dir1");

    boolean result;
    boolean dirExists = false;
    boolean fileExists = false;
    boolean dirDeleted = false;
    try {
      dirExists = JShell.curr.getDir("dir1").getDir("dir2").getContents()
          .toString().equals("{file2=file2}");
      fileExists = JShell.curr.getDir("dir1").getDir("dir2").getFile("file2")
          .getContents().equals("hello world");
      dirDeleted = JShell.curr.getContents().toString().equals("{dir1=/dir1}");
    }
    catch(Exception exception) {
      result = false;
    }
    result = dirExists && fileExists && dirDeleted;

    assertEquals(true, result);
  }
  
  //commands.put("get", "GetURL");
  @Test
  public void testEvalGetURL() {
      simpleSetUp();
      eval.command("get http://utsc.utoronto.ca/~nick/cscB36/189/index.html");
      boolean fileExists = JShell.curr.getContents().containsKey("index");
      assertEquals(true, fileExists);
  }
  
  //commands.put("tree", "Tree");
  @Test
  public void testEvalTree() {
    simpleSetUp();
    eval.command("tree > testFile");
    String expected1 = "/\n\t/dir1\n\t\tfile1\n\t/dir2\n\t\tfile2\n";
    String expected2 = "/\n\t/dir2\n\t\tfile2\n\t/dir1\n\t\tfile1\n";
    String result = JShell.curr.getFile("testFile").getContents();
    assertEquals(true, expected1.equals(result) || expected2.equals(result));
  }
}
