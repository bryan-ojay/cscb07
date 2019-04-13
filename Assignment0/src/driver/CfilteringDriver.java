package driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import a0.Cfiltering;

public class CfilteringDriver {

  /**
   * Reads user movie ratings from a text file, calculates similarity scores 
   * and prints a score matrix.
   */
  public static void main(String[] args) {
    try {

      // open file to read
      String fileName;
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the name of input file? ");
      fileName = in.nextLine();
      FileInputStream fStream = new FileInputStream(fileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(fStream));

      // Read dimensions: number of users and number of movies
      int numberOfUsers = Integer.parseInt(br.readLine());
      int numberOfMovies = Integer.parseInt(br.readLine());

      /*
       * create a new Cfiltering object that contains: a) 2d matrix
       * i.e.userMovieMatrix (#users*#movies) b) 2d matrix i.e. userUserMatrix
       * (#users*#users)
       */
      Cfiltering cfObject = new Cfiltering(numberOfUsers, numberOfMovies);

      // this is a blankline being read
      br.readLine();

      // read each line of movie ratings and populate the
      // userMovieMatrix
      String row;
      int rowNumber = 0;
      while ((row = br.readLine()) != null) {
        // allRatings is a list of all String numbers on one row
        String allRatings[] = row.split(" ");
        int columnNumber = 0;
        for (String singleRating : allRatings) {
          // make the String number into an integer
          int ratingNumber = Integer.parseInt(singleRating);
          // populate userMovieMatrix
          cfObject.populateUserMovieMatrix(rowNumber, columnNumber,
              ratingNumber);

          columnNumber++;
        }
        rowNumber++;
      }
      // close the file
      fStream.close();  

      // 1. CALCULATE THE SIMILARITY SCORE BETWEEN USERS.
      cfObject.calculateSimilarityScore();
      // 2. PRINT OUT THE userUserMatrix
      System.out.println("\n");
      cfObject.printUserUserMatrix();
      // 3. PRINT OUT THE MOST SIMILAR PAIRS OF USER AND THE MOST DISSIMILAR
      // PAIR OF USERS.
      System.out.println("\n\n");
      cfObject.findAndprintMostSimilarPairOfUsers();
      System.out.print("\n\n");
      cfObject.findAndprintMostDissimilarPairOfUsers();


    } catch (FileNotFoundException e) {
      System.err.println("Do you have the input file in the root folder "
          + "of your project?");
      System.err.print(e.getMessage());
    } catch (IOException e) {
      System.err.print(e.getMessage());
    }
  }
}