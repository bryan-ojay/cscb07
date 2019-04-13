package driver;

import java.io.FileNotFoundException;
import java.util.Scanner;
import a3.*;
import exceptions.InvalidFileException;

/**
 * Initiates the program for reading files containing matrices between users
 * and user ratings of movies
 */
public class CfilteringDriver {

  /**
   * Reads user movie ratings from a text file, calculates similarity scores 
   * and prints a score matrix.
   */
  public static void main(String[] args) {
    try {
      //get input from user
      System.out.println("Enter the name of the file: ");
      Scanner scan = new Scanner(System.in);
      String fileName = scan.nextLine();
      //build the userMovieMatrix from the given file
      UserMovieMatrix userMovies = CFParser.buildMatrix(fileName);
      //build the UserUserMatrix and print out the details of the file contents
      String matrixContents = CFPrinter.execute(userMovies);
      System.out.println(matrixContents);
      scan.close();

    } catch (FileNotFoundException e) {
      System.err.print("The file could not be found:\n" + e.getMessage());
    } catch (InvalidFileException e) {
      System.err.print("The given file is invalid:\n" + e.getMessage());
    } catch (Exception e) {
      System.err.print("An error has occured:\n" + e.getMessage());
    }
  }
}