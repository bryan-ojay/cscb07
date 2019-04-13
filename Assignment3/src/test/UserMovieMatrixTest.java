package test;

import org.junit.*;
import static org.junit.Assert.*;
import a3.UserMovieMatrix;

public class UserMovieMatrixTest {
  UserMovieMatrix matrix;

  @Before 
  public void setUp(){
    matrix = new UserMovieMatrix(3, 3);
    matrix.setValue(0, 0, 2);
    matrix.setValue(0, 1, 3);
    matrix.setValue(0, 2, 5);
    matrix.setValue(1, 0, 2);
    matrix.setValue(1, 1, 4);
    matrix.setValue(1, 2, 3);
    matrix.setValue(2, 0, 1);
    matrix.setValue(2, 1, 5);
    matrix.setValue(2, 2, 2);
  }

  public Integer[] getAllValues() {
    Integer[] values = {
        matrix.getValue(0, 0),
        matrix.getValue(0, 1),
        matrix.getValue(0, 2),
        matrix.getValue(1, 0),
        matrix.getValue(1, 1),
        matrix.getValue(1, 2),
        matrix.getValue(2, 0),
        matrix.getValue(2, 1),
        matrix.getValue(2, 2)
    };

    return values;
  }


  @Test
  public void TestMatrixGetValueFromIndex() {
    Integer[] expected = {2, 4, 2};
    Integer[] values = {matrix.getValue(0, 0), matrix.getValue(1, 1), 
        matrix.getValue(2, 2)};

    assertArrayEquals(expected, values);
  }

  @Test
  public void TestMatrixGetValueFromInvalidIndex() {
    //should throw an error
    String expected = "This matrix has 3 rows and 3 columns.";
    String result = "";
    try {
      matrix.getValue(3, 2);
    }
    catch (Exception exception) {
      result = exception.getMessage();
    }
    assertEquals(expected, result);
  }

  @Test
  public void TestMatrixSetValues() {
    matrix.setValue(0, 0, 5);
    matrix.setValue(1, 1, 5);
    matrix.setValue(2, 2, 5);
    
    Integer[] expected = {5, 3, 5, 2, 5, 3, 1, 5, 5};
    Integer[] result = getAllValues();
    assertArrayEquals(expected, result);
  }
  
  @Test
  public void TestMatrixSetValueAtInvaldIndex() {
    String expected = "This matrix has 3 rows and 3 columns.";
    String result = "";
    try {
      matrix.setValue(3, 5, 3);
    }
    catch (Exception exception) {
      result = exception.getMessage();
    }
    
    assertEquals(expected, result);
  }
  
  @Test
  public void testMatrixGetRow() {
    Number[] expected = {2,4,3};
    Number[] result = matrix.getRow(1);
    assertArrayEquals(expected, result);
  }
  
  @Test
  public void testMatrixGetRowAtInvalidIndex() {
    String expected = "This matrix has 3 rows.";
    String result = "";
    try {
      matrix.getRow(4);
    }
    catch (Exception exception) {
      result = exception.getMessage();
    }
    
    assertEquals(expected, result);
  }
  
  @Test
  public void testMatrixtoString() {
    String expected = "UserMovieMatrix is:\n"
        + "[2, 3, 5]\n"
        + "[2, 4, 3]\n"
        + "[1, 5, 2]";
    assertEquals(expected, matrix.toString());
  }


}
