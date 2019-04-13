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
