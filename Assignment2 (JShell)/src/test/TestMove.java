package test;

import org.junit.*;
import static org.junit.Assert.*;
import a2.Directory;
import a2.Move;
import a2.Tree;
import a2.Cat;
import driver.JShell;
import mock.MockFS;

public class TestMove {
	Move mv = new Move();
	Tree tree = new Tree();
	Cat cat = new Cat();
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
	public void testMoveWithRoot(){
		setUp();
		// the file system should stay the same
		String expected = "/\n\t/dir2\n\t\tfile2\n\t/dir1\n\t\tfile1\n";
		// result of running tree
		mv.execute("/ dir2");
		String result = tree.execute("");
		assertEquals(expected, result);
	}

	@Test
	public void testMoveWithDir1() {
		setUp();
		// the file system should stay the same
		String expected = "/"
				+ "\n\t/dir1\n\t\tfile1\n\t\t/dir2\n\t\t\tfile2\n";
		// result of running tree
		mv.execute("/dir2 dir1");
		String result = tree.execute("");
		assertEquals(expected, result);
	}
	
	@Test
	public void testMoveWithDir2() {
		setUp();
		// the file system should stay the same
		String expected = "/"
				+ "\n\t/dir2\n\t\tfile2\n\t\t/dir1\n\t\t\tfile1\n";
		// result of running tree
		mv.execute("/dir1 dir2");
		String result = tree.execute("");
		assertEquals(expected, result);
	}
	
	@Test
	public void testMoveWithSameDir() {
		setUp();
		// the file system should stay the same
		String expected = "/\n\t/dir2\n\t\tfile2\n\t/dir1\n\t\tfile1\n";
		// result of running tree
		mv.execute("/dir2 dir2");
		String result = tree.execute("");
		assertEquals(expected, result);
	}
	
	@Test
	public void testMoveWithFileToFile(){
		setUp();
		// the file system should stay the same
		String expected = "dir1/file1:\nhello world\n\n\n";
		// result of running tree
		mv.execute("dir2/file2 dir1/file1");
		String result = cat.execute("dir1/file1");
		assertEquals(expected, result);
	}
}

