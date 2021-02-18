/**
 * Tuba.java
 * represents a tuba instrument that inherits from Brass.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
public class Tuba extends Brass {
  
  /**
   * Tuba
   * - sets the instrument name to "Tuba" when a Tuba object is created.
   */
  Tuba() {
    setInstrumentName("Tuba");
  }
  
  /**
   * play
   * - prints out the sound of the Tuba when played.
   */
  @Override
  public void play() {
    System.out.println("Bom bom bom bom");
  }
}