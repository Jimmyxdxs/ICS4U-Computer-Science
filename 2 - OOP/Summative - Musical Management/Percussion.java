/**
 * Percussion.java
 * represents a percussion instrument that inherits from Instrument, and is a superclass of Timpani, Maracas, Bongos, Xylophone, and CrashCymbals.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
abstract class Percussion extends Instrument {
  private boolean tuned = false; // tuned is set to false since percussion instruments by default have not been tuned yet.
  
  /**
   * setTune
   * - tunes a percussion instrument by setting tuned to true.
   */
  void setTune() {
    tuned = true;
  }
  
  /**
   * getTune
   * - returns whether the percussion instrument has been tuned or not.
   * @return A boolean true if the instrument has been tuned, or false if it has not.
   */
  boolean getTune() {
    return tuned;
  }
  
  /**
   * tune
   * - tunes a percussion instrument.
   */
  @Override
  public void tune() {
    setTune(); // Set tuned to true, representing that the instrument has been tuned.
    System.out.println("The instrument has been tuned."); // Prints out a message saying that the instrument has been tuned.
  }
}