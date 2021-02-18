/**
 * Main.java
 * This program displays circle information and "draws" it 
 * @author Daniel Zhang
 * @version 1.0
 * @since October 19, 2020
 */

import java.awt.Point;

public class Main {  
  public static void main(String[] args) {
    Circle circle = new Circle(new Point(10, 10), 5); // Creates new Circle object
    System.out.println("Radius: " + circle.getRadius()); // Prints out radius of the circle
    System.out.println("Center of circle: (" + circle.getCenter().x + ", " + circle.getCenter().y + ")"); // Prints out center coordinates of circle
    System.out.println("Area: " + circle.getArea()); // Prints out area of the circle
    System.out.println("Circumference: " + circle.getCircumference()); // Prints out circumference of the circle
    System.out.println(circle.toString()); // Prints out string representation of circle
    System.out.println(circle.contains(new Point(7, 7))); // Prints out a boolean to check if point is inside circle
  }
}