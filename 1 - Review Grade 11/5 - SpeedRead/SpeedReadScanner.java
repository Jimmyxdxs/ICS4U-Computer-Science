import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class SpeedReadScanner {
  public static void main(String[] args) throws IOException {
    Scanner newFrankensteinScanner = new Scanner(new File("Frankenstein.txt"));
    long startTime = System.currentTimeMillis();
    
    long endTime;
    long elapsedTime;
    int lineCounter = 0;
  
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