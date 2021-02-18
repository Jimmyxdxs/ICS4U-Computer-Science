import java.awt.Graphics;

public class Circle extends Shape {
  private int radius;
  
  Circle(int x, int y, int radius) {
    super(x,y);
    this.radius = radius;
  }
  
  public int getRadius() {
    return radius;
  }
  
  public double getArea() {
    return Math.PI * radius * radius;
  }
  
  public double getCircumference() {
    return 2 * Math.PI * radius;
  }
  
  public String toString() {
    return "Circle[center = (" + this.getX() + ", " + this.getY() + "), radius = " + getRadius() + "]"; 
  }
  
  public void draw(Graphics g) {
    g.drawOval(this.getX(), this.getY(), getRadius(), getRadius());
    g.drawString("(" + this.getX() + ", " + this.getY() + ")", this.getX() + 10, this.getY() - 10);
  }
  
  public boolean contains(int x, int y) {
    return (getRadius() > (Math.sqrt(Math.pow((this.getX() - x), 2) + Math.pow(this.getY() - y, 2))));
  }


  public static void main(String[] args) {

    Circle circle = new Circle(10, 10, 5);
    System.out.println(circle.getRadius());
    System.out.println("The center of circle: (" + circle.getX() + ", " + circle.getY() + ")");
    System.out.println("Area: "+circle.getArea());
    System.out.println("Circumference: "+circle.getCircumference());
    System.out.println(circle.toString());
    System.out.println(circle.contains(6, 9));

  }
}