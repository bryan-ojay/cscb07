package driver;

import java.io.BufferedReader;
import java.io.FileReader;
import a3.UserMovieMatrix;
import exceptions.*;

/**
 * Processes contents from a given file.
 */
public class CFParser {

  /**
   * Instantiates a new CFParser
   */
  private CFParser() {}

  /**
   * Parses a file of given name, returns a UserMovieMatrix created from the
   * file.
   * @param fileName The name of the file to be parsed.
   * @return The UserMovieMatrix built from the file under fileName
   * @throws Exception If the file or name of the file is invalid.
   */
  public static UserMovieMatrix buildMatrix(String fileName) throws Exception {
    // Create a reader for the file
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    // Initialize a UserMovieMatrix
    UserMovieMatrix matrix;
    try {
      matrix = parseFile(reader); //parse the file
    }
    catch(Exception exception) { //if an error is thrown, the file is invalid
      throw new InvalidFileException(exception.getMessage());
    }
    reader.close();
    return matrix;
  }

  /**
   * Parses the given file and returns a UserMovieMatrix based on the contents 
   * of the file.
   * @param reader The file reader used to parse the file
   * @return The UserMovieMatrix based on the contents of the file.
   * @throws Exception If the file format or contents are invalid.
   */
  private static UserMovieMatrix parseFile(BufferedReader reader) 
      throws Exception {
    /* Get the number of users and number of movies from the first 2 lines of
     * the file */
    int[] verifiedNums = verifyUserAndMovieNum(reader);
    int userNum = verifiedNums[0];
    int movieNum = verifiedNums[1];

    // read (and verify if the next line is) a blank line 
    if (!reader.readLine().equals("")) {
      throw new InvalidFileException("Invalid formatting.");
    }

    // Create the UserMovieMatrix based on the number of users and movies 
    UserMovieMatrix userMovies = new UserMovieMatrix(userNum, movieNum);

    // Traverse through the given text representation of the matrix 
    String row;
    int rowNum = 0;
    // Read the next lines of the file (until the end of the file)
    while ((row = reader.readLine()) != null) {

      // UserRatings is a list of all String numbers on one row
      String userRatings[] = row.split(" ");

      /* If the length of the user ratings is not equal to the given number
       * of movies, raise an error */ 
      if (userRatings.length != movieNum) {
        throw new InvalidFileException("Incorrect number of movie ratings.");
      }

      // Traverse through each rating for the given row
      int colNum = 0;
      for (String rating : userRatings) {
        /* Parse the number from the rating and verify that it the rating is
         * between 1 and 5 */
        //validateUserRating(rating);
        int ratingNum = validateUserRating(rating);
        // populate the UserMovieMatrix with the given values
        userMovies.setValue(rowNum, colNum, ratingNum);
        colNum++;
      }
      rowNum++;
    }

    // verify that the number of rows is equal to the given number of users
    if (rowNum < userNum) {  
      throw new InvalidFileException("Incorrect number of users.");
    }
    return userMovies;
  }

  /**
   * Verifies that the first 2 numbers in the file, representing the number of 
   * users and number of movies, respectively, are valid
   * @param reader The reader used to read the file containing the 2 numbers
   * @return The 2 numbers
   * @throws Exception If the numbers are invalid.
   */
  private static int[] verifyUserAndMovieNum(BufferedReader reader) 
      throws InvalidFileException {
    //create the two variables for the user and movie numbers
    int userNum;
    int movieNum;

    //validate the number of users
    try {
      /* Validate that the number of users are under the given
       * constraint (3 <= users <= 9) */
      userNum = Integer.parseInt(reader.readLine());
      if (!(userNum >= 3 && userNum <= 9)) {
        //throw an arbitrary exception if the constraint is not met
        throw new Exception();
      }
    }
    catch(Exception exception) {
      throw new InvalidFileException("Invalid number of users."); 
    }

    try {
      /* Validate that the number of movies are under the given
       * constraint (3 <= users <= 9) */
      movieNum = Integer.parseInt(reader.readLine());
      if (!(movieNum >= 3 && movieNum <= 9)) {
        //throw an arbitrary exception if the constraint is not met
        throw new Exception(); 
      }
    }
    catch(Exception exception) {
      throw new InvalidFileException("Invalid number of movies.");
    }

    //insert the 2 numbers into a single array
    int[] nums = {userNum, movieNum};

    return nums;
  }
  
  /**
   * Parses the given string representation of a user rating and returns the
   * user rating
   * @param rating The string representation of a user rating
   * @return The user rating in integer form
   * @throws InvalidFileException If the user rating is not an integer or
   * is not between 1 and 5 (inclusive).
   */
  private static int validateUserRating(String rating) 
      throws InvalidFileException {
    int ratingNum; //create a variable for the integer parsing
    
    try {
      ratingNum = Integer.parseInt(rating); //parse the integer
      if (ratingNum < 1 || ratingNum > 5) { //validate that 1 <= integer <= 5
        throw new Exception(); //throw an arbitrary exception if this is false
      }
    }
    catch(Exception exception) { 
      throw new InvalidFileException("Invalid rating: " + rating);
    }
    
    return ratingNum;
  }
}
