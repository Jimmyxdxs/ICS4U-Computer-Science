/**
 * Timpani.java
 * represents a timpani instrument that inherits from Percussion.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
public class Timpani extends Percussion {
  
  /**
   * Timpani
   * - sets the instrument name to "Timpani" when a Timpani object is created.
   */
  Timpani() {
    setInstrumentName("Timpani");
  }
  
  /**
   * play
   * - prints out the sound of the Timpani when played.
   */
  @Override
  public void play() {
    System.out.println("Boom boom");
  }
}