/**
 * Trombone.java
 * represents a trombone instrument that inherits from Brass.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
public class Trombone extends Brass {
  
  /**
   * Trombone
   * - sets the instrument name to "Trombone" when a Trombone object is created.
   */
  Trombone() {
    setInstrumentName("Trombone");
  }
  
  /**
   * play
   * - prints out the sound of the Trombone when played.
   */
  @Override
  public void play() {
    System.out.println("Wohm wohm wohhhhhhm blat");
  }
}