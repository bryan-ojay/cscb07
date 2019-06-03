package mock;
import java.io.File;
import java.util.*;

public class MockLoadFile {
  public String getFile(String name) {
    /* create variables to store the file contents and to check if the end of 
     * the file has been reached */
    String fileContents = null;
    try {
      Scanner fileScanner = new Scanner(new File(name));
      fileContents = fileScanner.useDelimiter("\\Z").next();
      fileScanner.close();
    }
    catch (Exception exception) {
      //the file was not found
    }
    
    return fileContents;
  }
}
