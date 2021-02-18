import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class SpeedRead {
  public static void findWordInText (Scanner newTextFile, String wordToFind) {
    
    String newLine = "";
    String newWord = "";
    int wordCounter = 0;
    int wordFindLength = wordToFind.length();
    
    while (newTextFile.hasNext()) {
      newLine = newTextFile.nextLine();
      for (int i = 0; i <= newLine.length() - wordFindLength; i++) {
        if (wordToFind.equalsIgnoreCase(newLine.substring(i, i + wordFindLength))) {
          wordCounter++;
        }
      }
    }
    System.out.println("The word \"" + wordToFind.toLowerCase() + "\" appears in Frankenstein " + wordCounter + " times.");
    newTextFile.close(); 
    
  }
  public static void main(String[] args) throws IOException {
    
    File frankensteinFile = new File ("Frankenstein.txt");
    Scanner newFrankensteinFile = new Scanner (frankensteinFile);
    
    Scanner input = new Scanner (System.in);
    System.out.print("Enter a word here:");
    String wordToFind = input.nextLine();
    
    findWordInText(newFrankensteinFile, wordToFind);
    input.close();
  }
}
    
    
    
    