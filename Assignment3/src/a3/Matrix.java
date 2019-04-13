package a3;

import exceptions.*;
import java.util.Iterator;

/**
 * Represents a Matrix of Number type
 * @param <E> The type of the matrix.
 */
public abstract class Matrix<E extends Number> {
  private E[][] matrix;
  public final int rows;
  public final int columns;

  /**
   * Instantiates a 1x1 Matrix
   */
  public Matrix() {
    this.matrix = (E[][]) new Number[1][1];
    rows = 1;
    columns = 1;
  }

  /**
   * Instantiates a row x column Matrix
   * @param rowNum The number of rows in the Matrix 
   * @param colNum The number of columns in the Matrix
   */
  public Matrix (int rowNum, int colNum) {
    if (rowNum <= 0 || colNum <= 0) {
      throw new InvalidDimensionsException("Dimensions must be greater than 0");
    }

    this.matrix = (E[][]) new Number[rowNum][colNum];
    rows = rowNum;
    columns = colNum;
  }

  /**
   * Returns the row at given index.
   * @param index The index of the row
   * @return The row at given index
   * @throws OutOfBoundsException If the row index does not exist in the matrix
   */
  public E[] getRow(int index) {
    E[] matrixRow;
    try {
      matrixRow = matrix[index];  
    }
    catch(ArrayIndexOutOfBoundsException e) {
      throw new OutOfBoundsException("This matrix has " + rows + " rows.");
    }
    return matrixRow;
  }

  /**
   * Returns the value at the given index.
   * @param row The row index of the value
   * @param column The column index of the value
   * @return The value at index (row, column)
   * @throws OutOfBoundsException If the index does not exist in the matrix
   */
  public E getValue(int row, int column) {
    try {
      return matrix[row][column];
    }
    catch(ArrayIndexOutOfBoundsException e) {
      throw new OutOfBoundsException("This matrix has " + rows + " rows and "
          + columns + " columns.");
    }
  }

  /**
   * Sets the current value at the given index to the given value
   * @param row The row of the value
   * @param col The columns index of the value
   * @param value The value to replace at index (row, column)
   * @throws OutOfBoundsException If the index does not exist in the matrix
   */
  public void setValue(int row, int col, E value) {
    try {
      matrix[row][col] = value;
    }
    catch(ArrayIndexOutOfBoundsException e) {
      throw new OutOfBoundsException("This matrix has " + rows + " rows and "
          + columns + " columns.");
    }
  }

  /**
   * Returns a string representation of the matrix
   * @return A string representation of the matrix
   */
  public String toString() {
    //run for loop to scan all the rows in the matrix
    String matrixType = this.getClass().getSimpleName();
    String matrixStr = matrixType + " is:";
    for (int i = 0; i < matrix.length; i++) {
      Number[] matrixObj = matrix[i];
      matrixStr += "\n["; //formatting, print a new line for each matrix row
      //run a for loop to scan through the matrix row
      for (int j = 0; j < matrixObj.length; j++) {

        /*
         * formatting:
         * [value_11, value_12, ... , value_1n]
         * [value_21, value_22, ... , value_2n]
         * [...]
         * [value_n1, value_n2, ... , value_nn]
         */

        //print the similarity score rounded to 4 decimal places
        try {
          matrixStr += String.format("%.4f", matrixObj[j]);
        }
        catch (Exception exception) {
          matrixStr += matrixObj[j];
        }
        //if not at the end of the userMatrix, print a comma
        if (j != matrixObj.length - 1) matrixStr += ", ";
        //if at the end of the userMatrix, print a close bracket
        else matrixStr += ']';
      }
    }
    return matrixStr;
  }

}
