// **********************************************************
// Assignment0:
// UTORID: oladejib
// UT Student #: 1004112738
// Author: Bryan Oladeji
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences. In this semester
// we will select any three of your assignments from total of 5 and run it
// for plagiarism check. 
// *********************************************************
package a0;

public class Cfiltering {
  // this is a 2d matrix i.e. user*movie
  private int userMovieMatrix[][];
  // this is a 2d matrix i.e. user*movie
  private float userUserMatrix[][];

  /**
   * Default Constructor.
   */
  public Cfiltering() {
    // this is 2d matrix of size 1*1
    userMovieMatrix = new int[1][1];
    // this is 2d matrix of size 1*1
    userUserMatrix = new float[1][1];
  }


  /**
   * Constructs an object which contains two 2d matrices, one of size
   * users*movies which will store integer movie ratings and one of size
   * users*users which will store float similarity scores between pairs of
   * users.
   * 
   * @param numberOfUsers Determines size of matrix variables.
   * @param numberOfMovies Determines size of matrix variables.
   */
  public Cfiltering(int numberOfUsers, int numberOfMovies) {
    // this is 2d matrix of size numberOfUsers * numberOfMovies
    userMovieMatrix = new int[numberOfUsers][numberOfMovies];
    // this is square 2d matrix of size numberOfUsers * numberOfUsers
    userUserMatrix = new float[numberOfUsers][numberOfUsers];
  }

  /**
   * The purpose of this method is to populate the UserMovieMatrix. As input
   * parameters it takes in a rowNumber, columnNumber and a rating value. The
   * rating value is then inserted in the UserMovieMatrix at the specified
   * rowNumber and the columnNumber.
   * 
   * @param rowNumber The row number of the userMovieMatrix.
   * @param columnNumber The column number of the userMovieMatrix.
   * @param ratingValue The ratingValue to be inserted in the userMovieMatrix
   */
  public void populateUserMovieMatrix(int rowNumber, int columnNumber,
      int ratingValue) {

    userMovieMatrix[rowNumber][columnNumber] = ratingValue;
  }

  /**
   * Determines how similar each pair of users is based on their ratings. This
   * similarity value is represented with with a float value between 0 and 1,
   * where 1 is perfect/identical similarity. Stores these values in the
   * userUserMatrix.
   */
  public void calculateSimilarityScore() {
    // get number of users from matrix  
    int numberOfUsers = userMovieMatrix.length;

    // nested for loop runs in the style of an upper triangular matrix
    for (int userA = 0; userA < numberOfUsers; userA++) {
      for (int userB = userA; userB < numberOfUsers; userB++) {

        //get specific user matrices
        int[] userMatrixA = userMovieMatrix[userA];
        int[] userMatrixB = userMovieMatrix[userB];

        //calculate the distance between the two points
        float similarity = calculateDistance(userMatrixA, userMatrixB);

        /* 
         * set the value of the position in the matrix and its transpose
         * to the same similarity score
         */
        userUserMatrix[userA][userB] = 
            userUserMatrix[userB][userA] = similarity; 
      }
    }
  }

  /**
   * Helper function:
   * Calculates the Euclidean distance between two user matrices of the same
   * length.
   * 
   * @param userMatrixA The first user in the comparison
   * @param userMatrixB The second user in the comparison
   */
  private float calculateDistance(int[] userMatrixA, int[] userMatrixB){
    //create variable for similarity score
    double similarity;
    //set similarity score to 1 if matrices are equal
    if (userMatrixA == userMatrixB) similarity = 1;

    else {
      //get number of ratings for each user
      int numberOfRatings = userMatrixA.length;
      //initialize variable for distance between points
      double distance = 0;
      for (int rating = 0; rating < numberOfRatings; rating++) {
        /*
         * add (ratingA-ratingB)^2 to distance for all ratings in
         * each user matrix
         */
        distance = distance + 
            Math.pow((userMatrixA[rating] - userMatrixB[rating]), 2);
      }
      //calculate the similarity score
      similarity = 1 / (1 + Math.sqrt(distance));
    }
    return (float) similarity;
  }

  /**
   * Prints out the similarity scores of the userUserMatrix, with each row and
   * column representing each/single user and the cell position (i,j)
   * representing the similarity score between user i and user j.
   */
  public void printUserUserMatrix() {
    //run for loop to scan all the userMatrices in the userUserMatrix
    System.out.print("userUserMatrix is:");
    for (int i = 0; i < userUserMatrix.length; i++) {
      float[] userMatrix = userUserMatrix[i];
      System.out.println(); //formatting, print a new line for each matrix
      //run a for loop to scan through the user matrix
      for (int j = 0; j < userMatrix.length; j++) {

        /*
         * formatting:
         * [score_11, score_12, ... , score_1n]
         * [score_21, score_22, ... , score_2n]
         * [...]
         * [score_n1, score_n2, ... , score_nn]
         */
        
        //if at the start of the userMatrix, print an open bracket
        if (j == 0) System.out.print('[');
        //print the similarity score rounded to 4 decimal places
        System.out.print(String.format("%.4f", userMatrix[j]));
        //if not at the end of the userMatrix, print a comma
        if (j != userMatrix.length - 1) System.out.print(", ");
        //if at the end of the userMatrix, print a close bracket
        else if (j == userMatrix.length - 1) System.out.print(']');
      }
    }
  }


  /**
   * This function finds and prints the most similar pair of users in the
   * userUserMatrix.
   * 
   */
  public void findAndprintMostSimilarPairOfUsers() {
    //set default values for most similar score and user pair
    float similarScore = -1;
    String similarUsers = "";

    //run through all pairs in the matrix
    for (int userA = 0; userA < userUserMatrix.length; userA++){
      for (int userB = userA + 1; userB < userUserMatrix.length; userB++){

        /*
         * if a pair has a higher similar score than the current highest
         * replace the most similar score and user pair.
         */
        if (userUserMatrix[userA][userB] > similarScore) {
          similarScore = userUserMatrix[userA][userB];
          similarUsers = "User" + (userA + 1) + " and User" + (userB + 1);
        }

        /*
         * if a pair has the same similar score as the current highest,
         * add them as a most similar user pair.
         */
        else if (userUserMatrix[userA][userB] == similarScore) {
          similarUsers += ",\nUser" + (userA + 1) + " and User" + (userB + 1);
        }
      }
    }
    
    /*
     * print out the most similar scores (rounded to 4 decimal prints) and the
     * most similar users 
     */
    System.out.println("The most similar pairs of users from " + 
        "above userUserMatrix are:\n" + similarUsers + 
        "\nwith similarity score of " + String.format("%.4f", similarScore));
  }

  /**
   * This function finds and prints the most dissimilar pair of users in the
   * userUserMatrix.
   */
  public void findAndprintMostDissimilarPairOfUsers() {
    //set default values for least similar score and user pair
    float dissimilarScore = (float) 1.0001;
    String dissimilarUsers = "";

  //run through all pairs in the matrix
    for (int userA = 0; userA < userUserMatrix.length; userA++){
      for (int userB = userA + 1; userB < userUserMatrix.length; userB++){
        
        /*
         * if a pair has a lower similar score than the current lowest
         * replace the least similar score and user pair.
         */
        if (userUserMatrix[userA][userB] < dissimilarScore) {
          dissimilarScore = userUserMatrix[userA][userB];
          dissimilarUsers = "User" + (userA + 1) + " and User" + (userB + 1);
        }
        
        /*
         * if a pair has the same similar score as the current lowest,
         * add them as a least similar user pair.
         */
        else if (userUserMatrix[userA][userB] == dissimilarScore) {
          dissimilarUsers += ",\nUser" + (userA + 1) 
              + " and User" + (userB + 1);
        }
      }
    }
    
    /*
     * print out the least similar scores (rounded to 4 decimal prints) and the
     * least similar users 
     */
    System.out.println("The most dissimilar pairs of users from " + 
        "above userUserMatrix are:\n" + dissimilarUsers + 
        "\nwith similarity score of " + String.format("%.4f", dissimilarScore));
  }
}
