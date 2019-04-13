package test;

import a3.UserUserMatrix;

public class MockUserUserMatrix extends UserUserMatrix {
  public MockUserUserMatrix() {
    super();
  }
  
  public MockUserUserMatrix(int rows) {
    super(rows);
  }
  
  public void build(int increment) {
    int denominator = 2;
    for (int i = 0; i < rows; i++) {
      for (int j = i + 1; j < columns; j++) {
        if (i == j) {
          setValue(i, j, 1f);
        }
        else {
          setValue(i, j, 1f/denominator);
          setValue(j, i, 1f/denominator);
          denominator += increment;
        }
      }
    }
  }
}
