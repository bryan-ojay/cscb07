// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: oladejib
// UT Student #: 1004112738
// Author: Bryan Ifeoluwapo Oladeji
//
// Student2:
// UTORID user_name: singhd51
// UT Student #: 1004322280
// Author: Divneet Singh
//
// Student3:
// UTORID user_name: khulla18
// UT Student #: 1004325893
// Author: Jayesh Khullar
//
// Student4:
// UTORID user_name: mendezve
// UT Student #: 1004353479
// Author: Daniel Mendez Velarde
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package a2;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import driver.*;

/**
 * Represents the save 'filename' command. Users may create file and store
 * it on the users actual computer file system.
 */
public class SaveFile extends Command {
	/**
	 * Initialize private variables to represent
	 * shell history,
	 * shell stack,
	 * directories in the shell,
	 * files in the shell.
	 */
	private String shellHistory = "";
	private String shellStack = "";
	private String directories = "";
	private String files = "";

	/**
	 * Executes the 'save' command.
	 * @param fileName The path of the file that will contain the shell data.
	 */
	public String execute(String fileName) {
		//get all the directories and files in the filesystem
		getData(JShell.root);

		//set the shellStack variable to the be the stack of saved directories
		String dirStack = JShell.directoryStack.toString();
		shellStack = dirStack.substring(1, dirStack.length() - 1);

		/* add all the elements of the shell history (on separate lines) to
		 * the shell history variable */
		for (String cmd:JShell.history){
			shellHistory += cmd + "\n";
		}

		//concatenate all the data of the shell
		String shellData = directories + "\n\"\"\"\n" + files + "\n\"\"\"\n" + 
				shellStack + "\n\"\"\"\n" + shellHistory;

		//write the <shelldata> to a file
		writeToFile(fileName, shellData); 
		// return null
		return null;
	}

	/**
	 * Obtains the directories, the files and the data of the files in the
	 * given directory and formats it into a string representation of all the
	 * objects.
	 * @param currentDir The directory to obtain contents from.
	 */
	public void getData(Directory currentDir) {
		//get contents of the given directory
		Hashtable<String, Object> contents = currentDir.getContents();

		//loop through all the contents in the given directory
		for (String dir:contents.keySet()) {

			//get the object in the given directory and its class name
			Object dirOrFile = contents.get(dir);
			String dataType = dirOrFile.getClass().getSimpleName();

			//if the object is a file, add it to the string of files
			if (dataType.equals("File")) {
				String filePath = currentDir.getPath() + dir;
				files += filePath + " \"" + ((File) dirOrFile)
						.getContents() + "\" "; 
			}

			/* if the object is a directory, add it to the string of
			 * directories and get all the data inside of it
			 */
			else if (dataType.equals("Directory")) {
				Directory dirParent = ((Directory) dirOrFile).getParent();
				directories += dirParent.getPath() + dir + " ";
				getData((Directory) dirOrFile);
			}
		}
	}

	/**
	 * Writes the data of the shell to a file on the user's actual filesystem.
	 * Notifies the user if the file name or path is invalid.
	 * @param fileName The path of the desired file
	 * @param data The data of the shell
	 */
	public void writeToFile(String fileName, String data) {
		//create new output stream
		OutputStream saveFile;
		try {
			/* open a new file with the given path 'fileName' and write the
			 * shell data into it */
			saveFile = new FileOutputStream(new java.io.File(fileName));
			saveFile.write(data.getBytes());
			saveFile.close();
		} 
		catch (Exception exception) {
			//if an exception is thrown, then the file path is invalid
			System.out.println("The file path is invalid.");
		}
	}
}
