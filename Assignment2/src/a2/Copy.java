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

/**
 * Represents the cp command. Users are able to copy the inputted
 * parameters in the following manner:
 * cp dir dir, 
 * cp file file, 
 * cp file dir.
 */
public class Copy extends Command {
	/**
	 * Executes the CP (copy command)
	 * Makes sure the parameters are correct
	 * @param is the new and old path
	 */
	public String execute(String path) {
		// check if the input path is empty
		if(path.equals("")) {
			// print a message saying the command's syntax is wrong
			System.out.println("The syntax of the command is incorrect.");
		}
		else {
			try {
				// split command to ["keyWord", "path"]
				String[] cmdSplit = path.split(" ");
				// check if the correct parameters entered are correct
				copy(cmdSplit[0], cmdSplit[1]);
			}
			// check if a path is missing
			catch (Exception except){
				System.out.println("There is a path missing.");
			}
		}
		return null;
	}

	/**
	 * Copies content of oldD to newD
	 * @param oldD is the path to copy
	 * @param newD id the path to copy into
	 */
	public void copy(String oldD, String newD) {
		/* let path and putInHere and name be of Type object s.t we may use
		 * them as files or dirs
		 */
		Object path = null;
		Object putInHere = null;
		Object name = null;
		try {
			// check if the path is singular i.e /a or /aaa
			String[] dirName = pathHelper(oldD);
			String[] toPaste = pathHelper(newD);
			// now we check if oldD and newD is a path to a dir or a file
			try {path = findFile(dirName[0] + dirName[1], false);}
			catch(Exception e) {
				// find the directory
				path = findPath(dirName[0]);
				// check the type of item to be copied
				name = ((Directory) path).getContents().get(dirName[1]);
			}
			try {
				/* call find file with <toPaste> concatenated and false for
				 * the boolean to create the file
				 */
				putInHere = findFile(toPaste[0] + toPaste[1], false);
			}
			// if not a file, find the directory with <toPaste>
			catch(Exception e) {putInHere = findPath(toPaste[0]+toPaste[1]);}
			//make sure the user is not copying into the same dir
			// throw the exception if they do
			if(newD.startsWith(oldD) || newD.startsWith("/" + oldD)
					|| ("/" + newD).startsWith("/"+oldD)
					|| ("/" + newD).startsWith(oldD)) {
				throw new Exception("You can't copy the same paths!");
			}
			if(oldD.startsWith("/"+newD) || oldD.startsWith(newD)
					|| ("/" + oldD).startsWith("/"+newD)
					|| ("/" + oldD).startsWith(newD)) {
				throw new Exception("You can't copy the same paths!");
			}
			// check if type of item to be pasted is file and the newD is a dir
			// this is the cp file dir case
			if(path.getClass().getSimpleName().equals("File") &&
					putInHere.getClass().getSimpleName().equals("Directory")){
				// get contents of file oldD
				String filePaste = ((File) path).getContents();
				// create a new file in newD with contents of file in oldD
				((Directory) putInHere).createFile(path.toString(), filePaste);
			} else if(putInHere.getClass().getSimpleName().equals("File") &&
					path.getClass().getSimpleName().equals("File")) {
				// check if both paths are files
				// this is the cp file file case
				// get the contents of file oldD
				String filePaste =  ((File) path).getContents();
				// update contents in file newD
				((File) putInHere).setContents(filePaste);
			}
			// else check if the type is dir
			// this is the cp dir dir/file case
			else if(name.getClass().getSimpleName().equals("Directory")) {
				// get the directory
				Directory dirPaste = ((Directory) path)
						.getDir(name.toString());
				// paste the directory
				((Directory) putInHere).placeDir(dirName[1], dirPaste);
				// check if the current directory has any children
				if(!(((Directory) path).getContents().isEmpty())) {
					// update the oldD and newD paths
					newD = newD + "/" + dirName[1];
					oldD = dirName[0] + dirName[1];
					// find the updated path of oldD
					path = findPath(oldD);
					// update the path to where we want to paste the file
					// try to get the path
					putInHere = findPath(newD);
					// get all of the contents of dir
					Object[] setKids = ((Directory) path)
							.getContents().keySet().toArray();
					// use a loop to traverse all the children
					for(int i = 0; i < setKids.length; i++) {
						// get child name
						String childName = setKids[i].toString();
						Object child = ((Directory) path)
								.getContents().get(childName);
						// get type of child
						Object typeOfChild = child.getClass().getSimpleName();
						// check if the type of child is a file
						if(typeOfChild.equals("File")) {
							String filePaste = ((Directory) path)
									.getFile(childName).getContents();
							//print the contents for test	
							((Directory) putInHere)
							.createFile(childName, filePaste);
						}
						// check if the type of child is a directory
						if(typeOfChild.equals("Directory")) {
							/* place the child and recurse to see if it
							 * has kids
							 */
							Directory dirChild = ((Directory) path)
									.getDir(childName);
							((Directory) putInHere)
							.placeDir(childName, dirChild);
							// update the path to paste from						
							String childD = oldD + "/" + childName;							
							// recursive case
							copy(childD, newD);
						} else {return;}
					}
				} else {return;}
			}
		} catch (Exception e) {
			// catch the error and print an appropriate incorrect path msg
			System.out.println("There is an incorrect path.");
		}
	}

	/**
	 * Return a String array containing parent path and path to be copied
	 * @param path is the path to split into parent and path to be copied
	 * @return pathSplit String array containing the targetDir and the name
	 * of the new directory
	 */
	public String[] pathHelper(String path) {
		// split oldD path into array
		// Split the path by the last '/' in the string.
		int dirIndex = path.lastIndexOf('/') + 1;
		// The first part is the parent directory of the new directory.
		String targetDir = path.substring(0, dirIndex);
		// The second part will be the name of the new directory. 
		String dirName = path.substring(dirIndex);
		// put them in a string array
		String[] pathSplit = {targetDir, dirName};
		// return the string array
		return pathSplit;
	}
}
