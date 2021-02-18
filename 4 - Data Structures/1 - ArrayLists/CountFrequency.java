import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class CountFrequency {
  public static void main(String[] args) throws IOException {
    
    Scanner in = new Scanner (System.in);
    ArrayList<LetterFrequency> frequencyList = new ArrayList<>();
    
    for (int i = 0; i <= 25; i++) {
      LetterFrequency lf = new LetterFrequency((char) (i + 97));
      frequencyList.add(lf);
    }
    
    System.out.print("Enter the name of the file: ");
    String fileName = in.nextLine() + ".txt";
    
    try {
      BufferedReader frankensteinReader = new BufferedReader(new FileReader(fileName));
      String line;
      char letter;
      
      while ((line = frankensteinReader.readLine()) != null) {
        for (int i = 0; i < line.length(); i++) {
          letter = line.toLowerCase().charAt(i);
          
          for (int j = 0; j < frequencyList.size(); j++) {
            if (letter == frequencyList.get(j).getLetter()) {
              frequencyList.get(j).add();
            }
          }
        }
      }
      
      Collections.sort(frequencyList, new FrequencySort());
      
      for (int i = 0; i <= 25; i++) {
        System.out.println(frequencyList.get(i).getLetter() + " " + frequencyList.get(i).getCount());
      }

    } catch (FileNotFoundException e) {
      System.out.println("File of name \"" + fileName + "\" cannot be found.");
    }
  }
}