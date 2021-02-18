/**
 * Flute.java
 * represents a flute instrument that inherits from Woodwind.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
public class Flute extends Woodwind {
  
  /**
   * Flute
   * - sets the instrument name to "Flute" when a Flute object is created.
   * @param reed A String representing the type of reed, wood or plastic.
   */
  Flute(String reed) {
    super(reed);
    setInstrumentName("Flute");
  }
  
  /**
   * play
   * - prints out the sound of the Flute when played.
   */
  @Override
  public void play() {
    System.out.println("tootle tootle too");
  }
}