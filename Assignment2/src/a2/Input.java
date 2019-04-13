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
 * Represents the Input in the JShell.
 */
public class Input {
	/**
	 * Takes in user input, returns the input without whitespace
	 * @return The given user input, without leading and trailing whitespace
	 */
	public String userInput() {
		// create the scanner to allow us to input in the shell
		Scanner input = new Scanner(System.in);
		// trim the input
		String in = input.nextLine();
		// return the input
		return in;
	}
}