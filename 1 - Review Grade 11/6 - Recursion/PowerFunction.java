import java.util.Scanner;

public class PowerFunction {
  public static int pow(int base, int power) {
    if (power == 0) {
      return 1;
    }
    
    else {
      return base * pow(base, power - 1);
    }
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner (System.in);
    System.out.println(pow(2, 3));
  }
}
    