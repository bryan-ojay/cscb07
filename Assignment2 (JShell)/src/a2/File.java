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
