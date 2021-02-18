import java.util.Scanner;
import java.util.Arrays;

public class ArraySortCopy {
  public static int[] bubbleSortArray(int[] integerArray) {
    int temporaryInteger;
    boolean continueToSort = true;

    while (continueToSort) {
      continueToSort = false;
      for (int j = 0; j < integerArray.length - 1; j++) {
        if (integerArray[j + 1] < integerArray[j]) {
          temporaryInteger = integerArray[j + 1];
          integerArray[j + 1] = integerArray[j];
          integerArray[j] = temporaryInteger;
          System.out.println(Arrays.toString(integerArray));
          continueToSort = true;
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
      