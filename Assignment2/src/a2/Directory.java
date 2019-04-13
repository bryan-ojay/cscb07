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

import java.util.*;

/**
 * Represents a Directory in the shell.
 * Our Hashtable implementation of Directory uses Generics
 * because Hashtables use Generics.
 */
public class Directory {
	// create a private string variable for the name
	private String name;
	// create a directory hashtable
	private Hashtable<String, Object> contents = new Hashtable<>();
	// create a private directory parent
	private Directory parent;

	/**
	 * Constructs a Directory obj with the specified name.
	 * @param dirName represents the name of the Directory obj to be created.
	 */
	public Directory(String dirName) {
		// set the name to the inputted dir_name
		this.name = dirName;
		//  initialize the parent
		this.parent = this;
	}

	/**
	 * Constructs a Directory obj with the specified name,and parent
	 * @param dirName represents the name of the Directory obj to be created.
	 * @param parent represents the name of the parent Directory.
	 */
	public Directory(String dirName, Directory parent) {
		// set the name to the inputted dir_name
		this.name = dirName;
		// set the parent to the inputted parent
		this.parent = parent;
	}

	/**
	 * Returns the directory name
	 * @return name of the directory
	 */
	public String getName() {
		return name;
	}

	/**
	 * Provides a string representation of the directory.
	 * @return name of the directory preceded by a '/'.
	 */
	public String toString() {
		// return a "/" concatenated with the name
		return "/" + this.name;
	}

	/**
	 * Places directory param into the current directories contents
	 * @param dirName represents the name of the Directory obj to be created.
	 * @param toPlace represents the name of the path where 
	 * 		  the Directory obj to be created.
	 */
	public void placeDir(String dirName, Directory toPlace) {
		// but the <dirName> param and a new directory into the hashtable
		this.contents.put(dirName, new Directory(dirName, this));
	}

	/**
	 * Inserts a sub-directory into the directory
	 * @param name The name of the sub-directory.
	 */
	public void putIn(String name) {
		// put the name and a new directory in the directory hashtable
		this.contents.put(name, new Directory(name, this));
	}

	/**
	 * Returns the parent of the directory.
	 * @return The parent of the directory.
	 */
	public Directory getParent() {
		// return the parent
		return this.parent;
	}

	/**
	 * Returns the sub-directories and files of the directory.
	 * @return A set of all the contents of the directory.
	 */
	public Hashtable<String, Object> getContents() {
		// return the contents of the hashtable
		return this.contents;
	}

	/**
	 * Returns a directory with given name.
	 * @param name The name of the desired directory.
	 * @return The directory of given name or null if it does not exist.
	 */
	public Directory getDir(String name) {
		// return the directory from inputted name in the directory hashtable
		return (Directory) this.contents.get(name);
	}

	/**
	 * Returns a file with the given name.
	 * @param name The name of the desired file.
	 * @return The file of given name or null if it does not exist. 
	 */
	public File getFile(String name) {
		// return the file from inputted name in the hashtable
		return (File) this.contents.get(name);
	}

	/**
	 * Creates a file and inserts it into the directory
	 * @param name The name of the created file.
	 * @param contents The contents of the created file.
	 */
	public void createFile(String name, String contents) {
		// create a new file with the given name
		File newFile = new File(name, this);
		// put the file into the hashtable
		this.contents.put(name, newFile);
		// set the contents of the new file to the given contents
		newFile.setContents(contents);
	}

	/**
	 * Provides the full path of the directory.
	 * @return The full path of the directory.
	 */
	public String getPath() {
		// create a path string
		String path;

		// check if Directory is root
		if (this.parent == this) {
			// then set the path to "/"
			path = "/";
		}

		// if not
		else {
			/*
			 * then set the path to the parents path concatenated with the
			 * name followed by a "/" recursively
			 */
			path = this.parent.getPath() + this.name + "/";
		}

		//return the path
		return path;
	}
}
