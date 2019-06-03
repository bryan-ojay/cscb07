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
