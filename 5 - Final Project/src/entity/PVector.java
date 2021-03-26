package entity;

/**
 * PVector.java
 * @version 1.0
 * @author Daniel Zhang
 * @since January 20, 2021
 * Creates a mathematical particle system that is composed of many individual particles.
 */

public class PVector {

 private double x;
 private double y;
 private double z;

 /**
  * PVector
  * Constructs a new PVector object.
  * @param x The x position of the vector.
  * @param y The y position of the vector.
  * @param z The z position of the vector.
  */
 public PVector(double x, double y, double z) {
  this.x = x;
  this.y = y;
  this.z = z;
 }

 /**
  * PVector
  * Constructs a new PVector object.
  * @param x The x position of the vector.
  * @param y The y position of the vector.
  */
 public PVector(double x, double y) {
  this.x = x;
  this.y = y;
  z = 0;
 }

 /**
  * set
  * Sets the vector location.
  * @param x The x position of the vector.
  * @param y The y position of the vector.
  * @param z The z position of the vector.
  */
 public void set(double x, double y, double z) {
  this.x = x;
  this.y = y;
  this.z = z;
 }

 /**
  * set
  * Sets the vector location.
  * @param v A particle's vector.
  */
 public void set(PVector v) {
  x = v.x;
  y = v.y;
  z = v.z;
 }

 /**
  * get
  * Gets the vector location.
  * @return A vector with the given x, y and z positions.
  */
 public PVector get() {
  return new PVector(x, y, z);
 }

 /**
  * add
  * Add two vectors.
  * @param v A particle's vector.
  */
 public void add(PVector v) {
  x += v.x;
  y += v.y;
  z += v.z;
 }

 /**
  * add
  * Add two vectors.
  * @param x The x position of a vector.
  * @param y The y position of a vector.
  * @param z The z position of a vector.
  */
 public void add(double x, double y, double z) {
  this.x += x;
  this.y += y;
  this.z += z;
 }

 /**
  * add
  * Add two vectors.
  * @param v1 A particle's vector.
  * @param v2 Another particle's vector.
  * @return The resultant vector after adding the two vectors.
  */
 public static PVector add(PVector v1, PVector v2) {
  return add(v1, v2, null);
 }

 /**
  * add
  * Add two vectors and returns the vector of travel.
  * @param v1 A particle's vector.
  * @param v2 Another particle's vector.
  * @param target The targeted direction vector.
  * @return The new target direction vector after adding up.
  */
 public static PVector add(PVector v1, PVector v2, PVector target) {
  if (target == null) {
   target = new PVector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
  } else {
   target.set(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
  }
  return target;
 }

 /**
  * toString
  * Returns the vector's location with string format.
  * @return A string containing the vector's x, y and z positions.
  */
 @Override
 public String toString() {
  return "[ " + x + ", " + y + ", " + z + " ]";
 }

 /**
  * equals
  * Compares two vectors.
  * @return A boolean true, if the vectors are equal, or false, if they are not.
  */
 @Override
 public boolean equals(Object obj) {
  if (!(obj instanceof PVector)) {
   return false;
  }
  PVector p = (PVector) obj;
  return (x == p.x) && (y == p.y) && (z == p.z);
 }

 /**
  * getX
  * Returns the x value of the vector.
  * @return The x value of the vector.
  */
 public double getX() {
  return x;
 }

 /**
  * getY
  * Returnds the y value of the vector.
  * @return The y value of the vector.
  */
 public double getY() {
  return y;
 }

 /**
  * getZ
  * Returns the z value of the vector.
  * @return The z value of the vector.
  */
 public double getZ() {
  return z;
 }
}
