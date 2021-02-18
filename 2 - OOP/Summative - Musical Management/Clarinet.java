/**
 * Clarinet.java
 * represents a clarinet instrument that inherits from Woodwind.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
public class Clarinet extends Woodwind {
  
  /**
   * Clarinet
   * - sets the instrument name to "Clarinet" when a Clarinet object is created.
   * @param reed A String representing the type of reed, wood or plastic
   */
  Clarinet(String reed) {
    super(reed);
    setInstrumentName("Clarinet");
  }
  
  /**
   * play
   * - prints out the sound of the Clarinet when played.
   */
  @Override
  public void play() {
    System.out.println("Hmmm hm hm hmmmmm hm");
  }
}