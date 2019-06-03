package a2;

/**
 * Represents the mv command. User may move files/dirs to a specified paths.
 */
public class Move extends Command {
	/**
	 * executes the move command and checks if the input is correct
	 * @param path is the path inputted in the shell as a string
	 */
	public String execute(String path) {

		// check if the input path is empty
		if(path.equals("")) {
			// print a message saying the command's syntax is wrong
			System.out.println("The syntax of the command is incorrect.");
			// if not	
		} else {
			try {
				// split command to at the spaces, separates into 2
				String[] mvSplit = path.split(" ");
				// call move with the split path inputs
				move(mvSplit[0], mvSplit[1]);
			}
			catch (Exception exception) {
				/* if there is a missing path, catch the error and return an
				 * appropriate error message
				 */
				System.out.println("Please try again");
			}
		}
		// return null
		return null;
	}

	/**
	 * Calls the copy class's copy constructor to copy an object
	 * (file/directory) from one location to another and then remove the old
	 * object and location
	 * @param oldD is the old path/location (where it is moved from)
	 * @param newD is the new path/location (where it is moved to)
	 * @throws Exception 
	 */
	public void move(String oldD, String newD) throws Exception {
		// create the copy object
		Copy move1 = new Copy();
		// call the copy command method with the old and new path
		move1.copy(oldD, newD);
		// create objects for the old and new paths
		Object oldPath = null;
		Object newPath = null;

		// now we check if oldD and newD is a path to a dir or a file
		try { oldPath = findFile(oldD, false);
		} catch(Exception e) {
			// if not, find the directory
			oldPath = findPath(oldD);
		} try {
			/* call find file with <toPaste> concatenated and false for
			 * the boolean to create the file
			 */
			newPath = findFile(newD, false);
		}
		// if not a file, find the directory with <toPaste>
		catch(Exception e) { newPath = findPath(newD); }

		//make sure the user is not copying into the same dir
		// throw the exception if they do
		if(newD.startsWith(oldD) || newD.startsWith("/" + oldD)
				|| ("/" + newD).startsWith("/"+oldD)
				|| ("/" + newD).startsWith(oldD)
				|| oldD.startsWith("/"+newD)
				|| oldD.startsWith(newD)
				|| ("/" + oldD).startsWith("/"+newD)
				|| ("/" + oldD).startsWith(newD)) {
			throw new Exception("You can't copy the same paths!");
		} 
		
		// check if old path is a dir and new path is a file
		else if(oldPath.getClass().getSimpleName().equals("Directory") &&
				newPath.getClass().getSimpleName().equals("File")) {
			// throw an exception
			throw new Exception("You can't move a directory to a file");
		} else {
			// otherwise remove the old path
			// check if the old path does not contain a "/"
			if(!(oldD.contains("/"))) {
				// add it at the start of the old path
				oldD = "/" + oldD;
			}
			// create the object variable that should be removed
			Object removeObj = null;
			// gets the last index of the /
			int parentInd = oldD.lastIndexOf('/');
			// gets the path of the parent directory
			String parentDir = oldD.substring(0, parentInd);
			/* create a directory obj and set if equal to the parent dir
			 * of <oldD>
			 */
			Directory parentObj = findPath(parentDir);

			try {
				/* check if it's a file by calling findFile and store
				 * in <removeObj>
				 */
				removeObj = findFile(oldD, false);
			}
			catch(Exception exception) {
				// if it is not, catch the error and get the target directory
				removeObj = findPath(oldD);
			}
			// create a name object for the name of the <removeObj>
			Object name;
			// check if removeObj is a directory
			if(removeObj.toString().startsWith("/")) {
				// if it is, get the name without the "/"
				name = removeObj.toString().substring(1);
			} else {
				// else, its a file to set name to removeObj as a string
				name = removeObj.toString();
			}
			// remove the item
			parentObj.getContents().remove(name);
		}
	}
}
