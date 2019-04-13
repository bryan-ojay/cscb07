package driver;

import a3.*;

/**
 * Prints given contents from a Matrix.
 */
public class CFPrinter {

  /**
   * Instantiates a new CFPrinter
   */
  private CFPrinter() {}

  /**
   * Takes in a UserMovieMatrix; builds and prints a UserUserMatrix 
   * based off of the UserMovieMatrix, and calculates the most and 
   * least similar users in the UserUserMatrix
   * @param matrix The UserMovieMatrix
   */
  public static String execute(UserMovieMatrix matrix) {
    MatrixAnalysis analysis = MatrixAnalysis.getInstance();
    String result = "";

    /* Step 1. Build a UserUserMatrix based off the similarity scores of the
     * user ratings in the given UserMovieMatrix */
    UserUserMatrix users = analysis.buildUserUserMatrix(matrix);
    /* Step 2. Print the UserUserMatrix */
    result += "\n\n" + users + "\n\n";
    /* Step 3. Print the most and least similar users given in the 
     * UserUserMatrix */

    // Similar Users
    Object[] similarUsers = analysis.compareUsers(users, false);
    result += "\nThe most similar pairs of users from above " +
        "userUserMatrix are:\n" + similarUsers[0] + 
        "\nwith similarity score of " + 
        String.format("%.4f", similarUsers[1]) + "\n\n";


    // Dissimilar Users
    Object[] dissimilarUsers = analysis.compareUsers(users, true);
    result += "\nThe most dissimilar pairs of users from " + 
        "above userUserMatrix are:\n" + dissimilarUsers[0] + 
        "\nwith similarity score of " + 
        String.format("%.4f", dissimilarUsers[1]);
    
    return result;
  }
  
}
