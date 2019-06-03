package test;

import org.junit.*;
import static org.junit.Assert.*;
import a2.Directory;
import a2.Move;
import a2.Tree;
import a2.Find;
import a2.MakeDir;
import driver.JShell;
import mock.MockFS;

public class TestFind {
	MakeDir mkdir = new MakeDir();
	Move mv = new Move();
	Tree tree = new Tree();
	Find find = new Find();
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
	public void testFindFileInRoot() {
		setUp();
		String expected = "\nFile located at /dir1\n";
		String result = find.execute("/dir1 -type f -name \"file1\"");
		assertEquals(expected, result);
	}
	
	@Test
	public void testFindFileInDir() {
		setUp();
		String expected = "\nFile located at /dir1\n";
		String result = find.execute("/dir1 -type f -name \"file1\"");
		assertEquals(expected, result);
	}
	
	@Test
	public void testFindDirInDir() {
		setUp();
		// make a new dir
		mkdir.execute("dir1/dir4");
		String expected = "\nDirectory located at /dir1\n";
		String result = find.execute("/dir1 -type d -name \"dir4\"");
		assertEquals(expected, result);
	}
	
	@Test
	public void testFindInvalidFile() {
		setUp();
		String expected = "\nFile or directory not found at "
				+ "/dir1, or paths do not exist. \n";
		String result = find.execute("/dir1 -type f -name \"file6\"");
		assertEquals(expected, result);
	}
	
	@Test
	public void testFindInvalidDir() {
		setUp();
		// make a new dir
		mkdir.execute("dir1/dir4");
		String expected = "\nFile or directory not found at "
				+ "/dir1, or paths do not exist. \n";
		String result = find.execute("/dir1 -type d -name \"dir10\"");
		assertEquals(expected, result);
	}
}
