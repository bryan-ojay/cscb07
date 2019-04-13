package test;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class MockFileWriter {
  
  public void createMatrix(String fileName, String fileContents) {
    //create new output stream
    OutputStream saveFile;
    try {
      /* open a new file with the given path 'fileName' and write the
       * fileContents into it */
      saveFile = new FileOutputStream(new java.io.File(fileName));
      saveFile.write(fileContents.getBytes());
      saveFile.close();
    } 
    catch (Exception exception) {
      System.out.println("File was not created.");
    }
  }
}
