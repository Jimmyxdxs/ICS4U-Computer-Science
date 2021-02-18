/**
 * Xylophone.java
 * represents a xylophone instrument that inherits from Percussion.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
public class Xylophone extends Percussion {
  
  /**
   * Xylophone
   * - sets the instrument name to "Xylophone" when a Xylophone object is created.
   */
  Xylophone() {
    setInstrumentName("Xylophone");
  }
  
  /**
   * play
   * - prints out the sound of the Xylophone when played.
   */
  @Override
  public void play() {
    System.out.println("Ding dingding ding");
  }
}