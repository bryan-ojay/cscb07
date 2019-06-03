package test;

import org.junit.*;
import static org.junit.Assert.*;
import a2.Directory;
import driver.JShell;
import mock.MockFS;
import a2.Manual;
public class ManCMDTest {
	Manual man = new Manual();
	MockFS fileSystem = new MockFS();
	boolean fileSystemEnabled = false;

	public void setUp() {
		//configured so the filesystem is only created once
		if (!fileSystemEnabled) {
			fileSystemEnabled = true;
			JShell.root = new Directory("");
			JShell.curr = JShell.root;
			fileSystem.createSimpleMockFS();  //create a filesystem
		}
	}
	
	@Test
	public void testManMan() {
		setUp();
		String expected = "man CMD: displays the manual for CMD";
		String result = man.execute("man");
		assertEquals(expected, result);
	}

	@Test
	public void testManExit() {
		setUp();
		String expected = "exit: quits the JShell program";
		String result = man.execute("exit");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManCD() {
		setUp();
		String expected = "cd DIR: change current directory to DIR";
		String result = man.execute("cd");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManMkdir() {
		setUp();
		String expected = "mkdir DIR ...: creates directories DIRs";
		String result = man.execute("mkdir");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManLs() {
		setUp();
		String expected = "ls [PATH ...]:    display list of files and " +
		        "subdirectories " + "of current directory, or PATHs if given" + 
		        "\nls -R [PATH ...]: recursively display list of files and " + 
		        "subdirectories of the current directory, or PATHs if given";
		String result = man.execute("ls");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManPwd() {
		setUp();
		String expected = "pwd: prints the path of the current " +
		        "working directory";
		String result = man.execute("pwd");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManPushD() {
		setUp();
		String expected = "pushd DIR: saves the current directory " + 
		        "and changes the current directory to DIR";
		String result = man.execute("pushd");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManPopD() {
		setUp();
		String expected = "popd: changes the current directory " +
		        "to the last saved directory";
		String result = man.execute("popd");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManHistory() {
		setUp();
		String expected = "history [num]: displays ordered list of all " +
		        "recent commands, or of last 'num' commands, if given";
		String result = man.execute("history");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManCat() {
		setUp();
		String expected = "cat FILE1 [FILE2 ...]: " + 
		        "displays the contents of FILE1 (and FILE2, etc.) in the " +
		        "shell.";
		String result = man.execute("cat");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManEcho() {
		setUp();
		String expected = "echo \"STRING\": prints STRING";
		String result = man.execute("echo");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManMv() {
		setUp();
		String expected = "mv PATH1 PATH2: moves PATH1 into PATH2";
		String result = man.execute("mv");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManCp() {
		setUp();
		String expected = "cp PATH1 PATH2: copies PATH1 into PATH2";
		String result = man.execute("cp");
		assertEquals(expected, result);
	}


	@Test
	public void testManGetURL() {
		setUp();
		String expected = "get URL: retrieves and adds file at URL to directory.";
		String result = man.execute("get");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManSave() {
		setUp();
		String expected = "save FileName: saves the state of the shell into a " +
		        "file on the filesystem under FileName";
		String result = man.execute("save");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManLoad() {
		String expected = "load FileName: loads the saved state of the shell " +
		        "from a file on the filesystem under FileName";
		String result = man.execute("load");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManFind() {
		setUp();
		String expected = "find PATH ... -type [f|d] -name [expression]:\n" +
		        "    searches the given PATHs for a file/directory " +
		        "(given by type) that has the name \"expression\".";
		String result = man.execute("find");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManTree() {
		setUp();
		String expected = "tree: displays the entire filesystem in a tree-like " +
		"format.";
		String result = man.execute("tree");
		assertEquals(expected, result);
	}
	
	@Test
	public void testManInvalidCommand() {
		setUp();
		String expected = null;
		String result = man.execute("clear");
		assertEquals(expected, result);
	}
}
