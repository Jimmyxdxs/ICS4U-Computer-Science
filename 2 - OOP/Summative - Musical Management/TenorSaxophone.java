/**
 * TenorSaxophone.java
 * represents a tenor saxophone instrument that inherits from Woodwind.
 * @author Daniel Zhang
 * @since October 20, 2020
 * @version 1.0
 */
public class TenorSaxophone extends Woodwind {
  
  /**
   * Piano
   * - sets the instrument name to "Tenor Saxophone" when a TenorSaxophone object is created.
   * @param reed A String representing the type of reed, wood or plastic
   */
  TenorSaxophone(String reed) {
    super(reed);
    setInstrumentName("Tenor Saxophone");
  }
  
  /**
   * play
   * - prints out the sound of the Tenor Saxophone when played.
   */
  @Override
  public void play() {
    System.out.println("waaaahh squeak wah waaah");
  }
}