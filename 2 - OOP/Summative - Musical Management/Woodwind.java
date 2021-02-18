/**
 * Woodwind.java
 * represents a woodwind instrument that inherits from Wind, and is a superclass of Flute, TenorSaxophone, Clarinet, and Oboe.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */

abstract class Woodwind extends Wind {
  private String reed; // reed variable holds a String with reed information, wood or plastic.
  
  /**
   * Woodwind
   * - creates a new Woodwind object with a reed that is set inside the instrument.
   * @param reed A string with the type of reed in the instrument, wood or plastic
   */
  Woodwind(String reed) {
    setReed(reed);
  }
  
  /**
   * setReed
   * - sets a reed and assigns it to the private variable reed.
   * @param reed A string with the type of reed, wood or plastic.
   */
  public void setReed(String reed) {
    this.reed = reed;
  }
   
  /**
   * getReed
   * - returns the type of reed in the instrument.
   * @return wood or plastic, the type of reed used in the woodwind instrument.
   */
  public String getReed() {
    return reed;
  }
}