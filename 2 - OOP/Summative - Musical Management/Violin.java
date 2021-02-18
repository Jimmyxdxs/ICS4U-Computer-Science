/**
 * Violin.java
 * represents a violin instrument that inherits from StringInstrument.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
public class Violin extends StringInstrument {
  
  /**
   * Violin
   * - sets the instrument name to "Violin" when a Violin object is created.
   */
  Violin() {
    setInstrumentName("Violin");
  }
  
  /**
   * play
   * - prints out the sound of the Violin when played.
   */
  @Override
  public void play() {
    System.out.println("Eeeee eee eeeee");
  }
}