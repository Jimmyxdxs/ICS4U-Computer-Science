import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * InstrumentDatabase.java
 * represents the instrument database, the ArrayList of Instrument objects.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
class InstrumentDatabase {
  // Instrument ArrayList of different types of Instrument objects.
  private ArrayList<Instrument> databaseArray = new ArrayList<Instrument>(); 
  
  /**
   * getArraySize
   *  - gets the current size of the database.
   * @return The current size of the database.
   */
  public int getArraySize() {
    return databaseArray.size();
  }
  
  /**
   * printInstruments
   *  - prints the instruments inventory database including all instrument index and added dates.
   */
  public void printInstruments() {
    System.out.println();
    if (databaseArray.isEmpty()) { // Checks if the database is empty and prints out a message.
      System.out.println("The instrument database is empty.");
    } else { // Iterates through the database and prints out the index, instrument name, and date added.
      for (int i = 0; i < databaseArray.size(); i++) {
        System.out.println("[" + i + "] " + databaseArray.get(i).convertToString());
      }
    }
    System.out.println();
  }
  
  /**
   * add
   *  - adds an instrument without reed type into the database.
   * @param instrumentName The name of theinstrument.
   */
  public void add(String instrumentName) {
    /* Switch statement checks whether the instrument (brass, string, and percussion only) is valid.
     * If the instrument is valid, a new instrument object is created and added to the database. */
    switch (instrumentName) {
      case "trumpet":
        databaseArray.add(new Trumpet());
        break;
      case "tuba":
        databaseArray.add(new Tuba());
        break;
      case "trombone":
        databaseArray.add(new Trombone());
        break;
      case "violin":
        databaseArray.add(new Violin());
        break;
      case "bass guitar":
        databaseArray.add(new BassGuitar());
        break;
      case "piano":
        databaseArray.add(new Piano());
        break;
      case "timpani":
        databaseArray.add(new Timpani());
        break;
      case "bongos":
        databaseArray.add(new Bongos());
        break;
      case "maracas":
        databaseArray.add(new Maracas());
        break;
      case "xylophone":
        databaseArray.add(new Xylophone());
        break;
      case "crash cymbals":
        databaseArray.add(new CrashCymbals());
        break;
      default:
        System.out.println("\n\"" + instrumentName.toUpperCase().charAt(0) + instrumentName.substring(1) + "\" does not exist.\n"); 
        return;
    }  
    System.out.println("\n" + instrumentName.toUpperCase().charAt(0) + instrumentName.substring(1) + " successfully added to the database!\n");
  }
  
  /**
   * add
   *  - adds an instruments with reed type into the database.
   * @param instrumentName The name of instrument.
   * @param reedType The type of reed: wood or plastic
   */
  public void add(String instrumentName, String reedType) {
    /* Switch statement checks whether the instrument (woodwind instrument only) is valid.
     * If the instrument is valid, a new instrument object is created and added to the database. */
    switch (instrumentName) {
      case "flute":
        databaseArray.add(new Flute(reedType));
        break;
      case "tenor saxophone":
        databaseArray.add(new TenorSaxophone(reedType));
        break;
      case "clarinet":
        databaseArray.add(new Clarinet(reedType));
        break;
      case "oboe":
        databaseArray.add(new Oboe(reedType));
        break;
      default:
        System.out.println("\n\"" + instrumentName.toUpperCase().charAt(0) + instrumentName.substring(1) + "\" does not exist.\n"); 
        return;
    }  
    System.out.println("\n" + instrumentName.toUpperCase().charAt(0) + instrumentName.substring(1) + " with a " + reedType + " reed successfully added to the database!\n");
  }
  
  /**
   * playInstrument
   *  - plays the instrument of the specific index in the database.
   * @param index The index of the instrument in the database to be played.
   */
  public void playInstrument(int index) {
    if (databaseArray.isEmpty()) {
      System.out.println("No instruments are currently in the database.\n");
    } else if ((index >= 0) &&(index < databaseArray.size())) {
      System.out.println();
      databaseArray.get(index).play();
      System.out.println();
    } else {
      System.out.println("\nThe input index is out of bounds.\n");
    }
  }
  
  /**
   * tuneInstrument
   *  - tunes the instrument of the specific index in the database.
   * @param index The index of the instrument in the database to be tuned.
   */
  public void tuneInstrument(int index) {
    if (databaseArray.isEmpty()) { // Checks if the database is empty, and prints out a message
      System.out.println("No instruments are currently in the database.\n");
    } else if ((index >= 0) && (index < databaseArray.size())) { // Checks if the index is within bounds, between 0 and the database size
      System.out.println();
      databaseArray.get(index).tune(); // Calls the tune method and tunes the instrument
      System.out.println();
     } else {
      System.out.println("\nThe input index is out of bounds.\n"); // Prints out of bounds message
    }
  }
 
  /**
   * removeInstrument
   *  - removes an instrument from the database.
   * @param index The index of the instrument in the database to be removed.
   */
  public void removeInstrument(int index) {
    if (!databaseArray.isEmpty()) {
      // Prints out message that instrument at index has been removed.
      System.out.println("\n[" + index + "] "+ databaseArray.get(index).getInstrumentName() + " successfully removed from the database!\n");
      databaseArray.remove(index); // Calls the remove method and removes the instrument from the database
    } 
  }
  
  /**
   * writeInstrumentsToFile
   *  - outputs the instrument inventory database to the text file idata.txt
   * @param database The database that stores the instruments.
   * @param file The name of the file to write the data to.
   * @throws IOException if the file cannot be written to.
   */
  public void writeInstrumentsToFile(InstrumentDatabase database, String file) throws IOException {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(file);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      
      // Outputs the instrument inventory counts
      int arraySize = database.databaseArray.size();
      objectOutputStream.writeObject(arraySize);
      
      // Outputs all instrument objects
      for (Instrument instrument: database.databaseArray) {
        objectOutputStream.writeObject(instrument);
      }
      fileOutputStream.close();
      System.out.println("\nData has been saved to the file \"idata.txt\" successfully!\n");
      
      // Catches output errors
    } catch (IOException e) {
      System.out.println("\nError: the system cannot create the file specified.\n");
    } 
  }
  
  /**
   * readInstrumentsFromFile
   *  - loads the idata.txt file and replaces the current inventory database.
   * @param database The database that stores the instruments.
   * @param file The name of the file where the data is being retrieved.
   * @throws IOException if data cannot be read from the file.
   * @throws ClassNotFoundException if a particular class cannot be found.
   */
  public void readInstrumentsFromFile(InstrumentDatabase database, String file) throws IOException, ClassNotFoundException{
    try {
      FileInputStream fileInputStream = new FileInputStream(file);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      
      // Reads instrument inventory counts
      int instrumentCount = (int) objectInputStream.readObject();
      database.databaseArray.removeAll(database.databaseArray);
      
      // Loads all instruments objects with the inventory counts
      for (int i = 0; i < instrumentCount; i++) {
        Instrument readInstrument = (Instrument) objectInputStream.readObject();
        if (readInstrument != null) {
          database.databaseArray.add(readInstrument);
        }
      }
      fileInputStream.close();
      System.out.println("\nData has been loaded from the file \"idata.txt\" sucessfully.\n");
      
      // Catches input error
    } catch (IOException e) {
      System.out.println("\nError: the system cannot find the file specified.\n");
    } catch (ClassNotFoundException e) {
      System.out.println("\nError: the system failed to load the file specified.\n");      
    }
  }
}