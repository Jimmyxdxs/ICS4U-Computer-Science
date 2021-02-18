/**
 * Main.java
 * @author Daniel Zhang
 * @version 1.0
 * @Date Sept 18, 2020
 * This program accepts an integer n and creates an mxm 2D array that counts outwards from the center starting from 1.
 */

import java.util.Scanner;

public class TwoDimensionalPrettyPattern {

  /* The main method reads the input of integer n and creates (2n-1)x(2n-1) 2D array with a specific pattern,
   * which has the same value on four sides of each square, from outside layer to center.
   */
  public static void main(String[] args) {
    // Asks the user to input integer n.
    int n;
    Scanner in = new Scanner (System.in);
    n = in.nextInt();
    
    int[][] numberArray = new int[2 * n - 1][2 * n - 1];  //Creates the 2D array to store data.
    int minimumBoundary = 0;            //Initial value of start of boundary.
    int maximumBoundary = 2 * (n - 1);  //Initial value of end of boundary.
    int value = n;                      //Initial value to fill the array.
    
    // Iterates through each square layer to fill the value from n to 1, starting from the outside layer to center.
    for (int i = 0; i < n; i++) {
      // Fills the 4 sides of the square with the current value.
      for (int j = minimumBoundary; j <= maximumBoundary; j++) {
        numberArray[minimumBoundary][j] = value;    // Fills the top row of array.
        numberArray[maximumBoundary][j] = value;    // Fills the bottom row of array
        numberArray[j][minimumBoundary] = value;    // Fills the left column of array.
        numberArray[j][maximumBoundary] = value;    // Fills the right colume of array.
      }
      minimumBoundary++;    // Finds the next start of boundary.
      maximumBoundary--;    // Finds the next end of boundary
      value--;              // Finds the next vaule to fill.
    }
    
    // Displays the 2D array in the required format.
    for (int i = 0; i < numberArray.length; i++) {
      for (int j = 0; j < numberArray[i].length; j++) {
        System.out.print(Integer.toString(numberArray[i][j]));
      }
      System.out.println();
    }   
  }
}