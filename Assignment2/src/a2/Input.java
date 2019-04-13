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