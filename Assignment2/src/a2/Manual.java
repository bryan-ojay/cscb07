// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: oladejib
// UT Student #: 1004112738
// Author: Bryan Ifeoluwapo Oladeji
//
// Student2:
// UTORID user_name: singhd51
// UT Student #: 1004322280
// Author: Divneet Singh
//
// Student3:
// UTORID user_name: khulla18
// UT Student #: 1004325893
// Author: Jayesh Khullar
//
// Student4:
// UTORID user_name: mendezve
// UT Student #: 1004353479
// Author: Daniel Mendez Velarde
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package a2;

import java.util.*;

/**
 * Represents the man 'command' command. Users may see the manual of the
 * command entered in the parameters of man.
 */
public class Manual extends Command {
  // create private hashtable called manual with 2 string inputs for the
  // command name and description
  private Hashtable<String, String> manual = new Hashtable<String, String>(); 

  /**
   * Puts the manual (documentation) for each command in a hashtable
   */
  public Manual(){
    // set the manual to the hashtable
    manual = new Hashtable<String, String>();
    // put the man command in the manual
    manual.put("man","man CMD: displays the manual for CMD");
    // put the exit command in the manual
    manual.put("exit", "exit: quits the JShell program");
    // put the mkdir command in the manual
    manual.put("mkdir","mkdir DIR ...: creates directories DIRs"); 
    // put the cd command in the manual
    manual.put("cd","cd DIR: change current directory to DIR"); 
    // put the ls command in the manual
    manual.put("ls", "ls [PATH ...]:    display list of files and " +
        "subdirectories " + "of current directory, or PATHs if given" + 
        "\nls -R [PATH ...]: recursively display list of files and " + 
        "subdirectories of the current directory, or PATHs if given");
    // put the pwd command in the manual
    manual.put("pwd", "pwd: prints the path of the current " +
        "working directory");
    // // put the pushd command in the manual
    manual.put("pushd", "pushd DIR: saves the current directory " + 
        "and changes the current directory to DIR"); 
    // put the popd command in the manual
    manual.put("popd", "popd: changes the current directory " +
        "to the last saved directory"); 
    // put the history command in the manual
    manual.put("history", "history [num]: displays ordered list of all " +
        "recent commands, or of last 'num' commands, if given"); 
    // put the cat command in the manual
    manual.put("cat", "cat FILE1 [FILE2 ...]: " + 
        "displays the contents of FILE1 (and FILE2, etc.) in the " +
        "shell.");
    // put the echo command in the manual
    manual.put("echo", "echo \"STRING\": prints STRING");
    manual.put("mv", "mv PATH1 PATH2: moves PATH1 into PATH2");
    manual.put("cp", "cp PATH1 PATH2: copies PATH1 into PATH2");
    manual.put("get", "get URL: retrieves and adds file at URL to directory.");
    manual.put("save", "save FileName: saves the state of the shell into a " +
        "file on the filesystem under FileName");
    manual.put("load", "load FileName: loads the saved state of the shell " +
        "from a file on the filesystem under FileName");
    manual.put("find", "find PATH ... -type [f|d] -name [expression]:\n" +
        "    searches the given PATHs for a file/directory " +
        "(given by type) that has the name \"expression\".");
    manual.put("tree", "tree: displays the entire filesystem in a tree-like " +
        "format.");
  }

  /**
   * Validates parameters for and executes the 'manual' command.
   * @param input The parameters for the input.
   * @return output string with the appropriate command documentation
   */
  public String execute(String input) {
    String output = null;
    // split the input in a string array separated at each space
    String[] inputSplit = input.split(" ");

    // check if the array's length is not 1 or if it is empty
    if (inputSplit.length != 1 || input.equals("")) {
      // print a message saying the command's syntax is wrong
      System.out.println("The syntax of the command is incorrect.");
    }

    else {
      // get the inputted commands manual
      String cmdManual = manual.get(input);
      // check if the command's manual is null
      if (cmdManual == null) {
        // print a message saying the command does not exit
        System.out.println("The command specified does not exist.");
      }
      else {
        // otherwise, print command's manual
        output = cmdManual;
      }
    }
    // return the output
    return output;
  }
}
