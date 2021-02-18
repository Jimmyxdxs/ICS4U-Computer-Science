/**
 * Bongos.java
 * represents a bongos instrument that inherits from Percussion.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
public class Bongos extends Percussion {
  
  /**
   * Bongos
   * - sets the instrument name to "Bongos" when a Bongos object is created.
   */
  Bongos() {
    setInstrumentName("Bongos");
  }
  
  /**
   * play
    -  prints out the sound of Bongos when played.
   */
  @Override
  public void play() {
    System.out.println("Bang tick tick bang");
  }
}