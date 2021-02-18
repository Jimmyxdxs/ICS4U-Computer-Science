/**
 * Wind.java
 * represents a wind instrument that inherits from Instrument, and is a superclass of Brass and Woodwind.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */

abstract class Wind extends Instrument {  
  
  /**
   * tune
   * - prints out that a wind instrument cannot be tuned.
   */
  @Override
  public void tune() {
    System.out.println("This instrument cannot be tuned.");
  }
}