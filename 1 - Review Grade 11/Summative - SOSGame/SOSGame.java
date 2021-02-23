/**
 * SOSGame.java
 * @author Daniel Zhang
 * @version 1.0
 * @since October 09, 2020
 * The program is a SOS game of player vs. AI. 
 */

import java.util.Scanner;
import java.util.Arrays;

public class SOSGame {
 
  static char[][] boardArray;         // Array to store the game board
  static int boardSize;               // Size of game board
  static int rowInput;                // Row of placement player inputs
  static int columnInput;             // Column of placement player inputs
  static int playerTurn = 0;          // Player's turn (0 = player, 1 = AI)
  static int playerCounter = 0;       // Player's score
  static int aICounter = 0;           // AI's score
  static int updateBoardCounter = 0;  // Total counter of AI and player's turns
 
  /**
   * aIPlay
   * The AI uses recursion to play the game
   */
  public static void aIPlay() {
    boolean update = true; 
    int[] decisionArray = aIDecision(); // Calls aIDecision to get the next best placement
    
    // Displays the AI placement to the screen
    if (decisionArray[0] == 0) {
      System.out.println("Move "+(updateBoardCounter+1)+": AI placed \'o\' at (" + (decisionArray[2] + 1) + ", " + (decisionArray[3] + 1) + ").");
    }
    else if (decisionArray[0] == 1) {
      System.out.println("Move " + (updateBoardCounter + 1) + ": AI placed \'s\' at (" + (decisionArray[2] + 1) + ", " + (decisionArray[3] + 1) + ").");
    }
    
    if (decisionArray[0] == 0) {
      /* If AI decision returns 'o' and the power is greater than 0, then update the 
       * board and add AI's points with the number of SOSs. Otherwise, only update the board.
       */
      if (decisionArray[1] > 0) {
        checkAroundO(update, decisionArray[2], decisionArray[3]); 
      }else{
        updateArray('o', decisionArray[2], decisionArray[3]);
      }
    }
      /* If AI decision returns 's' and the power is greater than 0, then update the 
       * board and add AI's points with number of SOSs. Otherwise, only update the board.
       */
    else if (decisionArray[0] == 1) {
      if (decisionArray[1] > 0) {
        checkAroundS(update, decisionArray[2], decisionArray[3]);
      }else{
        updateArray('s', decisionArray[2], decisionArray[3]);
      }
    }else{
      return; // If AI decision returns "none", then all empty spots are filled and the game is over.
    }
      
    displayBoard();
    updateBoardCounter++;
    /* If AI decision returns a power value less than or equal to 0, this
     * means that AI cannot find any SOSs. Switch turn to the player.
     */
    if (decisionArray[1] <= 0) { 
      reverseTurn();
    }else{
      /* If AI decision returns a power greater than 0, this means AI can find at least one SOS. 
       * AI still holds the turn and continues playing.
       */
      aIPlay(); // Recursion 
    }
  }
  
  /** 
   * initiateBoardSize(int boardSize)
   * Initiates the boardArray with the board size that player inputs.
   * @param boardSize: size of the board 
   */
  public static void initiateBoardSize(int boardSize) {
    boardArray = new char [boardSize][boardSize];
  }
  
  /** 
   * fillBoardArray
   * Initiates boardArray with '*'. 
   */
  public static void fillBoardArray() {
    for (int i = 0; i < boardSize; i++) {
      Arrays.fill(boardArray[i], '*');
    }
  }
  
  /**
   * displayBoard
   * Displays boardArray, the player and the AI score
   */
  public static void displayBoard() {
    System.out.println("Your score: " + playerCounter);
    System.out.println("AI score: " + aICounter);
    System.out.println();
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        System.out.print(boardArray[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }
  
  /** 
   * updateArray(char letter, int row, int column)
   * This method updates the array with the last placement
   * @param letter: The letter 'o' or 's' 
   * @param row: The row of the letter
   * @param column: The column of the letter 
   */
  public static void updateArray(char letter, int row, int column) {
    boardArray[row][column] = letter;
  }
  
  /** 
   * checkAroundO(boolean update, int row, int column)
   * This method checks "SOS", "*OS", and "SO*" patterns around 'o' and updates the boardArray if update is true
   * @param update: A boolean to update (true) or check (false)
   * @param row: The row of the 'o' 
   * @param column: The column of the 'o'
   * @return: The power of the checked position
   */
  public static int checkAroundO(boolean update, int row, int column) {
    
    /* power is the integer number between -4 and 4, which represents the power value of the spot that 'o' is placed.
     * When AI evaluates the spot, it checks patterns in four directions by following the rules below.
     * 1. Power's default value is 0
     * 2. i) One is subtracted from power when "*OS" and "SO*" patterns are found if power is less and equal to 0. 
     *       It means a potential "SOS" will be gained by the player next turn. 
     *    ii) Add one to power when "SOS" pattern has been found in previous directions. This is because AI will keep 
     *        its turn and change potential loss of SOSs to gain of SOSs.
     * 3. Add one to power when "SOS" patterns are found. Convert any negative values to positive and add it to power.   
     * 
     * After evaluation, the power values below can be:
     * 1. Negative: The number of SOSs the other player gains next turn. The AI will try to avoid the spot.
     * 2. Zero: No SOSs are found for this spot. No gain or potential loss for the next placement by the player.
     * 3. Positive: The number of SOSs currently gained and will be gained next turn by the AI.
     * Based on the value of power, the AI will choose the spot with the greatest power value.
     */
    int power = 0; 
    
    char firstLetter;    // The first letter of the pattern 
    char thirdLetter;    // The third letter of the pattern 
    
    /* Start of checking "SOS", "*OS", "SO*" patterns in four directions around 'o'
     * Visualization:
     * 
     *        \ | /
     *        - o -
     *        / | \
     */
       
    // Check the first direction
    if ((row >= 1 && row <= boardSize - 2) && (column >= 1 && column <= boardSize - 2)) {
      firstLetter = Character.toUpperCase(boardArray[row - 1][column - 1]);
      thirdLetter = Character.toUpperCase(boardArray[row + 1][column + 1]);
      // Check pattern "SOS" 
      if ((firstLetter == 'S') && (thirdLetter == 'S')) {
        /* If "SOS" is found, turn previous negative power value to positive and add one to power.
         * This is because the AI can hold the turn for the next move.
         */
        power = Math.abs(power) + 1; 
        // If update is true, then update the array with uppercase "SOS" and new player points.
        if (update) {
          updateArray('S', row - 1, column - 1);
          updateArray('O', row, column);
          updateArray('S', row + 1, column + 1);
          updatePlayerPoints();
        }
      }
      // Check pattern "SO*"
      if ((firstLetter == 'S') && (thirdLetter == '*')) {
        // If "SOS" is already found, add one to power for this pattern because AI can choose it for the next placement.
        if (power > 0) {
          power++;
        }else{
          power--; // If no "SOS" is found, subtract one from power because player can use the placement to gain "SOS" next turn.
        }
      }
      // Check pattern "*OS"
      if ((firstLetter == '*') && (thirdLetter == 'S')) {
        // If "SOS" is already found, add one to power for this pattern because AI can choose it for the next placement.
        if (power > 0) {
          power++;
        }else{
          power--; // If no "SOS" is found, subtract one from power because player can use the placement to gain "SOS" next turn.
        }
      }
      // Check the second direction
      firstLetter = Character.toUpperCase(boardArray[row - 1][column + 1]);
      thirdLetter = Character.toUpperCase(boardArray[row + 1][column - 1]);
      if ((firstLetter == 'S') && (thirdLetter == 'S')) {
        power = Math.abs(power) + 1;
        if (update) {
          updateArray('S', row - 1, column + 1);
          updateArray('O', row, column);
          updateArray('S', row + 1, column - 1);
          updatePlayerPoints();
        }
      }
      if ((firstLetter == 'S') && (thirdLetter == '*')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
      if ((firstLetter == '*') && (thirdLetter == 'S')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
    }
    // Check the third direction
    if ((row >= 1 && row <= boardSize - 2)) {
      firstLetter = Character.toUpperCase(boardArray[row - 1][column]);
      thirdLetter = Character.toUpperCase(boardArray[row + 1][column]);
      if ((firstLetter == 'S') && (thirdLetter == 'S')) {
        power = Math.abs(power) + 1;
        if (update) {
          updateArray('S', row - 1, column);
          updateArray('O', row, column);
          updateArray('S', row + 1, column);
          updatePlayerPoints();
        }
      }
      if ((firstLetter == 'S') && (thirdLetter == '*')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
      if ((firstLetter == '*') && (thirdLetter == 'S')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
    }
    // Check the fourth direction
    if (column >= 1 && column <= boardSize - 2) {
      firstLetter = Character.toUpperCase(boardArray[row][column - 1]);
      thirdLetter = Character.toUpperCase(boardArray[row][column + 1]);
      if ((firstLetter == 'S') && (thirdLetter == 'S')) {
        power = Math.abs(power) + 1;
        if (update) {
          updateArray('S', row, column - 1);
          updateArray('O', row, column);
          updateArray('S', row, column + 1);
          updatePlayerPoints();
        }
      }
      if ((firstLetter == 'S') && (thirdLetter == '*')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
      if ((firstLetter == '*') && (thirdLetter == 'S')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
    } 
    // End of checking patterns
    return power;
  }
  /** 
   * checkAroundS(boolean update, int row, int column)
   * This method checks "SOS", "S*S", "SO*" patterns around 's'
   * @param update: A boolean to update (true) or check (false)
   * @param row: The row of the 's' 
   * @param column: The column of the 's'
   * @return: The evaluation of the checked position
   */
  public static int checkAroundS(boolean update, int row, int column) {
    
    /* power is the integer number between -8 and 8.
     * Please see comment of checkAroundO (top of method above) for explanation of power value.
     */
    int power = 0;
    
    char secondLetter;   // The second letter of the pattern "SOS"
    char thirdLetter;    // The third letter of the pattern "SOS"

    /* Start of checking "SOS", "*OS", "SO*" patterns in eight directions around 's'
     * Visualization:
     * 
     *       \  |  /
     *        \ | /
     *      - - s - -
     *        / | \
     *       /  |  \
     */
    
    // Check the first direction
    if (row >= 2) {
      secondLetter = Character.toUpperCase(boardArray[row - 1][column]);
      thirdLetter = Character.toUpperCase(boardArray[row - 2][column]);
      
      // Check pattern "SOS"
      if ((secondLetter == 'O') && (thirdLetter == 'S')) {
        /* If "SOS" found, turn previous negative power value to positive and add one to power.
         * This is because AI can hold the turn for the next move.
         */
        power = Math.abs(power) + 1;
        // If update is true, then update the array with capital SOS and add one to player points.
        if (update) {
          updateArray('S', row - 2, column);
          updateArray('O', row - 1, column);
          updateArray('S', row, column);
          updatePlayerPoints();
        }
      }        
      // Check pattern "SO*"
      if ((secondLetter == 'O') && (thirdLetter == '*')) {
        // If "SOS" is already found, add one to power for this pattern because it can be used for the next placement.
        if (power > 0) {
          power++;
        }else{
          power--; // If no "SOS" is found, subtract one from power because it could be used to gain "SOS" by the other player next turn.
        }
      }
      // Check pattern "S*S"
      if ((secondLetter == '*') && (thirdLetter == 'S')) {
        // If "SOS" is already found, add one to power for this pattern because it can be used for the next placement.
        if (power > 0) {
          power++;
        }else{
          power--; // If no "SOS" is found, subtract one from power because it could be used to gain "SOS" by the other player next turn.
        }
      }
    }
    // Check the second direction
    if ((row >= 2) && (column <= boardSize - 3)) {
      secondLetter = Character.toUpperCase(boardArray[row - 1][column + 1]);
      thirdLetter = Character.toUpperCase(boardArray[row - 2][column + 2]);
      if ((secondLetter == 'O') && (thirdLetter == 'S')) {
        power = Math.abs(power) + 1;
        if (update) {
          updateArray('S', row - 2, column + 2);
          updateArray('O', row - 1, column + 1);
          updateArray('S', row, column);
          updatePlayerPoints();
        }
      }
      if ((secondLetter == 'O') && (thirdLetter == '*')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
      if ((secondLetter == '*') && (thirdLetter == 'S')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
    }
    // Check the third direction
    if (column <= boardSize - 3) {
      secondLetter = Character.toUpperCase(boardArray[row][column + 1]);
      thirdLetter = Character.toUpperCase(boardArray[row][column + 2]); 
      if ((secondLetter == 'O') && (thirdLetter == 'S')) {
        power = Math.abs(power) + 1;
        if (update) {
          updateArray('S', row, column + 2);
          updateArray('O', row, column + 1);
          updateArray('S', row, column);
          updatePlayerPoints();
        }
      }
      if ((secondLetter == 'O') && (thirdLetter == '*')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
      if ((secondLetter == '*') && (thirdLetter == 'S')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
    }
    // Check the fourth direction
    if ((row <= boardSize - 3) && (column <= boardSize - 3)) {
      secondLetter = Character.toUpperCase(boardArray[row + 1][column + 1]);
      thirdLetter = Character.toUpperCase(boardArray[row + 2][column + 2]);                                           
      if ((secondLetter == 'O') && (thirdLetter == 'S')) {
        power = Math.abs(power) + 1;
        if (update) {
          updateArray('S', row + 2, column + 2);
          updateArray('O', row + 1, column + 1);
          updateArray('S', row, column);
          updatePlayerPoints();
        }
      }
      if ((secondLetter == 'O') && (thirdLetter == '*')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
      if ((secondLetter == '*') && (thirdLetter == 'S')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
    }
    // Check the fifth direction
    if (row <= boardSize - 3) {
      secondLetter = Character.toUpperCase(boardArray[row + 1][column]);
      thirdLetter = Character.toUpperCase(boardArray[row + 2][column]);
      if ((secondLetter == 'O') && (thirdLetter == 'S')) {
        power = Math.abs(power) + 1;
        if (update) {
          updateArray('S', row + 2, column);
          updateArray('O', row + 1, column);
          updateArray('S', row, column);
          updatePlayerPoints();
        }
      }
      if ((secondLetter == 'O') && (thirdLetter == '*')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
      if ((secondLetter == '*') && (thirdLetter == 'S')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
    }
    // Check the sixth direction
    if ((row <= boardSize - 3) && (column >= 2)) {
      secondLetter = Character.toUpperCase(boardArray[row + 1][column - 1]);
      thirdLetter = Character.toUpperCase(boardArray[row + 2][column - 2]);
      if ((secondLetter == 'O') && (thirdLetter == 'S')) {
        power = Math.abs(power) + 1;
        if (update) {
          updateArray('S', row + 2, column - 2);
          updateArray('O', row + 1, column - 1);
          updateArray('S', row, column);
          updatePlayerPoints();
        }
      }
      if ((secondLetter == 'O') && (thirdLetter == '*')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
      if ((secondLetter == '*') && (thirdLetter == 'S')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
    }
    // Check the seventh direction
    if (column >= 2) {
      secondLetter = Character.toUpperCase(boardArray[row][column - 1]);
      thirdLetter = Character.toUpperCase(boardArray[row][column - 2]);
      if ((secondLetter == 'O') && (thirdLetter == 'S')) {
        power = Math.abs(power) + 1;
        if (update) {
          updateArray('S', row, column - 2);
          updateArray('O', row, column - 1);
          updateArray('S', row, column);
          updatePlayerPoints();
        }
      }
      if ((secondLetter == 'O') && (thirdLetter == '*')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
      if ((secondLetter == '*') && (thirdLetter == 'S')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
    }
    
    // Check the eighth direction
    if ((row >= 2 && (column >= 2))) {
      secondLetter = Character.toUpperCase(boardArray[row - 1][column - 1]);
      thirdLetter = Character.toUpperCase(boardArray[row - 2][column - 2]);
      if ((secondLetter == 'O') && (thirdLetter == 'S')) {
        power = Math.abs(power) + 1;
        if (update) {
          updateArray('S', row - 2, column - 2);
          updateArray('O', row - 1, column - 1);
          updateArray('S', row, column);
          updatePlayerPoints();
        }
      }
      if ((secondLetter == 'O') && (thirdLetter == '*')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
      if ((secondLetter == '*') && (thirdLetter == 'S')) {
        if (power > 0) {
          power++;
        }else{
          power--;
        }
      }
    }
    // End of checking patterns
    return power;
  }
  
  /**
   * reverseTurn
   * Switches the turn of the player and AI.
   */
  public static void reverseTurn() {
    playerTurn = 1 - playerTurn;
  }
  
  /**
   * arrayShuffle(int[] arrayToShuffle)
   * Creates a shuffled integer array with a random order
   * @param arrayToShuffle: Holds integer values from 0 to (boardSize - 1) in order
   * @return: The shuffled array in a random order 
   */
  public static int[] arrayShuffle(int[] arrayToShuffle) {
    int temporaryInteger;
    int randomIndex; 
    
    /* Loop from the first to the last element of the array, use 
     * randomly-generated index to switch with the current element. 
     * Therefore, the original array will be shuffled to a random order. 
     */
    for (int i = boardSize - 1; i >= 0; i--) {
      randomIndex = (int) (Math.random()*(i + 1) + (boardSize - i - 1));
      temporaryInteger = arrayToShuffle[boardSize - i - 1];
      arrayToShuffle[boardSize - i - 1] = arrayToShuffle[randomIndex];
      arrayToShuffle[randomIndex] = temporaryInteger;
    }
    return arrayToShuffle;
  }
  
  /** 
   * aIDecision
   * This method evaluates the power of empty board spots and chooses the one with the greatest power value. Details of 
   * return is as below.
   * @return: An integer array. 
   *          array[0] = -1, 0, 1 which represents none, 'o', and 's' 
   *          array[1] = power
   *          array[2] = row
   *          array[3] = column
   */
  public static int[] aIDecision() {
    boolean aI = false;    
    int[] decisionArray = new int [4];
    int integerLetter = -1;
    int power = -9;
    int row = 0;
    int column = 0;
    
    /* Create two shuffled arrays so AI can start the evaluation from any position of the board randomly
     * By using this method, it can avoid AI next placement always put a specific letter on top of the board when 
     * many spots have the same power value. 
     */
    int[] randomRow = new int [boardSize];
    int[] randomColumn = new int [boardSize];
    for (int i = 0; i < boardSize; i++) {
      randomRow[i] = i;
      randomColumn[i] = i;
    }
    randomRow = arrayShuffle(randomRow);
    randomColumn = arrayShuffle(randomColumn);    
    // End of creating shuffled arrays
    
    int randomSelectOS = randomRow[0] % 2; // When 's' and 'o' have the same power value on the next placement, randomly select 'o' or 's' 
    
    // Scans the whole board and finds an empty spot with the greatest power value with the letter 'o' or 's'
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (boardArray[randomRow[i]][randomColumn[j]] == '*') {
          if (randomSelectOS == 0) {
            if (checkAroundO(aI, randomRow[i], randomColumn[j]) > power) {
              power = checkAroundO(aI, randomRow[i], randomColumn[j]);
              integerLetter = 0;
              row = randomRow[i];
              column = randomColumn[j];
            }
            if (checkAroundS(aI, randomRow[i], randomColumn[j]) > power) {
              power = checkAroundS(aI, randomRow[i], randomColumn[j]);
              integerLetter = 1;
              row = randomRow[i];
              column = randomColumn[j];
            }
          }else{
            if (checkAroundS(aI, randomRow[i], randomColumn[j]) > power) {
              power = checkAroundS(aI, randomRow[i], randomColumn[j]);
              integerLetter = 1;
              row = randomRow[i];
              column = randomColumn[j];
            }
            if (checkAroundO(aI, randomRow[i], randomColumn[j]) > power) {
              power = checkAroundO(aI, randomRow[i], randomColumn[j]);
              integerLetter = 0;
              row = randomRow[i];
              column = randomColumn[j];
            }
          }
        }
      }
    } 
    // Returns integer array that tells AI the placement information
    decisionArray[0] = integerLetter; // -1 = none, 0 = 'o', 1 = 's'
    decisionArray[1] = power;         // power value of the placement
    decisionArray[2] = row;           // Row of the placement
    decisionArray[3] = column;        // Column of the placement
    
    return decisionArray;
  }
  
  /**
   * updatePlayerPoints
   * Updates player points by adding one when finding a SOS.
   */
  public static void updatePlayerPoints() {
    if (playerTurn == 0) {
      playerCounter++; // Update player's points with number of SOSs
    }else{
      aICounter++;     // Update AI's points with number of SOSs 
    }
  }
  
  /**
   * gameOver
   * Checks if the game is over
   * @return: A boolean that represents if the game is over (true) or when it is not (false).
   */
  public static boolean gameOver() {
    if (updateBoardCounter < (boardSize * boardSize)) {
      return false;
    }else{ // The game is over when the entire board is filled.
      return true;
    }
  }
  
  /**
   * getUserInput
   * Gets user input of letter ('o' or 's'), row, and column and validates the user input
   */
  public static char getUserInput() {
    Scanner in = new Scanner (System.in);
    String letterInput = "";     // Player inputs 'o' or 's'
    char letterOfInput;          // Converts player input to char
    
    // Get character input and validate it to be 's' or 'o'
    do {
      System.out.print("Enter a letter (s or o): ");
      letterInput = in.nextLine().toLowerCase();
    } while (!(letterInput.equals("s")) && !(letterInput.equals("o")));
    letterOfInput = letterInput.charAt(0);
    
    
    do {
      // Validates if the row input is between 0 and (boardSize - 1).
      do {
        System.out.print("Enter a row: ");
        // Validates if the input is an integer.
        try {
          rowInput = in.nextInt() - 1;
        } catch (Exception e) {
          in.nextLine();
        }
      } while ((rowInput < 0 || rowInput > boardSize - 1));
      
      // Validates if the column input is between 0 and (boardSize - 1).
      do {
        System.out.print("Enter a column: ");
        // Validates if the input is an integer.
        try {
          columnInput = in.nextInt() - 1;
        } catch (Exception e) {
          in.nextLine();
        }
      } while ((columnInput < 0) || (columnInput > boardSize - 1)); // Row and column cannot exceed the borders.
    } while (boardArray[rowInput][columnInput] != '*'); // Input must be for an empty spot, not an occupied one with an 's' or 'o' in it.
    
    System.out.println("Move " + (updateBoardCounter + 1) + ": You placed \'" + Character.toLowerCase(letterOfInput) + "\' at (" + (rowInput + 1) + ", " + (columnInput + 1) + ").");
    
    return letterOfInput;
  }
  
  /*
   * playerPlay
   * Get player's input, 'o' or 's', and the position. If player gets SOSs, add points accordingly.
   * Player continues playing until no more SOSs are gained.
   */
  public static void playerPlay() {
    boolean getSOS = false;
    boolean update = true;
    boolean scan = false;
    char userInput;
    int power = 0;
    
    do {
      userInput = getUserInput();   // Gets player input.
      if (userInput == 'o') {  // Check if player placed 'o' in the spot.
        power = checkAroundO(scan, rowInput, columnInput);  // Checks the power value of placing 'o' in the current spot.
        if (power > 0) {      // If player gets SOSs, calculates points and update boardArray. 
          checkAroundO(update, rowInput, columnInput);
          updateBoardCounter++;
          displayBoard();
          getSOS = true;
        }else{
          getSOS = false;
        }
      }else{ // Check if player placed 's' in the spot.
        power = checkAroundS(scan, rowInput, columnInput); // Checks the power value of placing 's' in the current spot.
        if (power > 0) {     // If player gets SOSs calculates points and update boardArray.
          checkAroundS(update, rowInput, columnInput);  
          updateBoardCounter++;
          displayBoard();
          getSOS = true;
        }else{
          getSOS = false;
        }
      }
    } while ((getSOS) && (!gameOver()));
    
    // When no SOS is found, update boardArray, and switch player's turn.  
    if (!gameOver()) {
      updateArray(userInput, rowInput, columnInput);
      updateBoardCounter++;
      displayBoard();
      reverseTurn();
    }
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner (System.in);
    boolean continueToPlay = true;
    // Gets the input of the board size and validate it to be an integer, and greater than or equal to 3.
    do {
      System.out.print("Enter a board size greater than or equal to 3: ");
      // Validates input to be an integer
      try {
        boardSize = in.nextInt(); 
      } catch (Exception e) {
        in.nextLine();
      }
    } while (boardSize < 3);
    
    // Initiate and display the game board
    initiateBoardSize(boardSize); 
    fillBoardArray();             
    displayBoard();
    
    do {
      // Player plays
      playerPlay();
      if (gameOver()) {
        continueToPlay = false;
      }
      // AI plays
      aIPlay();
      if (gameOver()) {
        continueToPlay = false;
      }
    } while (continueToPlay);
    
    // When the game is over it prints the game result
    System.out.println("Game over.");
    if (playerCounter > aICounter) {
      System.out.println("Congratulations! You win.");
    }
    else if (playerCounter == aICounter) {
      System.out.println("Tie!");
    }else{
      System.out.println("AI wins.");
    }
  }
}