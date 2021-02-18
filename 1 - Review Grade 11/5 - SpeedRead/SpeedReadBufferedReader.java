import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class SpeedReadBufferedReader {
  public static void main(String[] args) throws IOException {
    
    // BufferedReader file
    BufferedReader frankensteinReader = new BufferedReader(new FileReader("Frankenstein.txt"));
    long startTime = System.currentTimeMillis();
    
    long endTime;
    long elapsedTime;
    int lineCounter;
    String line;
   
    lineCounter = 0;
    while ((line = frankensteinReader.readLine()) != null) {
      lineCounter++;
    }
    
    frankensteinReader.close();
    endTime = System.currentTimeMillis();
    
    elapsedTime = endTime - startTime;
    System.out.println(lineCounter + " lines, " + elapsedTime + " ms");
    
    // Scanner file
    Scanner newFrankensteinScanner = new Scanner(new File("Frankenstein.txt"));
    startTime = System.currentTimeMillis();
    
    lineCounter = 0;
  
    while (newFrankensteinScanner.hasNext()) {
      newFrankensteinScanner.nextLine();
      lineCounter++;
    }
    
    newFrankensteinScanner.close();
    endTime = System.currentTimeMillis();
    
    elapsedTime = endTime - startTime;
    System.out.println(lineCounter + " lines, " + elapsedTime + " ms"); 
  }
}