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
