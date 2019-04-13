package test;

import org.junit.*;
import static org.junit.Assert.*;
import driver.CFPrinter;
import a3.UserUserMatrix;

public class CFPrinterTest {
  MockUserMovieMatrix mockUserMovies = new MockUserMovieMatrix(4, 3);

  @Test
  public void testCFPrinterSameRatings() {
    mockUserMovies.build(0); //builds a matrix where all values are the same

    String expected = 
        "\n\nUserUserMatrix is:\n"
            + "[1.0000, 1.0000, 1.0000, 1.0000]\n"
            + "[1.0000, 1.0000, 1.0000, 1.0000]\n"
            + "[1.0000, 1.0000, 1.0000, 1.0000]\n"
            + "[1.0000, 1.0000, 1.0000, 1.0000]\n"
            + "\n\n"
            + "The most similar pairs of users from above userUserMatrix are:\n"
            + "User1 and User2,\n"
            + "User1 and User3,\n"
            + "User1 and User4,\n"
            + "User2 and User3,\n"
            + "User2 and User4,\n"
            + "User3 and User4" 
            + "\nwith similarity score of 1.0000"
            + "\n\n\n"
            + "The most dissimilar pairs of users from above userUserMatrix are:\n"
            + "User1 and User2,\n"
            + "User1 and User3,\n"
            + "User1 and User4,\n"
            + "User2 and User3,\n"
            + "User2 and User4,\n"
            + "User3 and User4" 
            + "\nwith similarity score of 1.0000";

    String result = CFPrinter.execute(mockUserMovies);
    assertEquals(expected, result);
  }

  @Test
  public void testCFPrinterDifferentRatings() {
    mockUserMovies.build(1); //builds a matrix with all different values
    String expected = "\n\nUserUserMatrix is:\n"
        + "[1.0000, 0.1614, 0.0878, 0.0603]\n"
        + "[0.1614, 1.0000, 0.1614, 0.0878]\n"
        + "[0.0878, 0.1614, 1.0000, 0.1614]\n"
        + "[0.0603, 0.0878, 0.1614, 1.0000]\n"
        + "\n\n"
        + "The most similar pairs of users from above userUserMatrix are:\n"
        + "User1 and User2,\n"
        + "User2 and User3,\n"
        + "User3 and User4\n"
        + "with similarity score of 0.1614"
        + "\n\n\n"
        + "The most dissimilar pairs of users from above userUserMatrix are:\n"
        + "User1 and User4"
        + "\nwith similarity score of 0.0603";
     String result = CFPrinter.execute(mockUserMovies);
     assertEquals(expected, result);
  }
}
