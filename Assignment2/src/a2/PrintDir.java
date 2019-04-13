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

import driver.*;

/**
 * Represents the pwd command. Users may print the path of the current
 * working directory
 */
public class PrintDir extends Command {
	/**
	 * Print the current working directory (including the whole path)
	 * validates the command is correct and has the correct parameter
	 * @param input The string representation of the input
	 * @return output String representing the path of the current directory
	 */
	public String execute(String input) {
		String output = null;
		// check if the input is not empty
		if (!(input.equals(""))) {
			// let the user know the command's syntax is wrong
			System.out.println("The syntax of the command is incorrect.");
		}
		else {
			// otherwise print the current path
			output = JShell.curr.getPath();
		}
		// return the output
		return output;
	}
}
