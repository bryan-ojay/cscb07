package test;

import org.junit.*;
import static org.junit.Assert.*;
import a2.Directory;
import a2.ExitShell;
import a2.MakeDir;
import driver.JShell;
import mock.MockFS;

public class TestExit {
	ExitShell exit = new ExitShell();
	MakeDir mkdir = new MakeDir();
	MockFS fileSystem = new MockFS();
	boolean fileSystemEnabled = false;

	public void setUp() {
		//configured so the filesystem is only created once
		if (!fileSystemEnabled) {
			fileSystemEnabled = true;
			JShell.root = new Directory("");
			JShell.curr = JShell.root;
			JShell.exitFlag = true;
			fileSystem.createSimpleMockFS();  //create a filesystem
		}
	}

	@Test
	public void testExitWithNoPreviousCommands() {
		setUp();
		// exit the shell
		boolean exitTrue = false;
		exit.execute("");
		Boolean result = JShell.exitFlag;
		assertEquals(exitTrue, result);
	}
	
	@Test
	public void testExitWithPreviousCommands() {
		setUp();
		// exit the shell after executing a command
		mkdir.execute("a");
		boolean exitTrue = false;
		exit.execute("");
		Boolean result = JShell.exitFlag;
		assertEquals(exitTrue, result);
	}
	
	@Test
	public void testExitWithParameter() {
		setUp();
		// exit the shell
		boolean exitTrue = true;
		exit.execute("-R");
		Boolean result = JShell.exitFlag;
		assertEquals(exitTrue, result);
	}
	
	
}
