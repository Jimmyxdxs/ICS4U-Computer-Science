/**
 * TwoDShape.java
 * This abstract class is the basis outline for 2D shape designs, in this case, circles.
 * @author Daniel Zhang
 * @version 1.0
 * @since October 19, 2020
 */

import java.awt.Point;

abstract class TwoDShape {
  private Point center; // Center point of shapes
  
  // Abstract methods for use in TwoDShape subclasses
  public abstract double getArea();
  public abstract double getCircumference();
  public abstract String toString();
  public abstract boolean contains(Point point);
}