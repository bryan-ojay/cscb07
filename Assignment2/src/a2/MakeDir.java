package a2;

/**
 * Represents the mkdir command. Users may create dir(s) in the root dir
 * or other dirs in the root.
 */
public class MakeDir extends Command {

	/**
	 * Executes the "mkdir" command and validates that the parameters are
	 * correct.
	 * @param paths The string representation of the paths that need to be
	 * created.
	 */
	public String execute(String paths) {

		// Check validity of parameter syntax, check if it's empty
		if (paths.equals("")) {
			// print a message saying the command's syntax is wrong
			System.out.println("The syntax of the command is incorrect.");
		}
		else {
			// split the path into a string array separated at every space
			String[] allPaths = paths.split(" ");
			// traverse through the path array
			for(int i = 0; i < allPaths.length; i++) {
				// Call makeDir on each directory given
				makeDir(allPaths[i]);
			}
		}
		// return null
		return null;
	}

	/**
	 * Creates a directory with the name 'name'.
	 * @param name Name of the directory to be created.
	 */
	public void makeDir(String path){
		// If the path ends with a slash, remove it
		path = path.replaceFirst("/$", "");
		// Split the path by the last '/' in the string.
		int dirIndex = path.lastIndexOf('/') + 1;
		// The first part is the parent directory of the new directory.
		String targetDir = path.substring(0, dirIndex);
		// The second part will be the name of the new directory. 
		String dirName = path.substring(dirIndex);

		//Create a regex to match all of the invalid file names.
		String invalidChars = "\\S*[.!@#$%^&*(){}~|<>?]+\\S*";

		// check if the directory name has any of the invalid chars or is empty
		if (dirName.matches(invalidChars) || dirName.matches("")) {
			// print a message saying the name is invalid
			System.out.println("The directory name '" + dirName +
					"' is invalid.");
		}

		/*
		 * - Check to see if the parent directory exists.
		 * - If it does, check to see if there is a directory with the same
		 * name as the new directory.
		 * - If there isn't, create the new directory.
		 */
		else {
			try {
				// get the path of the current directory
				Directory currentDir = findPath(targetDir);
				/* Check to see if there is a directory with
				 * the same name as the new directory. */
				if(currentDir.getContents().containsKey(dirName)) {
					System.out.println("The directory '" + path +
							"' already exists."); 
				}
				// If there isn't, create the new directory.
				else {
					currentDir.putIn(dirName);
				}
			}
			catch(Exception exception) {
				/* If the path is invalid, catch the error and give back an
				 * error message saying that it's invalid */
				System.out.println("The path " + path + " is invalid.");
			}
		}
	}
}
