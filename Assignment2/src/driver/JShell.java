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
package driver;

import java.util.*;
import a2.*;

/**
 * Instantiates the shell.
 */
public class JShell {

	/* Variables:
	 * - root: The root directory; the head of the filesystem
	 * - curr: The current working directory
	 * - exitFlag: Prompts the shell to exit
	 * - history: The history of commands
	 * - directoryStack: The stack of all saved directories
	 */
	public static Directory root = new Directory("");
	public static Directory curr = root;
	public static boolean exitFlag = true;
	public static ArrayList<String> history = new ArrayList<String>();
	public static Stack<String> directoryStack = new Stack<String>();

	/**
	 * Runs the shell and initiates the input
	 * @param args
	 */
	public static void main(String[] args) {
		// create a new input
		Input in = new Input(); 

		// while loop that runs the shell until the exit command is called
		while(exitFlag) {
			//prompt the user to input, then process the command
			System.out.print("JShell " + curr.getPath() + ">: ");
			String cmd = in.userInput();
			command(cmd);
		}
	}

	/**
	 * Adds each entered command to the history and evaluates the command
	 * @param cmd
	 */
	public static void command(String cmd) {
		/* Retrieve an instance of EvaluateInput to parse the command, then
		 * add the command to the history of commands
		 */
		EvaluateInput eval = EvaluateInput.getInstance();
		history.add(cmd);  
		// evaluate the command by calling command
		eval.command(cmd);
	}
}
