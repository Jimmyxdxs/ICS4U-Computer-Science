import java.util.Scanner;
import java.util.Arrays;

public class ArraySort {
//  public static void bubbleSortArray(int[] integerArray) {
//    int currentNumber;
//    int tempInt;
//    int counter;
//    boolean continueToSort = true;
//    
//    while (continueToSort) {
//      counter = 1;
//      for (int j = 0; j < integerArray.length - 1; j++) {
//        if (integerArray[j] > integerArray[j + 1]) {
//          if (counter == integerArray.length - 1) {
//            continueToSort = false;
//          }
//          else {
//            counter++;
//          }
//        }
//        else {
//          counter = 1;
//          tempInt = integerArray[j + 1];
//          integerArray[j + 1] = integerArray[j];
//          integerArray[j] = tempInt;
//          System.out.println(Arrays.toString(integerArray));
//        }
//      }
//    }
//  }
  
    public static int[] bubbleSortArray(int[] integerArray) {
    int temporaryInteger;
    
    for (int i = integerArray.length - 1; i >= 1; i--) {
      for (int j = 0; j <= (i - 1); j++) {
        if (integerArray[j + 1] < integerArray[j]) {
          temporaryInteger = integerArray[j + 1];
          integerArray[j + 1] = integerArray[j];
          integerArray[j] = temporaryInteger;
          System.out.println(Arrays.toString(integerArray));
        }
      }
    }
    
    return integerArray; 
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner (System.in);
    int randomLength = (int) (Math.random()*10 + 1);
    int[] randomArray = new int[randomLength];
    for (int i = 0; i < randomLength; i++) {
      randomArray[i] = (int) (Math.random()*100);
    }
    System.out.println(Arrays.toString(randomArray));
    System.out.println(Arrays.toString(bubbleSortArray(randomArray)));
  }
}
      