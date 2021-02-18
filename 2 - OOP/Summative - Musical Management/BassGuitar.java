/**
 * BassGuitar.java
 * represents a bass guitar instrument that inherits from StringInstrument.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
public class BassGuitar extends StringInstrument {  
  
  /**
   * BassGuitar
   * - sets the instrument name to "Bass Guitar" when a BassGuitar object is created.
   */
  BassGuitar() {
    setInstrumentName("Bass Guitar");
  }
  
  /**
   * play
   * - prints out the sound of the Bass Guitar when played.
   */
  @Override
  public void play() {
    System.out.println("Mmmm mm mm mmmm");
  }
}