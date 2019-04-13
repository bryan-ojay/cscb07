package a2;
import java.util.*;

/**
 * Represents the 'find' command. User may check if given file/dir exists in
 * specified paths.
 */
public class Find extends Command {
	/**
	 * executes the find command and checks if the input is correct
	 * @param input contains the given paths to search, the type of item to
	 * search for, and the name of the item, it is inputted to shell as 
	 * a string
	 */
	public String execute(String input) {
		/*
		 * Create an empty String for the output so we can add to it later,
		 * create a regex for the correct syntax of the command
		 */
		String output = "";
		/*
		 * for the regex, the input may start with any amount of paths, and it
		 * is always followed by "-type (f or d) -name ("valid alpha numerical
		 * string")
		 */
		String correctSyntax = "(\\S+ )+-type [f|d] -name \"\\S+\"";
		// if the input is blank, show error message and do nothing
		if(input == "") {
			System.out.println("The syntax of the command is incorrect.");
		}
		else {
			/*
			 * if input is not blank, create a String array representation of
			 * the input. Create a String for the type and name using this
			 * String array.
			 */
			String[] splittedInput = input.split(" ");
			String type = splittedInput[splittedInput.length - 3];
			String name = splittedInput[splittedInput.length - 1];
			/*
			 * if the String array representation of the input is less than 5
			 * in length, then there is missing syntax. If the String input
			 * does not match the regex, then syntax is invalid. Print the
			 * corresponding message and do nothing.
			 */
			if(splittedInput.length < 5 || !input.matches(correctSyntax)) {
				System.out.println("The syntax of the command is incorrect.");
			}
			else {
				/*
				 * if syntax is valid, then create a String array that only
				 * contains the paths
				 */
				
				String[] paths = Arrays.copyOfRange(splittedInput, 
						0, splittedInput.length - 4);
				
				/* trim the name by removing quotes so we can add it to the
				 * new path
				 */
				String trimmedName = name.replace("\"", "");
					// loop through the String array of the paths
					for(int i = 0; i < paths.length; i++) {
						/*
						 * create a new path that includes the given file or
						 * directory at the end, so it can be used for the
						 * respective find function
						 */
						// calls the ls -R on the root
						// create ls command, and the tree representation
						ListContents treeLs = new ListContents();
						// execute ls command on the root
						String treeRep = treeLs.execute(paths[i]+" -R");
							/* if the type is directory, check if the given
							 * directory appears in the tree representation
							 * if it does, print respective message
							 */
							if(type.equals("d") && treeRep.contains("/" + 
							 trimmedName)) {
								output += "\n" + "Directory located in " + 
								paths[i] + "\n";
							}
							/* if the type is file, check if the given
							 * file appears in the tree representation
							 * if it does, print respective message
							 */
							else if(type.equals("f") && treeRep.contains
									(trimmedName)) {
								output += "\n" + "File located in " + paths[i] 
										+ "\n";
							}
							/* if it doesnt appear in tree, it does not
							 * exist in the given path(s). print respective
							 * message
							 */
							else {
								output += "\n" + "File or directory not found "
										+ "at "+ paths[i] + ", or paths do "
												+ "not exist. \n";
							}
					}
				}
			}
		return output;
	}
	
	/**
	 * Returns tree format of path
	 * @return string of format tree
	 */
	public String tree() {
		// calls the ls -R on the root
		// create ls command
		ListContents treeLs = new ListContents();
		// execute ls command on the root
		return treeLs.execute("paths");
	}
}