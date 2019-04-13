package test;

import org.junit.*;
import static org.junit.Assert.*;
import a3.MatrixAnalysis;
import a3.UserUserMatrix;

public class MatrixAnalysisTest {
  MatrixAnalysis analysis = MatrixAnalysis.getInstance();
  MockUserMovieMatrix mockUserMovies = new MockUserMovieMatrix(4, 3);
  MockUserUserMatrix mockUsers = new MockUserUserMatrix(4); 

  @Test
  public void testAnalysisBuildUserUserMatrixSameRatings() {
    mockUserMovies.build(0); //builds a matrix where all values are the same
    UserUserMatrix users = analysis.buildUserUserMatrix(mockUserMovies);
    String expected = "UserUserMatrix is:\n"
        + "[1.0000, 1.0000, 1.0000, 1.0000]\n"
        + "[1.0000, 1.0000, 1.0000, 1.0000]\n"
        + "[1.0000, 1.0000, 1.0000, 1.0000]\n"
        + "[1.0000, 1.0000, 1.0000, 1.0000]";

    assertEquals(expected, users.toString());
  }

  @Test
  public void testAnalysisBuildUserUserMatrixDifferentRatings() {
    //build a matrix where each value is 1 more than the last
    mockUserMovies.build(1); 
    UserUserMatrix users = analysis.buildUserUserMatrix(mockUserMovies);
    boolean equalDiag = users.getValue(0, 0) == 1f &&
        users.getValue(1, 1) == 1f &&
        users.getValue(2, 2) == 1f &&
        users.getValue(3, 3) == 1f;

    boolean equalOpposites = 
        users.getValue(0, 1).equals(users.getValue(1, 0)) &&
        users.getValue(0, 2).equals(users.getValue(2, 0)) &&
        users.getValue(0, 3).equals(users.getValue(3, 0)) &&
        users.getValue(1, 2).equals(users.getValue(2, 1)) &&
        users.getValue(1, 3).equals(users.getValue(3, 1)) &&
        users.getValue(2, 3).equals(users.getValue(3, 2));

    assertEquals(true, equalDiag && equalOpposites);
  }
  
  @Test
  public void testAnalysisFindSimilarUsersSameRatings() {
    mockUsers.build(0);
    Object[] expected = {"User1 and User2,\n"
        + "User1 and User3,\n"
        + "User1 and User4,\n"
        + "User2 and User3,\n"
        + "User2 and User4,\n"
        + "User3 and User4", 0.5f};
    Object[] result = analysis.compareUsers(mockUsers, false);
    assertArrayEquals(expected, result);    
  }

  @Test
  public void testAnalysisFindDissimilarUsersSameRatings() {
    mockUsers.build(0);
    Object[] expected = {"User1 and User2,\n"
        + "User1 and User3,\n"
        + "User1 and User4,\n"
        + "User2 and User3,\n"
        + "User2 and User4,\n"
        + "User3 and User4", 0.5f};
    Object[] result = analysis.compareUsers(mockUsers, true);
    assertArrayEquals(expected, result);    
  }
  
  @Test
  public void testAnalysisFindSimilarUsersDifferentRatings() {
    mockUsers.build(1);
    Object[] expected = {"User1 and User2", 0.5f};
    Object[] result = analysis.compareUsers(mockUsers, false);
    assertArrayEquals(expected, result);    
  }

  @Test
  public void testAnalysisFindDisimilarUsersDifferentRatings() {
    mockUsers.build(1);
    Object[] expected = {"User3 and User4", (1/7f)};
    Object[] result = analysis.compareUsers(mockUsers, true);
    assertArrayEquals(expected, result);    
  }

}
