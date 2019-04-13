package a2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Stack;
import driver.*;

/**
 * Represents the loadfile command. User may load the contents of the 
 * FileName and reinitialize everything that was saved previously into the
 * FileName.
 */
public class LoadFile extends Command {
	// create a MakeDir and Echo object
	MakeDir mkDir = new MakeDir();
	Echo echo = new Echo();

	/**
	 * Validates parameters for and executes the 'load' command.
	 * @param fileName string representing the name of the file
	 */
	public String execute(String fileName) {
		// check if the file name is empty
		if (fileName.equals("")) {
			// print an appropriate error message
			System.out.println("The syntax of the command is incorrect.");
		}
		// check if the history size is greater than 1
		else if (JShell.history.size() > 1) {
			// print an appropriate message
			System.out.println("This command is disabled because another "
					+ "command was previously entered. Please restart the "
					+ "shell and try again.");
		}
		else {
			try {
				// Retrieve the save file and load the contents from the file
				BufferedReader reader =
						new BufferedReader(new FileReader(fileName));
				loadContents(reader);
				// close the reader
				reader.close();
			}
			catch(Exception exception){
				// Reset the shell contents
				JShell.root = new Directory("");
				// Create a new Stack object
				JShell.directoryStack = new Stack<String>();
				// Notify the user if the file could not be read
				System.out.println("The file was invalid or not found.");
			}
		}
		// return null
		return null;
	}

	/**
	 * Checks if the file is in the valid format.
	 * @param line The line that needs to be examined for validation.
	 * @throws Exception if the file does not exist. 
	 */
	private void checkFile(String line) throws Exception {
		// check if the line input equals \\\
		boolean isValid = line.equals("\"\"\"");
		// if it does not
		if (!isValid) {
			// throw an appropriate exception message
			throw new Exception("Invalid format.");
		}
	}

	/**
	 * Loads the contents in the save file.
	 * @param reader The BufferedReader that reads the save file.
	 * @throws Exception If the save file is in an invalid format.
	 */
	private void loadContents(BufferedReader reader) throws Exception {
		//read the list of directories
		String lineOfData = reader.readLine();
		// check if <lineOfData> is not empty
		if (!lineOfData.equals("")) {
			// execute the mkdir command with <lineOfData>
			mkDir.execute(lineOfData);
		}
		// call checkFile
		checkFile(reader.readLine());

		//read the list of files
		String[] dataSplit = reader.readLine().split("( \"|\" )");
		if (!(dataSplit.length == 1 && dataSplit[0].equals(""))) {
			// loop through the length of <dataSplit> every 2nd index
			for (int i = 0; i < dataSplit.length; i += 2) {
				// call the redirection
				echo.direct(dataSplit[i+1], "> " + dataSplit[i].trim());
			}
		}
		// call checkFile
		checkFile(reader.readLine());

		//read/split the stack of saved directories
		dataSplit = reader.readLine().split(", ");
		//loop through and add all the elements to the shell's directory stack 
		for (int i = 0; i < dataSplit.length; i++) {
			String cmd = dataSplit[i];
			JShell.directoryStack.add(cmd);
		}
		// call checkFile
		checkFile(reader.readLine());

		//loop through and add all the inputs to the shell's history
		int i = 0;
		while ((lineOfData = reader.readLine()) != null) {
			JShell.history.add(i, lineOfData);
			i++;
		}
	}
}
