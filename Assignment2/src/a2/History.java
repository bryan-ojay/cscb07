package a2;

import driver.*;

/**
 * Represents the history command.Users can see all of the input history
 * or history up to the nth term.
 */
public class History extends Command {

	/**
	 * Executes the 'History' command and makes sure the command is correct
	 * and has proper parameters.
	 * @param input Represents the "nth history" if inputted.
	 * @return output string representing the history of inputted commands
	 */
	public String execute(String input) {
		// split the input into an array separated at every space
		String[] inputSplit = input.split(" ");
		String output = null;

		// if the length of the input array is greater than 1
		if (inputSplit.length > 1) {
			// print a message saying the command's syntax is wrong
			System.out.println("The syntax of the command is incorrect.");
		}
		else {
			// if not, get the history with the input as its parameter
			output = getHistory(input);
		}
		// return the output
		return output;
	}

	/**
	 * Prints the input history up to the Nth most recent history
	 * if an N value is entered, else print the whole history.
	 * @param input representing the number of commands user wants to see
	 * @return history string containing the n number of commands
	 */
	public String getHistory(String input) {
		String history = "";
		// if the input is empty, aka input was just history
		if (input.equals("")) {
			/* print the shell's history by looping through history list
			 * and printing each input on a newline
			 */
			for(int i = 0; i < JShell.history.size(); i++) {
				history += (i+1) + ". " + JShell.history.get(i);
				if (i < JShell.history.size() - 1) { //formatting
					history += "\n";
				}
			}
		}
		else {
			try {
				/* parse the input into a number (for the number followed
				 * by "history") */
				int inputNum = Integer.parseInt(input);
				// if the number is negative
				if (inputNum < 0) {
					// print a message saying the number should be positive
					System.out.println("The given number should be greater "
							+ "than or equal to 0.");
				}
				else {
					// get the number of inputs in the history
					int hisLen = JShell.history.size(); 
					/* If the number is less than the history input size,
					 * set the input number equal to the number of commands
					 * in the history
					 */
					if (inputNum > hisLen) {
						inputNum = hisLen;
					}
					/* 
					 * print the history with most recent commands based on
					 * the input number, starting from the given input until
					 * the end of the list
					 */
					int hisIndex = hisLen - inputNum;
					for(int i = hisIndex; i < hisLen; i++) {
						history += (i+1) + ". " + JShell.history.get(i);
						if (i < JShell.history.size() - 1) { //formatting
							history += "\n";
						}
					}
				}
			}
			catch(Exception exception) {
				/*
				 * if the command's syntax is invalid, catch the error and
				 * give back an error message saying that it's invalid
				 */ 
				System.out.println("The syntax of the command is incorrect.");
			}
		}
		// check if the history is empty
		if (history.equals("")) {
			history = null;
		}
		// return the history
		return history;
	}
}
