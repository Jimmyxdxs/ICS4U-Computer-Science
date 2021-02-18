/**
 * Circle.java
 * This class creates a circle that has attributes radius and Point(x, y) as its center point.
 * @author Daniel Zhang
 * @version 1.0
 * @since October 19, 2020
 */

import java.awt.Point;
import java.awt.Graphics;

public class Circle extends TwoDShape {
  private Point center; // Point object is set to the center of the circle
  private int radius;   // Radius of the circle
  
  /**
   * This constructor constructs a new circle specified with a given Point object and integer radius.
   * @param center The x and y coordinates of the center of the circle
   * @param radius The radius of the circle
   */
  Circle(Point center, int radius) {
    this.center = center;
    this.radius = radius;
  }

  /**
   * Returns a shape's center point
   * @return The circle's center point
   */
  public Point getCenter() {
    return center;
  }
  
  /**
   * Returns the circle's center Point
   * @return The radius of the circle
   */
  public int getRadius() {
    return radius;
  }
  
  /**
   * Returns the circle's area, using the formula ?r^2.
   * @return The area of the circle
   */
  public double getArea() {
    return Math.PI * radius * radius;
  }
  
  /**
   * Returns the circle's circumference, using the formula 2?r.
   * @return The circumference of the circle
   */
  public double getCircumference() {
    return 2 * Math.PI * radius;
  }
  
  /**
   * Returns a string representation of the circle.
   * @return A string with circle information, in the format "Circle[center = (x, y), radius = r]"
   */
  public String toString() {
    return "Circle[center = (" + getCenter().x + ", " + getCenter().y + "), radius = " + getRadius() + "]"; 
  }
  
  /**
   * Draws the circle, with its outline drawn and its center point labelled
   * @param g The base class for all graphic contexts
   */
  public void draw(Graphics g) {
    g.drawOval(getCenter().x - getRadius(), getCenter().y - getRadius(), getRadius() * 2, getRadius() * 2);
    g.drawString("Point(" + getCenter().x + ", " + getCenter().y + ")", getCenter().x, getCenter().y);
  }
  
  /**
   * Returns whether the given point lies inside the circle.
   * @param point The x and y coordinates of the point in question
   * @return A boolean, true or false, if the point is inside the circle or not
   */
  public boolean contains(Point point) {
    return (getRadius() > (Math.sqrt(Math.pow((getCenter().x - point.x), 2) + Math.pow(getCenter().y - point.y, 2))));
  }
}