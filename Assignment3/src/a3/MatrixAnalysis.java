package a3;

/**
 * Analyzes and retrieves contents from given matrices.
 */
public class MatrixAnalysis {
  public static MatrixAnalysis instance = null;

  /**
   * Instantiates an instance of MatrixAnalysis
   */
  private MatrixAnalysis() {}

  /** Creates an instance of MatrixAnalysis, if not already created, and
   * returns the instance.
   * @return The created MatrixAnalysis instance
   */
  public static MatrixAnalysis getInstance() {
    if (instance == null) {
      instance = new MatrixAnalysis();
    }
    return instance;
  }  

  /**
   * Builds a UserUserMatrix of similarity scores based on the given 
   * UserMovieMatrix.
   * @param userMovies The given UserMovieMatrix
   * @return A UserUserMatrix of similarity scores, or null if the matrix is 
   * invalid.
   */
  public UserUserMatrix buildUserUserMatrix(UserMovieMatrix userMovies) {

    UserUserMatrix users = new UserUserMatrix(userMovies.rows);

    // get number of users from matrix  
    int numberOfUsers = userMovies.rows;

    // nested for loop runs in the style of an upper triangular matrix
    for (int userA = 0; userA < numberOfUsers; userA++) {
      for (int userB = userA; userB < numberOfUsers; userB++) {

        //get specific user matrices
        Number[] matrixA = userMovies.getRow(userA);
        Number[] matrixB = userMovies.getRow(userB);

        //calculate the distance between the two points
        float distance = DistanceCalculator.calculate(matrixA, matrixB);

        /* set the value of the position in the matrix and its transpose
         * to the same similarity score */
        float similarity = (float) (1 / (1 + distance));
        users.setValue(userA, userB, similarity); 
        users.setValue(userB, userA, similarity); 
      }
    }

    return users;
  }

  /**
   * Analyzes and compares the similarity scores of users in a given 
   * UserUserMatrix.
   * @param users The given UserUserMatrix
   * @param findDissimilar Decides if the query finds similar or dissimilar
   * users
   * @return The most (dis)similar users and the highest (dis)similarity score
   * @throws Exception
   */
  public Object[] compareUsers(UserUserMatrix users, boolean findDissimilar) {
    int factor = 1;
    if (findDissimilar) {
      factor = -1;
    }
    float bestScore = (float) -1.0001 * factor;
    String bestUsers = "";

    //run through all pairs in the matrix
    for (int userA = 0; userA < users.rows; userA++){
      for (int userB = userA + 1; userB < users.rows; userB++){

        /* if a pair has a higher (dis)similar score than the current highest
         * replace the most (dis)similar score and user pair. */

        float currentScore = users.getValue(userA, userB);

        if (currentScore * factor > bestScore * factor) {
          bestScore = users.getValue(userA, userB);
          bestUsers = "User" + (userA + 1) + " and User" + (userB + 1);
        }

        /* if a pair has the same (dis)similar score as the current highest,
         * add them as a most (dis)similar user pair. */
        else if (currentScore == bestScore) {
          bestUsers += ",\nUser" + (userA + 1) + " and User" + (userB + 1);
        }
      }
    }
    Object[] result = {bestUsers, bestScore};
    return result;
  }

}
