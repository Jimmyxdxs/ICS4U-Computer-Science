abstract class Shape {
  private int x,y;   // the center of the shape
  
  Shape(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public abstract double getArea();
  public abstract double getCircumference();
  public abstract String toString();
  public abstract boolean contains(int x, int y);
  //public abstract void draw(Graphics g);
  
  public int getX(){
    return this.x;
  }
  
  public int getY(){
    return this.y;
  }
}