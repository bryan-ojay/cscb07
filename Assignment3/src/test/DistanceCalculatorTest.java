package test;

import org.junit.*;
import static org.junit.Assert.*;
import a3.DistanceCalculator;

public class DistanceCalculatorTest {

  @Test
  public void testDistanceCalcEmptyArrays() {
    float expected = 0;
    float result = DistanceCalculator.calculate(new Number[0], new Number[0]);
    assertEquals(expected, result, 0);
  }
   
  @Test
  public void testDistanceOneLengthArrays() {
    Integer[] array1 = {2};
    Integer[] array2 = {5};
    float expected = 3;
    float result = DistanceCalculator.calculate(array1, array2);
    assertEquals(expected, result, 0);
  }
  
  @Test
  public void testDistanceCalcHigherLengthArrays() {
    Integer[] array1 = {1,1};
    Integer[] array2 = {4,5};
    float expected = 5;
    float result = DistanceCalculator.calculate(array1, array2);
    assertEquals(expected, result, 0);
  }
  
  @Test
  public void testDistanceCalceNegativeNumberArrays() {
    Double[] array1 = {-1.5, -1.6};
    Double[] array2 = {-4.5, -5.6};
    float expected = 5;
    float result = DistanceCalculator.calculate(array1, array2);
    assertEquals(expected, result, 0);
  }
  
  @Test
  public void testDistanceCalcEqualArrays() {
    Integer[] array1 = {1,2,3,4,5};
    Integer[] array2 = {1,2,3,4,5};
    float expected = 0;
    float result = DistanceCalculator.calculate(array1, array2);
    assertEquals(expected, result, 0); 
  }
  
  @Test
  public void testDistanceCalcUnequalLengthArrays() {
    // Used to test that the exception is successfully thrown
    Integer[] array1 = {2,4,1,3};
    Integer[] array2 = {1,5,2};
    String expected = "4 != 3";
    String result = null;
    try {
      // should raise an error
      float calc = DistanceCalculator.calculate(array1, array2);
    }
    catch (Exception e) {
      result = e.getMessage();
    }
    assertEquals(expected, result);
  }
  
}
