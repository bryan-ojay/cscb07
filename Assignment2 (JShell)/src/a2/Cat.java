package a2;

/**
 * Represents the CAT command which reads & outputs the contents of file(s)
 * in the parameter.
 */
public class Cat extends Command {

	/**
	 * Displays the contents of each file inputted separated by 3 line breaks.
	 * validates the command is correct and has the correct parameter
	 * @param files String representation of the inputted files
	 * @return output String for the contents of the file concatenated
	 */
	public String execute(String files){
		// Create a file object
		File file;
		// create the output string variable
		String output = "";

		// check if the files is empty
		if (files.equals("")) {
			// if it is, create an error message for invalid syntax
			System.out.println("The syntax of the command is incorrect.");
		}

		// if not
		else {
			// create a string Arraylist and separate each index at a space 
			String[] filePaths = files.split(" ");
			// traverse the ArrayList with a for loop to find all of the files
			for(int i = 0; i < filePaths.length; i++) {
				try {
					//Get the file at the given path
					file = findFile(filePaths[i], false);
					// print the file with its contents
					output += filePaths[i] + ":\n" + file.getContents() +
							"\n\n\n";
				}
				/*
				 * if file does not exist, catch the exception and give back
				 * an error message saying the file does not exist
				 */
				catch(Exception exception) {
					System.out.println("The file \"" + filePaths[i] + 
							"\" does not exist " + "or is a directory.");
				}   
			}
		}
		//an empty string is equivalent to no output
		if (output == "") {
			output = null;
		}
		// return the output
		return output;
	}
}