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
 * Represents the tree command. Users may see the file tree starting at
 * the root.
 */
public class Tree extends Command{

	/**
	 * Executes the Tree command.
	 * @param empty is an empty string to make sure no params are inputed
	 * for tree
	 */
	public String execute(String empty) {
		// because tree takes in no inputs we must make sure its empty
		if(!(empty.equals(""))) {
			System.out.println("This command takes in no Parameters!");
		}
		else {
			// run the tree command
			return tree();
		}
		return null;
	}
	/**
	 * Returns ListContents command on the root to get filesystem recursively
	 * @return call on ListContent's execute with "/ -R"
	 */
	public String tree() {
		// calls the ls -R on the root
		// create ls command
		ListContents treeLs = new ListContents();
		// execute ls command on the root
		return treeLs.execute("/ -R");
	}
}
