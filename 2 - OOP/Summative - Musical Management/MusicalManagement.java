import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;

/**
 * MusicalManagement.java
 * contains the musical instruments management menu, and creates and manages the instrument inventory database.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
class MusicalManagement {
  
  private static InstrumentDatabase database = new InstrumentDatabase(); // Creates a new instrument inventory database.
  
  /**
   * validateInteger
   *  - validates whether an integer has been entered or not.
   * @return The integer that has been inputted, or by default "-1" if an integer is not inputted.
   */
  static int validateInteger() {
    Scanner in = new Scanner (System.in);
    int inputInteger = -1;
    try {
      inputInteger = in.nextInt();
      in.nextLine();
    } catch (InputMismatchException e) {
      System.out.println("\nInvalid entry!");
      in.nextLine();
    }
    return inputInteger;
  }
  
  /**
   * validateIndex
   *  - validates whether the index is within array bounds or not.
   * @param index The inputted index.
   * @return A boolean true, if the index is within bounds, or false, if it is not.
   */
  static boolean validateIndex(int index) {
    if ((index >= 0) && (index < database.getArraySize())) {
      return true;
    }
    return false;
  }
  
  public static void main(String[] args) throws IOException, ClassNotFoundException {
    Scanner in = new Scanner (System.in);
    int menuInput = 0;
    int indexInput;
    String instrumentInput;
    String reedType;
    
    System.out.println("Welcome to the Musical Instruments Management System!\n");
    
    // Do-while loop runs until user enters the number 8 to quit.
    do {
      // Prints out all the menu input options
      System.out.println("Instrument Database Menu");
      System.out.println("1. List all instruments");
      System.out.println("2. Add an instrument");
      System.out.println("3. Play an instrument");
      System.out.println("4. Tune an instrument");
      System.out.println("5. Remove an instrument");
      System.out.println("6. Save data to file");
      System.out.println("7. Load data from file");
      System.out.println("8. Quit");
  
      System.out.print("Enter a number: ");
      menuInput = validateInteger(); 
      
      // Main menu of different tasks
      switch (menuInput) {
        case 1: // Prints out all the instruments 
          database.printInstruments();
          break;
        case 2: // Adds instruments to database
          if (database.getArraySize() < 30) { // Checks if database has reached maximum capacity
            System.out.print("What instrument would you like to add?");
            instrumentInput = in.nextLine().toLowerCase().trim();
            
            // If instrument type is woodwind, prompt user to enter the type of reed in the instrument.
            if (instrumentInput.equals("flute") || instrumentInput.equals("tenor saxophone") || instrumentInput.equals("clarinet") || instrumentInput.equals("oboe")) {
              System.out.print("Enter the reed type (wood/plastic): ");
              reedType = in.nextLine().toLowerCase().trim();
              
              // Validates the type of reed inputted
              if ((!reedType.equals("wood")) && (!reedType.equals("plastic"))) {
                System.out.println("\nReed \"" + reedType + "\" does not exist.\n");
              } else {
                database.add(instrumentInput, reedType); // Adds woodwind instrument to database
              }
            // If instrument type is non-woodwind, add instrument to database.
            } else {
              database.add(instrumentInput);
            }
          } else {
            System.out.println("\nDatabase is full. Cannot add another instrument!\n");
          }
          break;
        case 3: // Plays instruments in database
          database.printInstruments();
          if (database.getArraySize() == 0) { // Checks if database is empty.
            break;
          }
          // Validates index input and plays instrument.
          System.out.print("Enter the index of the instrument that you want to play: ");
          indexInput = validateInteger();
          if (validateIndex(indexInput)) {
            database.playInstrument(indexInput);
          } else {
            System.out.println("\nThe input index is out of bounds.\n");
          }
          break;
        case 4: // Tunes instruments in database.
          database.printInstruments();
          if (database.getArraySize() == 0) { // Checks if database is empty.
            break;
          }
          // Validates index input and tunes instrument.
          System.out.print("Enter the index of the instrument that you want to tune: ");
          indexInput = validateInteger();
          if (validateIndex(indexInput)) {
            database.tuneInstrument(indexInput);
          } else {
            System.out.println("\nThe index is out of bounds.\n");
          }
          break;
        case 5: // Removes instruments from database.
          database.printInstruments();
          if (database.getArraySize() == 0) { // Checks if database is empty.
            break;
          }
          // Validates index input and removes instrument.
          System.out.print("Enter the index of the instrument that you want to remove: ");
          indexInput = validateInteger();
          if (validateIndex(indexInput)) {
            database.removeInstrument(indexInput);
          } else {
            System.out.println("\nThe input is out of bounds.\n");
          }
          break;
        case 6: // Writes data in database to file and saves it.
          database.writeInstrumentsToFile(database, "idata.txt");
          break;
        case 7: // Reads data from file and overwrites current data in database.
          database.readInstrumentsFromFile(database, "idata.txt");
          break;
        case 8: // Exits the program.
          break;
        default: 
          System.out.println("\nPlease enter a number between 1 and 8.\n");
      }
    } while (menuInput != 8);
    System.out.println("\nThank you for using the Musical Instruments Management System!");
    in.close();
  }
}