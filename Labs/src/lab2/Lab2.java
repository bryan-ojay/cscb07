package lab2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Lab2 {

	public static void main(String[] args) {
		
		try {

	      // open file to read
	      String fileName;
	      Scanner in = new Scanner(System.in);
	      System.out.println("Enter the name of input file? ");
	      fileName = in.nextLine();
	      FileInputStream fStream = new FileInputStream(fileName);
	      BufferedReader br = new BufferedReader(new InputStreamReader(fStream));
	      String input;
	      
	      //create new Matrix
	      double[][] Matrix = new double[4][5];
	      int row = 0;
	      //store numbers in file
	      while ((input = br.readLine()) != null) {
	    	  System.out.println(input);
	    	  String allNumbers[] = input.split(" ");
	    	  int col = 0;
	    	  for (String singleNumber : allNumbers) {
	    		  Matrix[row][col] = Integer.parseInt(singleNumber);
	    		  col++;
	    	  }
	    	  row++;
	      }
	      fStream.close();
	      //Calculate average
	      double matrixTotal = 0;
	      
	      for (int i = 0; i < Matrix.length; i++) {
	    	  for (int j = 0; j < Matrix[i].length; j++) {
	    		  matrixTotal += Matrix[i][j];
	    	  }
	      }
	      double matrixAverage = matrixTotal / 20.;
	      
	      System.out.println("The average is: " + matrixAverage);
	      
	      
		} catch (FileNotFoundException e) {
		      System.err.println("Do you have the input file in the root folder "
		              + "of your project?");
		      System.err.print(e.getMessage());
		} catch (IOException e) {
		      System.err.print(e.getMessage());
	}
	
	

	}

}