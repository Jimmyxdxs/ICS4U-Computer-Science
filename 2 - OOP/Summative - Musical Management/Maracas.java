/**
 * Maracas.java
 * represents a maracas instrument that inherits from Percussion.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
public class Maracas extends Percussion {
  
  /**
   * Maracas
   * - sets the instrument name to "Maracas" when a Maracas object is created.
   */
  Maracas() {
    setInstrumentName("Maracas");
  }
  
  /**
   * play
   * - prints out the sound of Maracas when played.
   */
  @Override
  public void play() {
    System.out.println("Shake shake shake");
  }
}