/**
 * This program sums up multiple time spans to get the total time span.
 * TimeSpan.java
 * @author Daniel Zhang
 * @version 1.0
 * @since October 15, 2020
 */

class TimeSpan {
  
  /*
   * Stores the TimeSpan in hours and minutes.
   */
  private int hours;
  private int minutes;
  
  /**
   * Constructs a TimeSpan object storing the given time span of hours and minutes. 
   * @param hours The number of hours
   * @param minutes The number of minutes
   */
  TimeSpan(int hours, int minutes) {
    this.hours = hours;
    this.minutes = minutes;
  }
  
  /**
   * Returns the number of hours in the current time span.
   * @return The current number of hours
   */
  public int getHours() {
    return hours;
  }
  
  /**
   * Returns the number of minutes in the current time span.
   * @return The current number of minutes
   */
  public int getMinutes() {
    return minutes;
  }
  
  /**
   * Adds the given amount of time to the span. 
   * @param hours The number of hours added
   * @param minutes The number of minutes added
   */
  public void add(int hours, int minutes) {
    this.hours = this.hours + hours + ((this.minutes  + minutes) / 60);
    this.minutes = (this.minutes + minutes) % 60;
  }
  
  /**
   * Adds the given amount of time, stored as a TimeSpan object, to the current time span.
   * @param timeSpan The current TimeSpan object in hours and minutes
   */
  public void add(TimeSpan timeSpan) {
    add(timeSpan.getHours(), timeSpan.getMinutes());
  }
  
  /**
   * Returns the total time in the current time span as a decimal number of hours.
   * @return The current time span in decimal number of hours
   */
  public double getTotalHours() {
    return this.hours + (this.minutes / 60.0);
  }
  
  /**
   * Returns a string representation of the time span of hours and minutes.
   * @return A string formatted with the number of hours and minutes as "XXhXXm"
   */
  public String toString() {
    return this.hours + "h" + this.minutes + "m";
  }
}


class TimeSpan { 
  public static void main(String[] args) {
    // Creates 2 TimeSpan objects
    TimeSpan spanTest1 = new TimeSpan(2, 15); 
    TimeSpan spanTest2 = new TimeSpan(3, 45);

    // Run test on all the methods
    System.out.println("spanTest1: "+spanTest1.toString()+" ("+Math.round(spanTest1.getTotalHours()*100.0)/100.0+ " hours)");
    System.out.println("spanTest2: "+spanTest2.toString()+" ("+Math.round(spanTest2.getTotalHours()*100.0)/100.0+ " hours)");
    spanTest1.add(spanTest2);
    System.out.println("Add spanTest2 to spanTest1.");
    System.out.println("spanTest1: "+spanTest1.toString()+" ("+Math.round(spanTest1.getTotalHours()*100.0)/100.0+ " hours)");
   }
}