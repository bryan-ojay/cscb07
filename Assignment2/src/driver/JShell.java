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
