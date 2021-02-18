/**
 * MemoryGame.java
 * @author Daniel Zhang
 * @version 1.0
 * @since Sept 26, 2020
 */

import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class MemoryGame {
  final static int BOARD_SIZE = 4;  // The game can run with any board size with a positive even number.
 
  static int[][] numberBoard = new int[BOARD_SIZE][BOARD_SIZE];      // Array to store the board with pairs of random numbers
  static String[][] displayBoard = new String[BOARD_SIZE][BOARD_SIZE]; ;  // Array to display the board
  static int rowInputOne;          // Row of the first guess
  static int columnInputOne;       // Column of the first guess
  static int rowInputTwo;          // Row of the second guess
  static int columnInputTwo;       // Column of the second guess
  static int matchCounter = 0;     // Counter of pairs player finds
  static int attemptCounter = 0;   // Counter of player attempts
  static String nameInput;         // Player's name input
  static Scanner in = new Scanner (System.in);
    
  // Initialize the display board with all '*'
  public static void generateDisplayBoard() {
    for (int i = 0; i < displayBoard.length; i++) {
      for (int j = 0; j < displayBoard[i].length; j++) {
        displayBoard[i][j] = "*";
      }
    }
  }
  
  // Generate the number board with pairs of random number
  public static void generateNumberBoard() {
    int counter = 1;
    int rowIndex;
    int columnIndex;
    
    while (counter <= (int) (Math.pow(BOARD_SIZE, 2)) / 2) {
      for (int i = 0; i < 2; i++) {
        do {
          rowIndex = (int) (Math.random()*BOARD_SIZE);
          columnIndex = (int) (Math.random()*BOARD_SIZE);
        } while (numberBoard[rowIndex][columnIndex] != 0);
        numberBoard[rowIndex][columnIndex] = counter;
      }
      counter++;
    }
  }
  
  // Output display board array
  public static void printDisplayBoard(int displayType) {
    // Clear screen
     try {
       if (System.getProperty("os.name").contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
       }else{
        Runtime.getRuntime().exec("clear");
       }
    } catch (IOException | InterruptedException ex) {
    }
   
    // Print display board
    System.out.println();
    for (int i = 0; i < displayBoard.length; i++) {
      for (int j = 0; j < displayBoard[i].length; j++) {
        if (((displayType != 0) && ((i == rowInputOne) && (j == columnInputOne))) || ((displayType == 2) && ((i == rowInputTwo) && (j == columnInputTwo)))){
          System.out.print(Integer.toString(numberBoard[i][j]) + " ");
        }else{
          System.out.print(displayBoard[i][j] + " ");
        }
      }
      System.out.println();
    }
    // Print current game information
    System.out.println("\nAttempts: " + attemptCounter);
    System.out.println("Matches: " + matchCounter + "\n");
  }
  
  // Check if the player's input pair of numbers match
  public static boolean checkIfMatch() { 
    if (numberBoard[rowInputOne][columnInputOne] == numberBoard[rowInputTwo][columnInputTwo]) {
      return true;
    }else{
      return false;
    }
  }
  
  // Update display board array
  public static void updateDisplayBoard() {
    // If two guessing numbers match update the display board. Otherwise no update.
    if (checkIfMatch()) {
      displayBoard[rowInputOne][columnInputOne] = Integer.toString(numberBoard[rowInputOne][columnInputOne]);
      displayBoard[rowInputTwo][columnInputTwo] = Integer.toString(numberBoard[rowInputTwo][columnInputTwo]);
      matchCounter++; // Add to match counter
    }
    attemptCounter++; // Add to attempt counter
  }

  // Get player input, which has two types of input: inputType = 1, which means the first guess input, and inputType = 2, which means second input.
  public static void getInput(int inputType) {
    boolean inputValid = true;
    int rowInput;
    int columnInput;
    
    do {
      // Prompt player to enter the row of coordinates
      System.out.print("Guess " + inputType +"\nEnter the row: ");
      rowInput = in.nextInt() - 1;
      if (inputType == 1) {
        rowInputOne = rowInput;
      }else{
        rowInputTwo = rowInput;
      }
      
      // Prompt player to enter the column of coordinates
      System.out.print("Enter the column: ");
      columnInput = in.nextInt() - 1;
      if (inputType == 1) {
        columnInputOne = columnInput;
      }else {
        columnInputTwo = columnInput;
      }
      
      /* Does a valid check on coordinates of player input. This includes:
       * 1. Any input number has to be greater than 0 and less than the board size.
       * 2. The coordinates of the second guess cannot be the same as the first guess.
       * Game keeps prompting until correct information is entered. 
       */
      if (((rowInput > BOARD_SIZE - 1) || (rowInput < 0) || (columnInput < 0) || (columnInput > BOARD_SIZE - 1)) 
         || ((inputType == 2) && ((rowInputOne == rowInputTwo) && (columnInputOne == columnInputTwo)))) {
        inputValid = false;
      }else{
        inputValid = true;
      }
    } while (!inputValid);
  }
  
  // Pause screen for player remembering numbers.
  public static void pauseScreen(int seconds) {
    if (!checkIfMatch()) {
      try {
        Thread.sleep(seconds);
      } 
      catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      printDisplayBoard(0);
    }
  }
  
  // When the game is over, output game result to a text file.
  public static void generateScoreFile(String fileName) throws IOException{
    File scoreFile = new File(fileName);
    PrintWriter outputScoreFile = new PrintWriter(scoreFile);
    outputScoreFile.println("Username: " + nameInput);
    outputScoreFile.println("Guesses: " + attemptCounter);
    outputScoreFile.close();  
  }
  
  public static void main(String[] args) throws IOException {
    generateDisplayBoard();
    generateNumberBoard();
    printDisplayBoard(0);
    
    do {
      getInput(1);                 // Gets the first guess coordinates
      printDisplayBoard(1);        // Displays the first guess to the board
      getInput(2);                 // Gets the second guess coordinates
      updateDisplayBoard();        // Updates display board if the numbers match
      printDisplayBoard(2);        // Display the first and second guesses to the board
      pauseScreen(1500);           // Pause the screen 1.5 seconds to allow player to memorize number positions
    } while (matchCounter < (int) (Math.pow(BOARD_SIZE, 2)) / 2);     // Checks if player guesses all numbers and wins
    
    in.nextLine();
    System.out.print("Congratulations! Enter your name here: ");   // Player inputs name
    nameInput = in.nextLine();  
    generateScoreFile("score.txt");    // Generates the game result file for the player
  }
}
