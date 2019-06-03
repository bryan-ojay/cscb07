package a2;

import driver.*;

/**
 * Represents the abstract command. In general this class performs all the
 * commands and represents the default for all commands in the JShell. 
 */
public abstract class Command {

  /**
   * Default method for executing a command. In the subclasses an actual
   * command is executed.
   * @param input The parameters for the input.
   * @return returns a string saying you reached the default cmd execution
   */
  public String execute(String input){
    // print out saying you reached the default command execution
    return ("You've reached the default Command execution.");
  }

  /**
   * Returns the location of the directory represented by the given string.
   * Throws an error if the path is invalid.
   * @param path The string representation of the path.
   * @return The location of the directory.
   * @throws Exception if the path is invalid.
   */
  public Directory findPath(String path) throws Exception {
    //set directory marker
    Directory currentDir = JShell.curr;

    /* if the path is absolute, start at the root
     * the first index in the split-list will be empty, so we will use the
     * sublist of the array from index 1 to the end, for the for loop
     */

    // check if the file starts with a /
    if (path.startsWith("/")) {
      // set a marker to the root
      currentDir = JShell.root;
      // set the path to start at the second character to ignore the "/"
      path = path.substring(1);
    }
    // create empty split-path array (in case the 'path' string is empty) 
    String[] pathSplit = {};
    // check if the path is not empty
    if (!(path.equals(""))) {
      // split the path into an array separated at every "/"
      pathSplit = path.split("/");
    }
    // loop through the array
    for (int i = 0; i < pathSplit.length; i++) {
      // set a string variable equal to the content at the current index
      String dir = pathSplit[i];

      // check if the current index equals "."
      if (dir.equals(".")) {
        // if it does, continue on
        continue;
      }
      // check if the current index equals ".."
      else if (dir.equals("..")) {
        // if it does, set the current directory to the parent
        currentDir = currentDir.getParent();
      } else {
        // if the directory contains dir, go into dir
        currentDir = currentDir.getDir(dir);
        // check if the current directory is empty
        if (currentDir == null) {
          // if it is throw a message saying the path does not exist
          throw new Exception("This path does not exist.");
        }
      }
    }
    // return the current directory
    return currentDir;
  }

  /**
   * Finds and returns the file at the desired directory.
   * @param filePath The path of the file.
   * @param createFile Decide whether or not to create a new file if the
   * file does not exist
   * @return The file at the desired Directory
   * @throws Exception If the path is invalid
   */
  public File findFile(String filePath, boolean createFile) throws Exception{
    // create a directory
    Directory path;
    /* if the path ends with a slash, remove it, then get the last slash
     * in the filePath */
    filePath = filePath.replaceFirst("/$", "");
    int lastSlash = filePath.lastIndexOf("/") + 1;

    // check if filePath is a path and not just a file name
    if (lastSlash != 0) {
      /* set string variable dirPath to the filePath split from the
       * beginning to last slash
       */
      String dirPath = filePath.substring(0, lastSlash);
      // set the file path equal to everything after the lastSlash
      filePath = filePath.substring(lastSlash);
      // set path to call findPath to find the directory the file is in
      path = findPath(dirPath);
    }
    else {
      /* if the path is just a filename, it is relative to the current
       * directory
       */
      path = JShell.curr;
    }
    /* Check if there's something at the target path
     * - Create file is true; create file if path is null
     * - Create file is false; raise error if path is null
     * - If the target path is a directory, an error is thrown.
     */
    // get the target file by calling getFile
    File targetFile = path.getFile(filePath);
    // check if the <targetfile> is null and <createFile> is true
    if (targetFile == null && createFile) {
      // if it is, create the file at the path
      path.createFile(filePath, "");
      // get the file at the given path
      targetFile = path.getFile(filePath);
    }
    // check if the <targetfile> is null and <createFile> is false
    else if (targetFile == null && !createFile) {
      // throw an appropriate exception message
      throw new Exception("The file does not exist.");
    }
    // return the target file
    return targetFile;
  }

  /**
   * Prints the output of the command to the shell, or redirects it to a file.
   * @param contents The contents that are directed to the shell or a file.
   * @param redirection Indicates where the contents are directed.
   * @throws Exception If the file name is invalid.
   */
  public void direct(String contents, String redirection) throws Exception {
    // check if the redirection is an empty string
    if (redirection.equals("")) {
      // print the contents to the shell
      System.out.println(contents);
    }
    
    // If the redirection is not empty, get the redirection details
    else {
      //split the redirection parameter into the arrows and file name
      String arrows = redirection.replaceAll("[^>]", "");
      String fileName = redirection.replaceAll(">+ ?", "");
      
      if (fileName.matches("\\S*[.!@#$%^&*(){}~|<>?]+\\S*")) {
        System.out.println(contents + "\n\nThe name of the file is invalid.");
      }

      else {
        /* If the redirection starts with >, set the file's contents
         * to be the given contents.
         */
        if (arrows.equals(">")) {
          File file = findFile(fileName, true);
          file.setContents(contents);
        }
        /* Else if the redirection starts with >>, add the given
         * contents to the file's contents
         */
        else if (arrows.equals(">>")) {
          File file = findFile(fileName, true);
          file.addContents(contents);
        }
        // Else, notify the user of an error
        else {
          System.out.println(contents + "\n\nThe redirection "
              + "syntax was invalid.");
        }
      }
    }
  }
}
