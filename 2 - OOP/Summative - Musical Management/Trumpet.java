/**
 * Trumpet.java
 * represents a trumpet instrument that inherits from Brass.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
public class Trumpet extends Brass {
  
  /**
   * Trumpet
   * - sets the instrument name to "Trumpet" when a Trumpet object is created.
   */
  Trumpet() {
    setInstrumentName("Trumpet");
  }
  
  /**
   * play
   * - prints out the sound of the Trumpet when played.
   */
  @Override
  public void play() {
    System.out.println("Baaa ba baaaaaa");
  }
}