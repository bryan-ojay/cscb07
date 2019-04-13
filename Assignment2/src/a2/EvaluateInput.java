package a2;

import java.util.*;

/**
 * Evaluates the given input for the shell.
 */
public class EvaluateInput{
	/* Variables:
	 * - commands: A Hashtable mapping each command to its respective class.
	 * - instance: Ensures there is only one EvaluateInput instance.
	 * - created: Verifies that an EvaluateInput instance has been created
	 */
	private static Hashtable<String, String> commands;
	private static EvaluateInput instance;
	private static boolean created = false;

	/**
	 * adds the commands mapping to its respective class to the hashtable
	 */
	private EvaluateInput(){
		/* - Indicate that an EvaluateInput instance has been created
		 * - Initialize the commands Hashtable
		 */
		created = true;
		commands = new Hashtable<String, String>();

		//Add all of the commands into the Hashtable
		commands.put("exit","ExitShell");
		commands.put("mkdir","MakeDir");
		commands.put("cd","ChangeDir");
		commands.put("ls", "ListContents");
		commands.put("pwd", "PrintDir");
		commands.put("pushd", "PushDir");
		commands.put("popd", "PopDir");
		commands.put("history", "History");
		commands.put("cat", "Cat");
		commands.put("echo", "Echo");
		commands.put("man", "Manual");
		commands.put("save", "SaveFile");
		commands.put("cp", "Copy");
		commands.put("mv", "Move");
		commands.put("load", "LoadFile");
		commands.put("get", "GetURL");
		commands.put("tree", "Tree");
		commands.put("find", "Find");

	}

	/**
	 * Returns an instance of EvaluateInput. If an instance has already been 
	 * created, that instance will be returned.
	 * @return The created EvaluateInput instance
	 */
	public static EvaluateInput getInstance(){
		// check if created is false
		if (!created){
			// create a new instance of EvaluateInput if none has been created
			instance = new EvaluateInput();
			// set <created> to true
			created = true;
		}
		// return that instance
		return instance;
	}

	/**
	 * Calls the command class with the input checking for the instance
	 * validates the command is correct and has the correct parameter
	 * @param input The string representation of the input
	 */
	public void command(String input) {
		/* Remove all excess whitespace from the input, and split the input
		 * at the first space to get the command. */
		input = input.trim().replaceAll(" +", " ");
		String[] inputSplit = input.split(" ", 2);
		//System.out.println(Arrays.toString(inputSplit)); // for debugging

		// get the command name from the Hashtable
		String cmdName = commands.get(inputSplit[0]);
		//System.out.println(cmdName); //for debugging

		try {
			/* Turn the string representation of the command name into an 
			 * actual command. */
			Class<?> cmdClass = Class.forName("a2." + cmdName);
			Command command = (Command) cmdClass.newInstance();

			/* Execute the command with the given arguments (a.k.a the rest of
			 * the input). */
			String output;
			String redirection = "";
			try {
				String arguments = "";

				/* Check if the parameters matches the form:
				 * "[chars][>/>>] [file]"
				 */
				if (inputSplit[1].matches("(\\S|\\s)*>+ ?\\S+")) {
					/* Split the parameters into arguments and redirection:
					 * - arguments: "[chars][>/>>] file" becomes "[chars]"
					 * - redirection: "[chars][>/>>] file" becomes
					 * "[>/>>] file"
					 */
					arguments = inputSplit[1].replaceAll(" ?>+ ?.+$", "");
					redirection = inputSplit[1].replaceAll("[^>]* ?>", ">");
				}
				/* If it doesn't match the form, set the arguments to the
				 * parameters
				 */
				else {
					arguments = inputSplit[1];
				}
				// execute the arguments
				output = command.execute(arguments);
			}
			/* If an error is thrown, then there are no parameters. Execute
			 * the command with an empty string. */
			catch(Exception exception) {
				output = command.execute("");
			}
			//If the command gives an output, call the redirection method
			if (output != null) {
				command.direct(output, redirection);
			}
		}
		// If an error is thrown, then the command is not valid.
		catch(Exception exception){
			//If the command is invalid, notify the user.
			System.out.println("\"" + input + "\" is not a valid command.");
		}
	}
}
