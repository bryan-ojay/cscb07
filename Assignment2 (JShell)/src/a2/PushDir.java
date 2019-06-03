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

import driver.JShell;

/**
 * Represents the pushd command. Users may save the current working directory
 * by pushing  onto directory stack and then changes the new  current working 
 * directory to DIR.
 */
public class PushDir extends Command {

	/**
	 * Validates parameters for and executes the 'pushd' command.
	 * @param path The desired path.
	 */
	public String execute(String path) {

		// split the path into a string array separated at every space
		String[] pathSplit = path.split(" ");

		// check if the path is empty of the length <pathSplit> is > 1
		if (path.equals("") || pathSplit.length > 1) {
			// let the user know the command's syntax is wrong
			System.out.println("The syntax of the command is incorrect.");
		}
		else {
			try {
				// set the new dir path when findPath is called
				Directory newDir = findPath(path);
				// put the <curr>'s path into the stack
				JShell.directoryStack.push(JShell.curr.getPath());
				// set the <curr> to the <newDir>
				JShell.curr = newDir;
			}
			catch(Exception exception) {
				/*
				 * if the path is invalid, catch the error and give back an
				 * error message saying that it's invalid
				 */ 
				System.out.println("The given path is invalid.");
			}
		}
		return null;
	}
}
