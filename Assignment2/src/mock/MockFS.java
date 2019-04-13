package mock;

import driver.JShell;

public class MockFS {
  
  public void createMockFS() {
    //create dirs in root
    JShell.root.putIn("dir1");
    JShell.root.putIn("dir2");
    JShell.root.putIn("dir3");
    //create subdirs in dirs
    JShell.root.getDir("dir1").putIn("dir4");
    JShell.root.getDir("dir2").putIn("dir5");
    JShell.root.getDir("dir3").putIn("dir6");
    //nested subdirs in dir3
    JShell.root.getDir("dir3").getDir("dir6").putIn("dir7");
    JShell.root.getDir("dir3").getDir("dir6").getDir("dir7").putIn("dir8");
    //create files in dirs
    JShell.root.getDir("dir1").createFile("file1", "test contents");
    JShell.root.getDir("dir2").createFile("file2", "hello world");
    JShell.root.getDir("dir3").createFile("file3", "foo bar");
  }
  
  public void createSimpleMockFS() {
    //create dirs in root
    JShell.root.putIn("dir1");
    JShell.root.putIn("dir2");
    //create files in dirs
    JShell.root.getDir("dir1").createFile("file1", "test contents");
    JShell.root.getDir("dir2").createFile("file2", "hello world");
    
  }
  
  public void addHistory() {
    // add 10 inputs to the history 
    JShell.history.add("hello");
    JShell.history.add("world");
    JShell.history.add("foo");
    JShell.history.add("bar");
    JShell.history.add("invalid command");
    JShell.history.add("valid command");
    JShell.history.add("test contents");
    JShell.history.add("for the history cmd");
    JShell.history.add("ok ");
    JShell.history.add("/done/");
  }
  
  public void addSavedDirs() {
    //add 5 directories to the stack
    JShell.directoryStack.add("/dir1/dir4/");
    JShell.directoryStack.add("/");
    JShell.directoryStack.add("/dir2/");
    JShell.directoryStack.add("/dir3/dir6/dir7/");
    JShell.directoryStack.add("/dir2/dir5/");
  }
}
