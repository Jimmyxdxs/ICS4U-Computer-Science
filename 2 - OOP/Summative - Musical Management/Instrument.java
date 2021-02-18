import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

/**
 * Instrument.java
 * represents the instruments managed by the music department that is a superclass of Wind, StringInstrument, and Percussion.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
abstract class Instrument implements Serializable { 
  private String instrumentName; // Name of instrument
  private String addedDate; // Date instrument added to the database
  
  /**
   * tune
   * - tunes the instrument.
   */
  abstract void tune(); 
  /**
   * play
   * - plays the instrument.
   */
  abstract void play(); 
  
  /**
   * Instrument
   * - constructor of Instrument.
   */
  Instrument() {
    setDate();
  }
  
  /**
   * setDate
   * - sets addedDate of instrument with current date in the format mm/dd/yyyy.
   */
  void setDate() {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // Formats the time in month/day/year
    LocalDateTime currentDate = LocalDateTime.now(); // Gets the current date and time
    this.addedDate = dateFormat.format(currentDate); 
  }
  
  /**
   * getDate
   * - gets addedDate of the instrument.
   * @return addedDate in the format mm/dd/yyyy as a String.
   */
  String getDate() {
    return addedDate;
  }
  
  /**
   * setInstrumentName
   * - sets instrumentName.
   * @param instrumentName name of the instrument.
   */
  void setInstrumentName(String instrumentName) {
    this.instrumentName = instrumentName;
  }
  
  /**
   * getInstrumentName
   * - gets instrumentName.
   * @return The instrumentName as a String.
   */
  String getInstrumentName() {
    return instrumentName;      
  }
  
  /**
   * convertToString
   * - returns the instrument information including name and added date.
   * @return The name of the instrument and the date added to the database as a String.
   */
  public String convertToString() {
    return getInstrumentName() + ", added: " + getDate();
  }
}