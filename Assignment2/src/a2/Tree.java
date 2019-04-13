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
