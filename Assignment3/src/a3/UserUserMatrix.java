package a3;

/**
 * Represents a Matrix of calculated similarity scores between users.
 */
public class UserUserMatrix extends Matrix<Float> {
  
  /**
   * Instantiates a 1x1 UserUserMatrix
   */
  public UserUserMatrix() {
    super();
  }
  
  /**
   * Instantiates a users x users UserUserMatrix
   * @param users The number of users
   */
  public UserUserMatrix(int users) {
    super(users, users);
  }
  
}
