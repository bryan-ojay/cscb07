package a3;

import exceptions.UnequalArrayException;

/**
 * Calculates the distance between numbers or arrays of numbers
 */
public class DistanceCalculator {
  
  /**
   * Instantiates a DistanceCalculator
   */
  private DistanceCalculator() {}
  
  /**
   * Calculates the distance between 2 equal-length arrays of numbers.
   * @param array1
   * @param array2
   * @return The distance between the 2 arrays
   * @throws UnequalArrayException if the 2 arrays are not of equal lengths
   */
  public static float calculate(Number[] array1, Number[] array2) {
    //create variable for distance
    double distance = 0;
    
    if (array1.length != array2.length) {
      throw new UnequalArrayException(array1.length + " != " + array2.length);
    }

    else if (!array1.equals(array2)) {
      //get number of points in each array
      int numberOfPoints = array1.length;
      
      for (int point = 0; point < numberOfPoints; point++) {
        /* add (ratingA-ratingB)^2 to distance for all ratings in
         * each user matrix */
        distance = distance + Math.pow((array1[point].doubleValue() 
            - array2[point].doubleValue()), 2);
      }
    }
    return (float) Math.sqrt(distance);
  }
}
