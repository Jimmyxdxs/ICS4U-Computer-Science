import java.util.Arrays;
import java.util.Scanner;

public class CommonFriendProblem {
  public static boolean checkCommonFriendExists (int friendArray[][], int friendOne, int friendTwo) {
    for (int i = 0; i < friendArray.length; i++) {
      if ((friendArray[friendOne][i] == 1) && (friendArray[friendTwo][i] == 1)) {
        return true;
      }
    }
    return false;
  }
  
  public static void main(String[] args) {
    int[][] friendArray;
    char 
    System.out.println("Enter the number of friends: ");
    int friendNumber = in.nextInt();
    friendArray = new int [friendNumber][friendNumber];
    
    
    
    for (int i = 0; i < friendArray.length; i++) {
      System.out.println(Arrays.toString(friendArray[i]));
    }
    
    System.out.println(checkCommonFriendExists(friendArray, 1, 3));
    
  }
}