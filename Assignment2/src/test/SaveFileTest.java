package test;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import a2.Directory;
import a2.SaveFile;
import driver.JShell;
import mock.MockLoadFile;

public class SaveFileTest {
  public SaveFile save = new SaveFile();
  public MockLoadFile mockLoad = new MockLoadFile();

  public void setUp() {
    JShell.history = new ArrayList<String>();
    JShell.directoryStack = new Stack<String>();
    JShell.root = new Directory("");
    JShell.curr = JShell.root;
  }

  @Test
  public void TestSaveDirs() {
    setUp();
    //Tests that SaveFile saves directories and history
    //1. make directories
    JShell.command("mkdir a a/b a/b/c a/b/c/d");
    //2. save the contents into a file
    JShell.command("save testfile.txt");

    //Test that the file exists on the file system.
    String expected = "/a /a/b /a/b/c /a/b/c/d " + //dirs
        "\n\"\"\"\n" + //line break
        "" +
        "\n\"\"\"\n" + //line break
        "" + //saved dirs
        "\n\"\"\"\n" + //line break
        "mkdir a a/b a/b/c a/b/c/d\n" + //history
        "save testfile.txt";

    assertEquals(expected, mockLoad.getFile("testfile.txt"));
  }

  @Test
  public void TestSaveFiles() {
    setUp();
    //Tests that SaveFile saves directories, files and history
    //1. make directories
    JShell.command("mkdir a a/b a/b/c a/b/c/d");
    //2. make files
    JShell.command("echo \"test1\" > file1");
    JShell.command("echo \"test2\" > a/file2");
    //3. save the contents into a file (overwrites the previous testfile)
    JShell.command("save testfile.txt");

    //Test that the file exists on the file system.
    String dirs = "/a /a/b /a/b/c /a/b/c/d ";
    String history = "mkdir a a/b a/b/c a/b/c/d\n" +
        "echo \"test1\" > file1\n" +
        "echo \"test2\" > a/file2\n" +
        "save testfile.txt";

    /* there are 2 possibilities for the file contents, because of hashing from
     * the directory's hashtable */
    String[] fileLines = {"/file1 \"test1\" /a/file2 \"test2\" ", 
    "/a/file2 \"test2\" /file1 \"test1\" "};

    /* Since there are 2 possiblities for the file contents, there are also 2
     * expected strings */
    String expected1 = dirs + "\n\"\"\"\n" + //dirs and line break
        fileLines[0] + "\n\"\"\"\n" + //files and line break
        "" + "\n\"\"\"\n" + //saved dirs and line break
        history; //history

    String expected2 = dirs + "\n\"\"\"\n" + //dirs and line break
        fileLines[1] + "\n\"\"\"\n" + //files and line break
        "" + "\n\"\"\"\n" + //saved dirs and line break
        history; //history

    //Get the file contents and compare it with the 2 expected strings
    String fileContents = mockLoad.getFile("testfile.txt");
    boolean isEqual = (expected1.equals(fileContents) ||
        expected2.equals(fileContents));
    assertEquals(true, isEqual);
  }

  @Test
  public void TestSaveDirectoryStack() {
    setUp();
    //1. make directories
    JShell.command("mkdir a a/b a/b/c a/b/c/d");
    //2. push files to directory stack
    JShell.command("cd a");
    JShell.command("pushd b");
    JShell.command("pushd c");
    JShell.command("pushd d");
    //3. save the contents into a file (overwrites the previous testfile)
    JShell.command("save testfile.txt");

    //Test that the file exists on the file system.
    String expected = 
        "/a /a/b /a/b/c /a/b/c/d " + "\n\"\"\"\n" + //dirs and line break
            "" + "\n\"\"\"\n" + //files and line break
            "/a/, /a/b/, /a/b/c/" + "\n\"\"\"\n" +  //saved dirs and line break
            "mkdir a a/b a/b/c a/b/c/d\n" + //history
            "cd a\n" +
            "pushd b\n" +
            "pushd c\n" +
            "pushd d\n" +
            "save testfile.txt";

    assertEquals(expected, mockLoad.getFile("testfile.txt"));
  }

  @Test
  public void TestSaveEmptyPath() {
  //1. make directories
    JShell.command("mkdir a a/b a/b/c a/b/c/d");
    //2. "save" the contents (empty parameters)
    JShell.command("save  ");
    assertEquals(null, mockLoad.getFile(""));
  }
  
  @Test
  public void TestSaveInvalidPath() {
    //1. make directories
    JShell.command("mkdir a a/b a/b/c a/b/c/d");
    //2. save the contents into an invalid file
    JShell.command("save /invalidpath/testfile.txt");
    assertEquals(null, mockLoad.getFile("/invalidpath/testfile.txt"));
  }
}
