package test;

import a3.UserMovieMatrix;

/**
 * Represents a MockUserMovie UserMovieMatrix Object used for testing
 */
public class MockUserMovieMatrix extends UserMovieMatrix {

  public MockUserMovieMatrix() {
    super();
  }

  public MockUserMovieMatrix(int rows, int cols) {
    super(rows, cols);
  }

  public void build(int increment) {
    int num = 1;
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        setValue(row, col, num);
        num += increment;
      }
    }
  }
}
