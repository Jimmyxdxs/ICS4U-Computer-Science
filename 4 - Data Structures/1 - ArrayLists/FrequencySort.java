import java.util.Comparator;

class FrequencySort implements Comparator<LetterFrequency> {
  public int compare(LetterFrequency lf1, LetterFrequency lf2) {
    return lf2.getCount() - lf1.getCount();
  }
}