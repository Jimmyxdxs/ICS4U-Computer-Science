/**
 * StringInstrument.java
 * represents a string instrument that inherits from Instrument, and is a superclass of Violin, Bass Guitar, and Piano.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
abstract class StringInstrument extends Instrument {
  private boolean tuned = false; // tuned is set to false since string instruments by default have not been tuned yet.
  
  /**
   * setTune
   * - tunes a string instrument by setting tuned to true.
   */
  void setTune() {
    tuned = true;
  }
  
  /**
   * getTune
   * - returns whether the string instrument has been tuned or not.
   * @return A boolean true if the instrument has been tuned, or false if it has not.
   */
  boolean getTune() {
    return tuned;
  }
  
  /**
   * tune
   * - tunes a string instrument.
   */
  @Override
  public void tune() {
    setTune(); // Set tuned to true, representing that the instrument has been tuned.
    System.out.println("The instrument has been tuned."); // Prints out a message saying that the instrument has been tuned.
  }
}