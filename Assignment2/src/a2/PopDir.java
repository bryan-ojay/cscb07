package a2;

import driver.JShell;

/**
 * Represents the popd command. Users may remove the top entry from 
 * the directory stack, and cd into it
 */
public class PopDir extends Command {

	/**
	 * Validates parameters for and executes the popd command.
	 * @param input String for syntax correctness
	 */
	public String execute(String input) {
		// check if the input is not empty
		if (!(input.equals(""))) {
			// let the user know the commands syntax is wrong
			System.out.println("The syntax of the command is incorrect.");
		}
		else {
			try {
				// set the <curr> to the path after you pop from the dir stack
				JShell.curr = findPath(JShell.directoryStack.pop());
			}
			catch(Exception emptyException) {
				/*
				 * if there are no saved directories, catch the error and
				 * give an appropriate error message
				 */
				System.out.println("There are no saved directories.");
			}
		}
		return null;
	}
}
