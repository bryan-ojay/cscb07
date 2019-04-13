package a2;

import driver.*;
/**
 * Represents the cd command which reads the input param and changes
 * directory to the inputed path.
 */
public class ChangeDir extends Command {

  /**
   * Changes the directory to the specified directory via either a full path
   * or relative to the current directory
   * .. refers to the parent directory
   * . refers to the current directory
   * the directory must be a '/' while the foot is also a '/'
   * validates the command is correct and has the correct parameter
   * @param path The string representation of the path
   */
  public String execute(String path){
    // create an array that splits the given path at every space
    String[] pathSplit = path.split(" ");

    // check if the path is empty or the array's length is greater than 1
    if (path.equals("") || pathSplit.length > 1) {
      // then print out a message saying the command's syntax is wrong
      System.out.println("The syntax of the command is incorrect.");
    }
    // if not
    else {
      try {
        /*
         * set curr equal to the location of the directory present in
         * the path
         */
        JShell.curr = findPath(path);
      }
      /*
       * if the path is invalid, catch the error and give back an error
       * message saying invalid path
       */
      catch(Exception exception) {
        System.out.println("Invalid path.");
      }
    }
    // return null
    return null;
  }
}