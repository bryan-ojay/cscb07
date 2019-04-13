package test;

import org.junit.*;
import static org.junit.Assert.*;
import driver.CFParser;
import a3.UserMovieMatrix;

public class CFParserTest {
  MockFileWriter writer = new MockFileWriter();

  public String concatenateContents(UserMovieMatrix matrix) {
    String contents = "";
    for (int row = 0; row < matrix.rows; row++) {
      for (int col = 0; col < matrix.columns; col++) {
        contents += matrix.getValue(row, col) + " ";
      }
    }

    return contents;
  }

  @Test
  public void testParserValidFormat() {
    String contents = "4\n5\n\n"
        + "1 1 1 1 1\n"
        + "2 2 2 2 2\n"
        + "3 3 3 3 3\n"
        + "4 4 4 4 4";
    writer.createMatrix("test.txt", contents);
    UserMovieMatrix resultMatrix;
    try {
      resultMatrix = CFParser.buildMatrix("test.txt"); 
    }
    catch(Exception exception) {
      resultMatrix = null;
    }

    String expected = "1 1 1 1 1 2 2 2 2 2 3 3 3 3 3 4 4 4 4 4 ";
    String result = concatenateContents(resultMatrix);

    assertEquals(expected, result);
  }

  @Test
  public void testParserInvalidNumOfRows() {
    String contents = "2\n5\n\n"
        + "1 2 3 4 5\n"
        + "5 4 3 2 1";
    String expected = "Invalid number of users.";
    String result = "";
    writer.createMatrix("test.txt", contents);
    try {
      CFParser.buildMatrix("test.txt"); 
    }
    catch(Exception exception) {
      result = exception.getMessage();
    }

    assertEquals(expected, result);
  }

  @Test
  public void testParserInvalidNumOfColumns() {
    String contents = "3\n10\n\n"
        + "1 2 3 4 5 5 4 3 2 1\n"
        + "1 5 2 4 3 4 2 5 1 3\n"
        + "5 4 3 2 1 1 2 3 4 5";
    String expected = "Invalid number of movies.";
    String result = "";
    writer.createMatrix("test.txt", contents);
    try {
      CFParser.buildMatrix("test.txt"); 
    }
    catch(Exception exception) {
      result = exception.getMessage();
    }

    assertEquals(expected, result);
  }

  @Test
  public void testParserNoBlankLine() {
    //the line after the first two number should be blank
    String contents = "3\n5\nA\n" 
        + "1 2 3 4 5\n"
        + "4 3 4 2 5\n"
        + "5 4 3 2 1";
    String expected = "Invalid formatting.";
    String result = "";
    writer.createMatrix("test.txt", contents);
    try {
      CFParser.buildMatrix("test.txt"); 
    }
    catch(Exception exception) {
      result = exception.getMessage();
    }

    assertEquals(expected, result);
  }
  
  @Test
  public void testParserInvalidNumOfMovies() {
    /* the number of movies in one row does not match
     * the given number at the start of the file */
    String contents = "4\n5\n\n" 
        + "1 2 3 4\n"
        + "4 3 4 2\n"
        + "5 4 3 2\n"
        + "2 4 3 1";
    String expected = "Incorrect number of movie ratings.";
    String result = "";
    writer.createMatrix("test.txt", contents);
    try {
      CFParser.buildMatrix("test.txt"); 
    }
    catch(Exception exception) {
      result = exception.getMessage();
    }

    assertEquals(expected, result);
  }
  
  @Test
  public void testParserInvalidNumOfUsers() {
    /* the total number of users in one row does not 
     * match the given number at the start of the file */
    String contents = "4\n5\n\n" 
        + "1 2 3 4 2\n"
        + "4 3 4 2 4\n"
        + "5 4 3 2 3";
    String expected = "Incorrect number of users.";
    String result = "";
    writer.createMatrix("test.txt", contents);
    try {
      CFParser.buildMatrix("test.txt"); 
    }
    catch(Exception exception) {
      result = exception.getMessage();
    }

    assertEquals(expected, result);
  }
  
  @Test
  public void testParserInvalidRating() {
    String contents = "3\n5\n\n" 
        + "1 2 3 4 2\n"
        + "4 -1 4 2 4\n"
        + "5 4 3 2 3";
    String expected = "Invalid rating: -1";
    String result = "";
    writer.createMatrix("test.txt", contents);
    try {
      CFParser.buildMatrix("test.txt"); 
    }
    catch(Exception exception) {
      result = exception.getMessage();
    }

    assertEquals(expected, result);
  }
  
  @Test
  public void testParserAnotherInvalidRating() {
    String contents = "3\n5\n\n" 
        + "1 2 3 4 2\n"
        + "4 1.0 4 2 4\n"
        + "5 4 3 2 3";
    String expected = "Invalid rating: 1.0";
    String result = "";
    writer.createMatrix("test.txt", contents);
    try {
      CFParser.buildMatrix("test.txt"); 
    }
    catch(Exception exception) {
      result = exception.getMessage();
    }

    assertEquals(expected, result);
  }
}
