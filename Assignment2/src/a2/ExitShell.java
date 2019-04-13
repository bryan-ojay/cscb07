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
 * Represents the exit command. Users my use this command
 * to exit the JShell.
 */
public class ExitShell extends Command {

	/**
	 * Validates parameters for and executes the 'exit' command.
	 * @param input The parameters for the input.
	 * @return output null string
	 */
	public String execute(String input){
		String output = null;
		// if the input is not empty
		if (!(input.equals(""))) {
			// print a message saying the command's syntax is wrong
			System.out.println("The syntax of the command is incorrect.");
		}
		else {
			// if not, set the flag to false to exit the shell
			JShell.exitFlag = false;
		}

		return output;
	}
}
