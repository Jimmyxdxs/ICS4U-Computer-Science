import java.util.Scanner;

public class EveryOtherWordCapitalized {
  public static String uncapitalizeCapitalizeSentence (String sentence) {
    String[] sentenceArray = sentence.split(" ");
    String combinedSentence = "";
    
    for (int i = 0; i < sentenceArray.length; i++) {
      if (i % 2 == 0) {
        combinedSentence += sentenceArray[i].toLowerCase();
      }else{
        combinedSentence += sentenceArray[i].toUpperCase();
      }
      combinedSentence += " ";
    }
    return combinedSentence;
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    
    System.out.println("Enter a sentence here: ");
    String inputSentence = in.nextLine();
    
    System.out.println(uncapitalizeCapitalizeSentence(inputSentence));
    
  }
}