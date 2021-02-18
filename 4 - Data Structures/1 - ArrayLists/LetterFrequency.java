class LetterFrequency {
  private char letter;
  private int count;
  
  public LetterFrequency(char letter) {
    this.letter = letter;
    this.count = 0;
  }
  
  public Character getLetter() {
    return letter;
  }
  
  public Integer getCount() {
    return count;
  }
  
  public void add() {
    count++;
  }
}