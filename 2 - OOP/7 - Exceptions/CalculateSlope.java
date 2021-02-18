import java.util.Scanner;
import java.awt.Point;

class CalculateSlope {
  
  public static void findSlope(Point pointOne, Point pointTwo) throws VerticalLineException {
    double denominator = pointTwo.x - pointOne.x;
    
    if (denominator == 0) {
      throw new VerticalLineException("Invalid slope! Cannot divide by zero.");
    }else{
      System.out.println("Slope: " + (pointTwo.y - pointOne.y) / (pointTwo.x - pointOne.x));
    }
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    boolean checkAgain = true;
    Point pointOne;
    Point pointTwo;
    double slope;
    int x1;
    int y1;
    int x2;
    int y2;
    
    do {
      System.out.print("Enter x1: ");
      x1 = in.nextInt();
      System.out.print("Enter y1: ");
      y1 = in.nextInt();
      System.out.print("Enter x2: ");
      x2 = in.nextInt();
      System.out.print("Enter y2: ");
      y2 = in.nextInt();
      
      pointOne = new Point(x1, y1);
      pointTwo = new Point(x2, y2);
      
      
      try {
        findSlope(pointOne, pointTwo);
        checkAgain = false;
      }catch (VerticalLineException e) {
        //e.printStackTrace();
        System.out.println("Cannot divide by zero!\n");      
      }
    } while (checkAgain);
  }
}
