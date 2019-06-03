package mock;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class MockSaveFile {
  public void createFile(boolean[] options) {
    String dirs = "";
    String files = "";
    String dirstack = "";
    String history = "";

    //first element of options: store directories
    if (options[0]) {
      dirs = "/dir1 /dir1/dir2 /dir1/dir2/dir3 /dir1/dir2/dir3/dir4 ";

      //second element of options: store files
      if (options[1]) {
        files = "/dir1/file1 \"\" /dir1/dir2/file1 \"test string\" ";
      }

      //third element of options: store saved directories
      if (options[2]) {
        dirstack = "/dir1/dir2/, /dir1/dir2/dir3/dir4/";
      }
    }

    //fourth element of options: store history
    if (options[3]) {
      history = "valid output\n" +
          "invalid output\n" + 
          "hello world\n" +
          "mkdir dir1 dir1/dir2 dir1/dir2/dir3\n" +
          "save testfile2.txt";
    }

    String data = dirs + "\n\"\"\"\n" + files + "\n\"\"\"\n" + dirstack
        + "\n\"\"\"\n" + history;
    writeToFile(data);

  }
  public void createInvalidFile() {
    String contents = "This is an invalid file\n\"\"\"\nMore invalid syntax";
    writeToFile(contents);
  }
  
  private void writeToFile(String data) {
    //create new output stream
    OutputStream saveFile;
    try {
      /* open a new file with the given path 'fileName' and write the shell 
       * data into it */
      saveFile = new FileOutputStream(new java.io.File("testfile2.txt"));
      saveFile.write(data.getBytes());
      saveFile.close();
    } 
    catch (Exception exception) {
      //the file name is invalid
    }
  }
}

