package test;
import org.junit.*;
import static org.junit.Assert.*;
import a2.Directory;
import a2.GetURL;
import driver.JShell;
import mock.MockFS;
import mock.MockURL;

// We were not sure how to properly test this command.
public class TestGetUrl {
	GetURL get = new GetURL();
	MockFS fileSystem = new MockFS();
	MockURL mockUrlHtml = new MockURL();
	MockURL mockUrlTxt = new MockURL();
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
	public void testGetURLTxt() {
		setUp();
		String expected = null;
		String result = get.execute("file.txt");
		assertEquals(expected, result);
		
	}
	
	@Test
	public void testGetURLHtml() {
		setUp();
		String expected = null;
		String result = get.execute("file.html");
		assertEquals(expected, result);
		
	}
	
	@Test
	public void testGetURLInvalidHtml() {
		setUp();
		String expected = null;
		String result = get.execute("file.js");
		assertEquals(expected, result);
		
	}
	
	@Test
	public void testGetURLInvalidTxt() {
		setUp();
		String expected = null;
		String result = get.execute("file.java");
		assertEquals(expected, result);
		
	}
}
