package a3;

/**
 * Represents a Matrix mapping users to movie ratings.
 */
public class UserMovieMatrix extends Matrix<Integer> {
  
  /**
   * Instantiates a 1x1 UserMovieMatrix
   */
  public UserMovieMatrix() {
    super();
  }
  
  /**
   * Instantiates a users x movies UserMovieMatrix
   * @param users The number of users
   * @param movies The number of movies from each user
   */
  public UserMovieMatrix(int users, int movies) {
    super(users, movies);
  }
  
}
