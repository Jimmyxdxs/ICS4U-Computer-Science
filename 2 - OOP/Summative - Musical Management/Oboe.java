/**
 * Oboe.java
 * represents an oboe instrument that inherits from Woodwind.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
public class Oboe extends Woodwind {
  
  /**
   * Oboe
   * - sets the instrument name to "Oboe" when an Oboe object is created.
   * @param reed A String representing the type of reed, wood or plastic
   */
  Oboe(String reed) {
    super(reed);
    setInstrumentName("Oboe");
  }
  
  /**
   * play
   * - prints out the sound of the Oboe when played.
   */
  @Override
  public void play() {
    System.out.println("Too toooo too");
  }
}