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

import java.util.Hashtable;
import driver.JShell;

/**
 * Represents the ls command. The user may list dir contents of dirs normally
 * or recursively using -R.
 */
public class ListContents extends Command {

	/**
	 * executes the ls command by listing the contents of the current directory
	 * when "-R" is inputted, it lists all sub-directories of the current dir 
	 * @param paths takes input a string representation of a path
	 * @returns the (sub)directories as a string
	 */
	public String execute(String paths) {
		// create an string output variable
		String output = "";
		//set boolean value to check if recursive
		boolean recursive = false;
		/* if the input contains -R, set the boolean to true and
		 * remove it from input
		 */
		if (paths.contains("-R")) {
			// set the recursive boolean to true
			recursive = true;
			/* replace -R if:
			 * 1. it's the only parameter
			 * 2. there's a space before it
			 * 3. there's a space after it
			 */
			paths = paths.replaceFirst("(^-R$| -R|^-R )", "");
		}
		// check if the path is empty
		if (paths == "") {
			// print the contents of the current directory location
			output = printContents(JShell.curr, recursive, 0);
		}
		else {
			//separate the paths in an array
			String[] pathSplit = paths.split(" +");

			//loop through all the paths
			for (String path:pathSplit) {
				// get the parent directory of the path
				// Split the path by the last '/' in the string.
				int dirIndex = path.lastIndexOf('/') + 1;
				// The first part is the parent directory of the new directory
				String targetDir = path.substring(0, dirIndex);
				// The second part will be the name of the new directory. 
				String dirName = path.substring(dirIndex);

				try {
					/* create an object that will be used to find and get the
					 * object from the parent dir
					 */
					Object getObj;
					try {
						// call find path from the command class
						getObj = findPath(path);
					}
					catch(Exception exception) {
						/* if there is nothing at the location, get the parent
						 * directory by calling find path with the <targetDir>
						 */
						Directory parentDir = findPath(targetDir);
						// get the name of the directory
						getObj = parentDir.getContents().get(dirName);
					}

					// if getObj is null, object doesn't exist
					if (getObj == null) {
						// print out a path DNE message
						System.out.println("The path does not exist.");
					}
					//if a file, print the file name
					else {
						/* call the print contents with <getObj> starting at
						 * level 0
						 */
						output += printContents(getObj, recursive, 0);
					}
				}
				//If an error is thrown, the parent directory is invalid
				catch(Exception exception){
					System.out.println("Error.");
				}
			}
		}
		// check if the output is empty
		if (output.equals("")) {
			// set it to null
			output = null;
		}
		// return the output
		return output;
	}

	/**
	 * prints the contents recursively keeping track of the levels,
	 * format/spacing and sub directories and files inside the obj
	 * @param obj is the directory/file you are currently in
	 * @param recursive represents whether or not you need to recursively
	 * access/list the sub-contents
	 * @param level is the level of the sub-directory you are in
	 * @returns the (sub)directories as a string with the correct formatting
	 */
	public String printContents(Object obj, boolean recursive, int level) {
		// create a string tree variable for the output
		String tree = "";
		// create a tabs variable to keep track of the level u are in
		String tabs = "";
		// loop through the level
		for(int i = 0; i < level; i++) {
			// add the amount to tabs equal to the level
			tabs += "\t";
		}
		// add tabs and the object (file/dir) followed by a new line to tree
		tree += tabs + obj.toString() + "\n";

		//if it's a dir (check if recursive), print the dir name and contents
		if (obj.getClass().getSimpleName().equals("Directory")){
			// get the contents of the object
			Hashtable<String, Object> contents =
					((Directory) obj).getContents();

			// loop through the contents
			for (String dir:contents.keySet()) {
				// get the key from the contents
				Object dirOrFile = contents.get(dir);
				// check if it is recursive
				if (recursive) {
					/* if it is, recursively call printContents again with
					 * the appropriate parameters while concatenating to the
					 * tree output
					 */
					tree += printContents(dirOrFile, recursive, level + 1);
				}
				else {
					// else, add the string representation of the <dirOrFile>
					tree += "\t" + dirOrFile.toString() + "\n";
				}
			}
		}
		// return the tree output
		return tree;
	}
}
