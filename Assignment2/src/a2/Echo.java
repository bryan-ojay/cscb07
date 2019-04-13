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

/**
 * Represents the echo command. Users may create new files, update, or
 * replace contents of already created files.
 */
public class Echo extends Command {

	/**
	 * Executes the "echo" command and validates that parameters are correct.
	 * @param input The string of the input that gets added to a file.
	 * @return output as a string containing the contents
	 */
	public String execute(String input) {
		String output = null;
		// Create a regex string to check if the string contains quotes
		String correctString = "^\"[^\"]*\"$";		

		// If it does not contain quotes, then it is a valid format
		if (input.matches(correctString)) {
			output = input.substring(1, input.length() - 1);
		}

		//If it does, raise an error
		else {
			System.out.println("The syntax of the command is incorrect.");
		}
		// return the output
		return output;
	}
}
