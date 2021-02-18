import java.util.Scanner;
import java.util.Arrays;


public class PrettyPattern {
  public static void main(String[] args) {
    Scanner in = new Scanner (System.in);
    int n = in.nextInt();
    int[][] outputArray = new int [2 * n - 1][2 * n - 1];
    
    int p = n;
    int y = 2*n - 2;
    int j= 2*n -2;
    
    for (int i = 0; i < n; i++) {
      for (int x = i; x <= y; x++) {
          outputArray[i][x] = p;
          outputArray[j][x] = p;
          outputArray[x][i] = p;
          outputArray[x][j] = p;
         }
      j--;
      y--;
      p--;
       }
    for (int i=0; i< outputArray.length; i++) 
      System.out.println(Arrays.toString(outputArray[i]));
  }
}
        