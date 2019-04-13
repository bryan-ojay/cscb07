package test;

import org.junit.*;
import static org.junit.Assert.*;
import a3.UserUserMatrix;

public class UserUserMatrixTest {
  UserUserMatrix matrix;

  @Before 
  public void setUp(){
    matrix = new UserUserMatrix(3);
    matrix.setValue(0, 0, 2f);
    matrix.setValue(0, 1, 3f);
    matrix.setValue(0, 2, 5f);
    matrix.setValue(1, 0, 2f);
    matrix.setValue(1, 1, 4f);
    matrix.setValue(1, 2, 3f);
    matrix.setValue(2, 0, 1f);
    matrix.setValue(2, 1, 5f);
    matrix.setValue(2, 2, 2f);
  }

  public Float[] getAllValues() {
    Float[] values = {
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
    Float[] expected = {2f, 4f, 2f};
    Float[] values = {matrix.getValue(0, 0), matrix.getValue(1, 1), 
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
    matrix.setValue(0, 0, 5f);
    matrix.setValue(1, 1, 5f);
    matrix.setValue(2, 2, 5f);
    
    Float[] expected = {5f, 3f, 5f, 2f, 5f, 3f, 1f, 5f, 5f};
    Float[] result = getAllValues();
    assertArrayEquals(expected, result);
  }
  
  @Test
  public void TestMatrixSetValueAtInvaldIndex() {
    String expected = "This matrix has 3 rows and 3 columns.";
    String result = "";
    try {
      matrix.setValue(3, 5, 3f);
    }
    catch (Exception exception) {
      result = exception.getMessage();
    }
    
    assertEquals(expected, result);
  }
  
  @Test
  public void testMatrixGetRow() {
    Number[] expected = {2f,4f,3f};
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
    String expected = "UserUserMatrix is:\n"
        + "[2.0000, 3.0000, 5.0000]\n"
        + "[2.0000, 4.0000, 3.0000]\n"
        + "[1.0000, 5.0000, 2.0000]";
    assertEquals(expected, matrix.toString());
  }


}
