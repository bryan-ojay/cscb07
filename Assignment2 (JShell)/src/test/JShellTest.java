package test;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import a2.Directory;
import a2.File;
import driver.JShell;
import mock.MockFS;

public class JShellTest {
  MockFS fileSystem = new MockFS();
  
  public void setUp() {
    JShell.root = new Directory("");
    JShell.curr = JShell.root;
    JShell.history = new ArrayList<String>();
    JShell.directoryStack = new Stack<String>();
    JShell.exitFlag = true;
  }
  
  @Test
  public void testExit(){
    setUp();
    //exitFlag should start off as true
    boolean expected = true;
    boolean result1 = JShell.exitFlag;
    //exit command
    JShell.command("exit");
    boolean result2 = JShell.exitFlag; //flag should change to false
    assertEquals(true, expected == result1 && expected != result2);
  }
  
  @Test
  public void testHistory() {
    setUp();
    //test that arbitrary commands are added to the history
    JShell.command("test1");
    JShell.command("test2");
    JShell.command("test3");
    assertEquals("[test1, test2, test3]", JShell.history.toString());
  }

  @Test 
  public void testMkdirMultipleDirectories() {
    setUp();
    //mkdir command, create directories q, t in q and b in t
    JShell.command("mkdir q q/t q/t/b");
    /*
     * the directory b should exist in t, which exists in q, which exists in
     * root
     */
    Directory result = JShell.curr.getDir("q").getDir("t").getDir("b");
    assertEquals("/q/t/b/", result.getPath());
  }

  @Test 
  public void testMkdirInvalid() {
    setUp();
    //invalid mkdir command, nothing should be created
    JShell.command("mkdir");
    Directory result = JShell.curr.getDir("");
    assertEquals(null, result);
  }

  @Test 
  public void testMkdirInvalidChar() {
    setUp();
    /*
     * mkdir commands with invalid directory names, the root should be empty
     * after all the commands
     */
    JShell.command("mkdir ! @ # $ % ^ & * ( )");
    String result = JShell.root.getContents().toString();
    assertEquals("{}", result);
  }

  @Test 
  public void testCdRoot() {
    setUp();
    //mkdir command, create directory a
    JShell.command("mkdir a");
    //cd command, go into directory a
    JShell.command("cd a");
    //cd command, go into root directory
    JShell.command("cd /");
    //the current directory should be the root directory
    Directory result = JShell.curr;
    assertEquals("/", result.getPath());
  }

  @Test 
  public void testCdDot() {
    setUp();
    //mkdir command, create directory w
    JShell.command("mkdir w");
    //cd command, go into directory w
    JShell.command("cd w");
    //cd command, go into the same directory
    JShell.command("cd .");
    //the current directory should be directory w
    Directory result = JShell.curr;
    assertEquals("/w/", result.getPath());
  }

  @Test 
  public void testCdTwoDotsSingleDirs() {
    setUp();
    //mkdir command, create directory t
    JShell.command("mkdir t");
    //mkdir command, go into directory t
    JShell.command("cd t");
    //mkdir command go into parent directory
    JShell.command("cd ..");
    //the current directory should be the root directory
    Directory result = JShell.root.getDir("t").getParent();
    assertEquals("/", result.getPath());
  }

  @Test 
  public void testCdTwoDotsMultiDirs() {
    setUp();
    //create and cd into directory path x/y/z
    JShell.command("mkdir x");
    JShell.command("cd x");
    JShell.command("mkdir y");
    JShell.command("cd y");
    JShell.command("mkdir z");
    JShell.command("cd z");
    JShell.command("cd ..");
    Directory result = JShell.curr;
    assertEquals("/x/y/", result.getPath());
  }

  @Test 
  public void testCdTwoDotsSameDirHasMultiDirs() {
    setUp();
    //create directory x
    JShell.command("mkdir x");
    //create and cd into directory path y/z
    JShell.command("mkdir y");
    JShell.command("cd y");
    JShell.command("mkdir z");
    JShell.command("cd z");
    //cd into the parent directory of y/z
    JShell.command("cd ..");
    Directory result = JShell.curr;
    assertEquals("/y/", result.getPath());
  }

  @Test 
  public void testCdPath() {
    setUp();
    //create and cd into directory path x/y/z
    JShell.command("mkdir x");
    JShell.command("cd x");
    JShell.command("mkdir y");
    JShell.command("cd y");
    JShell.command("mkdir z");
    JShell.command("cd z");
    //cd into the root
    JShell.command("cd /");
    //cd back into directory x/y/z
    JShell.command("cd /x/y/z");
    Directory result = JShell.curr;
    assertEquals("/x/y/z/", result.getPath());
  }

  @Test 
  public void testLsRoot() {
    setUp();
    //create directories r,x  and y
    JShell.command("mkdir r x y");
    //create and cd into diretory path r/s
    JShell.command("cd r");
    JShell.command("mkdir s");
    JShell.command("cd s");
    //cd into the root directory
    JShell.command("cd /");
    //get the contents of the root directory (should be r, x and y)
    JShell.command("ls");
    String result = JShell.root.getContents().toString();
    assertEquals("{x=/x, r=/r, y=/y}", result);
  }

  @Test 
  public void testLsFile() {
    setUp();
    //create and cd into directory a/b
    JShell.command("mkdir a");
    JShell.command("cd a");
    JShell.command("mkdir b");
    JShell.command("cd b");
    //create file c in a/b
    JShell.command("echo \"cat\" > c");
    //cd into the root directory
    JShell.command("cd /");
    //show the contents of path a/b/c (should just be the file name 'c')
    JShell.command("ls a/b/c");
    String result = JShell.curr.getDir("a").getDir("b").getFile("c").toString();
    assertEquals("c", result);
  }

  @Test 
  public void testLsCurrentDir() {
    setUp();
    //create directories r, x and y
    JShell.command("mkdir r x y");
    //create and cd into directory path r/s
    JShell.command("cd r");
    JShell.command("mkdir s");
    JShell.command("cd s");
    //create directory t in r/s
    JShell.command("mkdir t");
    //show contents of r/s (should be directory t)
    JShell.command("ls");
    String result = JShell.curr.getContents().toString();
    assertEquals("{t=/t}", result);
  }

  @Test 
  public void testLsDot() {
    setUp();
    //create directories r, x and y
    JShell.command("mkdir r x y");
    //create and cd into directory path r/s
    JShell.command("cd r");
    JShell.command("mkdir s");
    JShell.command("cd s");
    //create directory t in r/s
    JShell.command("mkdir t");
    //show contents of the same directory (r/s, should be directory t)
    JShell.command("ls .");
    String result = JShell.curr.getContents().toString();
    assertEquals("{t=/t}", result);
  }

  @Test 
  public void testLsDotDot() {
    setUp();
    //create directories r and x
    JShell.command("mkdir r x");
    //create and cd into directory path r/s
    JShell.command("cd r");
    JShell.command("mkdir s");
    JShell.command("cd s");
    //create directory t
    JShell.command("mkdir t");
    //show contents of the parent directory (directory r, should be s)
    JShell.command("ls ..");
    String result = JShell.curr.getParent().getContents().toString();
    assertEquals("{s=/s}", result);
  }

  @Test 
  public void testLsPath() {
    setUp();
    //create directories r and x
    JShell.command("mkdir r");
    JShell.command("mkdir x");
    //create and cd into directory path r/s
    JShell.command("cd r");
    JShell.command("mkdir s");
    JShell.command("cd s");
    //create directory t
    JShell.command("mkdir t");
    //go into the parent directory (directory)
    JShell.command("cd ..");
    JShell.command("ls s");
    String result = JShell.curr.getDir("s").getContents().toString();
    assertEquals("{t=/t}", result);
  }

  @Test 
  public void testPwdMultipleDirs() {
    setUp();
    //create and cd into directory path r/s/t
    JShell.command("mkdir r");
    JShell.command("cd r");
    JShell.command("mkdir s");
    JShell.command("cd s");
    JShell.command("mkdir t");
    JShell.command("cd t");
    //print the directory path
    JShell.command("pwd");
    Directory result = JShell.curr;
    assertEquals("/r/s/t/", result.getPath());
  }

  @Test 
  public void testPwdRoot() {
    setUp();
    //print the directory path of the root
    JShell.command("pwd");
    Directory result = JShell.curr;
    assertEquals("/", result.getPath());
  }

  @Test
  public void testHistoryNone() {
    setUp();
    //print the history of all recent commands
    JShell.command("history");
    String result = JShell.history.toString();
    assertEquals("[history]", result);
  }

  @Test
  public void testHistoryWithMultipleCommands() {
    setUp();
    //cd into root
    JShell.command("cd /");
    //get the history
    JShell.command("history");
    String result = JShell.history.toString();
    assertEquals("[cd /, history]", result);
  }

  @Test
  public void testHistoryWithNthCommand() {
    setUp();
    //cd into root
    JShell.command("cd /");
    //create and cd into directory a
    JShell.command("mkdir a");
    JShell.command("cd a");
    //get the last 2 most recent commands
    JShell.command("history 2");
    String result = JShell.history.subList(2, JShell.history.size()).toString();
    assertEquals("[cd a, history 2]", result);
  }

  @Test
  public void testPushDSingleDir() {
    setUp();
    //create directory a, call pushd command with directory a
    JShell.command("mkdir a");
    JShell.command("pushd a");
    String res = JShell.curr.getPath();
    String stackRes = JShell.directoryStack.toString();
    assertEquals("/a/", res);
    assertEquals("[/]", stackRes);
  }

  @Test
  public void testPushDMultiDir() {
    setUp();
    //create directory w, call pushd with directory w
    JShell.command("mkdir w");
    JShell.command("pushd w");
    //create directory x, call pushd with directory x
    JShell.command("mkdir x");
    JShell.command("pushd x");
    String res = JShell.curr.getPath();
    String stackRes = JShell.directoryStack.toString();
    assertEquals("/w/x/", res);
    assertEquals("[/, /w/]", stackRes);
  }

  @Test
  public void testPopDMultiDir() {
    setUp();
    Stack<String> r = new Stack<String>();
    // mkdir a
    JShell.command("mkdir a");
    // push a
    r.push(JShell.curr.getPath());
    JShell.command("pushd a");
    // mkdir t in a
    JShell.command("mkdir t");
    // push t
    r.push(JShell.curr.getPath());
    JShell.command("pushd t");
    // popd so now you are in a
    JShell.command("popd");
    r.pop();
    Stack<String> res = JShell.directoryStack;
    assertEquals(r, res);
  }

  @Test
  public void testEcho1Root() {
    // setup shell
    setUp();
    //create a new file in root
    JShell.command("echo \"abc\" > a");
    boolean res = JShell.root.getContents().containsKey("a");
    String resText = JShell.root.getFile("a").getContents(); 
    assertEquals(true, res);
    assertEquals("abc", resText);
  }

  @Test
  public void testEcho1MultiDir() {
    // setup shell
    setUp();
    // create a new dir
    JShell.command("mkdir a");
    // cd into a
    JShell.command("cd a");
    //create a new file in a
    JShell.command("echo \"abc\" > a");
    boolean res = JShell.curr.getContents().containsKey("a");
    assertEquals(true, res);
  }

  @Test
  public void testEcho2Root() {
    // setup shell
    setUp();
    //create a new file in root
    JShell.command("echo \"abc\" > a");
    //edit the file in root
    JShell.command("echo \"def\" >> a");
    String testFile = ((File) 
        JShell.curr.getContents().get("a")).getContents();
    assertEquals("abc\ndef", testFile);
  }

  @Test
  public void testEcho2MultiDir() {
    // setup shell
    setUp();
    // create a new dir
    JShell.command("mkdir a");
    // cd into a
    JShell.command("cd a");
    //create a new file in a
    JShell.command("echo \"abc\" > a");
    // edit file a.txt
    JShell.command("echo \"def\" >> a");
    String testFile = ((File) 
        JShell.curr.getContents().get("a")).getContents();
    assertEquals("abc\ndef", testFile);
  }

}