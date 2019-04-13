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
 * Represents a file in the shell.
 */
public class File {
	/**
	 * Initialize private variables to represent
	 * file name, the directory where the file is stored
	 * and the contents of the file.
	 */
	private String name;
	private Directory dir;
	private String contents;

	/**
	 * Constructs a File obj with name @param name,
	 * and directory @param directory
	 * @param name represents the name of the File obj to be created.
	 * @param dir represents the name of the Directory where obj to be created
	 */
	public File(String name, Directory dir) {
		// initialize the name and directory
		this.name = name;
		this.dir = dir;
	}

	/**
	 * Constructs a File obj with name @param name, contents @param contents
	 * and directory @param directory
	 * @param name represents the name of the File obj to be created.
	 * @param dir represents the name of the Directory where obj to be created
	 * @param contents represents the contents of the file obj.
	 */
	public File(String name, Directory dir, String contents) {
		// create the instance variables for the name, directory and contents
		this.name = name;
		this.dir = dir;
		this.contents = contents;
	}

	/**
	 * Overwrites the toString 
	 * @return String with the name of the file.
	 */
	public String toString() {
		// return the name
		return this.name;
	}

	/**
	 * Gets the path of the file
	 * @return The path of the file.
	 */
	public String getPath() {
		// return the path, name and a "/"
		return this.dir.getPath() + this.name + "/";
	}

	/**
	 * Gets the contents
	 * @return The contents of the file. 
	 */
	public String getContents(){
		// return the contents
		return this.contents;
	}

	/**
	 * Sets or replaces the contents in the file to the given contents.
	 * @param contents The new contents of the file.
	 */
	public void setContents(String contents){
		// set the contents with the given contents
		this.contents = contents;
	}

	/**
	 * Appends the given contents to the current contents in the file.
	 * @param contents The contents to add to the file.
	 */
	public void addContents(String newContents){
		// adds the new contents to the old contents
		this.contents = this.contents + "\n" + newContents;
	}
}
