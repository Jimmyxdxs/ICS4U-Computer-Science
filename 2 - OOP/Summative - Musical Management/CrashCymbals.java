/**
 * CrashCymbals.java
 * represents a crash cymbals instrument that inherits from Percussion.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
public class CrashCymbals extends Percussion {
  
  /**
   * CrashCymbals
   * - sets the instrument name to "Crash Cymbals" when a CrashCymbals object is created.
   */
  CrashCymbals() {
    setInstrumentName("Crash Cymbals");
  }
  
  /**
   * play
   * - prints out the sound of Crash Cymbals when played.
   */
  @Override
  public void play() {
    System.out.println("Crash crash crash");
  }
}