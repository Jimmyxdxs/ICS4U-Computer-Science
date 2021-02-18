import java.util.Arrays;

public class BinarySearch {
  public static int[] generateArray() { 
    int[] numArray = new int[10];
    
    for (int i = 0; i < numArray.length; i++) {
      numArray[i] = i * 11;
    }
    return numArray;
  }
  
  public static int recursiveBinarySearch(int[] numArray, int left, int right, int numberToFind) {  
    int middle = left + (right - left) / 2;
    
    if (middle >= 1) {
      if (numArray[middle] > numberToFind) {
        return recursiveBinarySearch(numArray, left, middle - 1, numberToFind);
      }
      
      if (numArray[middle] < numberToFind) {
        return recursiveBinarySearch(numArray, middle + 1, right, numberToFind);
      }
      
      if (numArray[middle] == numberToFind) {
        return middle;
      }
    }
    return -1;
  }
    
  public static int iterativeBinarySearch(int numberToFind, int[] numArray) {
    int left = 0;
    int right = numArray.length - 1;
    int middle;
    
    while (left <= right) {
      middle = left + (right - left) / 2;
      
      if (numArray[middle] == numberToFind) {
        return middle;
      }
      
      else if (numArray[middle] < numberToFind) {
        left = middle + 1;
      }
      
      else if (numArray[middle] > numberToFind) {
        right = middle - 1;
      }
    } 
    return -1;
  }
    
  public static void main(String[] args) {
    int[] numArray = generateArray();
    
    int numberToFind = 77;

    System.out.println(Arrays.toString(numArray));
    System.out.println(iterativeBinarySearch(numberToFind, numArray));
    System.out.println(recursiveBinarySearch(numArray, 0, numArray.length - 1, numberToFind));
    
  }
}