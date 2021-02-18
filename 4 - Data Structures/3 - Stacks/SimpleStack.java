import java.util.Scanner;

/**
 * Main.java
 * @author Daniel Zhang
 * @since October 17, 2020
 * @version 1.0
 */ 
class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner (System.in);
    SimpleStack<Double> stack = new SimpleStack<Double>(); // Stack that holds numbers to perform operations on. 
    
    String[] input = in.nextLine().split(" "); // Gets input as a string array
    double first, second;
    double value = 0;
    
    try {
      for (int i = 0; i < input.length; i++) { 
        switch (input[i]) { // Checks whether the element in the input array is "+", "-", "*", or "/".
          case "+":
          case "-":
          case "*":
          case "/": {
            // Pop the top two elements from the stack to perform operations on.
            second = stack.pop(); 
            first = stack.pop(); 
            
            switch (input[i]) {
              // If the element in the input array is "+", add the two numbers.
              case "+": {
                value = first + second;
                break;
              }    
              // If the element in the input array is "-", subtract the two numbers.
              case "-": {
                value = first - second;
                break;
              }
              // If the element in the input array is "*", multiply the two numbers.
              case "*": {
                value = first * second;
                break;
              }
              // If the element in the input array is "/", divide the two numbers.
              case "/": {
                value = first / second;
                break;
              }
            }
            stack.push(value); // Push the result of the operation back into the stack.
            break;
          }  
          // By default, push the numbers from the input array into the stack. 
          default: {
            stack.push(Double.parseDouble(input[i]));
          }
        }
      }
      System.out.println(value);
    } catch (Exception e) { // Catches NullPointerException when there are too many operations inputted.
      System.out.println("Cannot calculate. Invalid postfix notation entry.");
    }
  }
}

/**
 * SimpleStack.java
 * @author Daniel Zhang
 * @since October 17, 2020
 * @version 1.0
 */
class SimpleStack<E> {
  private StackNode<E> head; // Declaration of head object, top of stack
  
  /**
   * SimpleStack
   * A constructor to create a stack.
   */
  public SimpleStack() {
  }
  
  /**
   * push
   * Creates a new node on the top of the stack.
   * @param item The data inside the node.
   */
  public void push(E item) {
    StackNode<E> tempHead = head;
    
    if (head == null) { 
      head = new StackNode<E>(item, null);
      return;
    }
    
    head = new StackNode<E>(item, tempHead);
  }
  
  /**
   * pop
   * Removes the topmost node on the stack.
   * @return The item that was removed from the stack.
   */
  public E pop() {
    E item = head.getItem();
    if (head.getNext() == null) { // Empties the stack if there is one node left.
      head = null;
    }
    else {
      head = head.getNext(); // Shifts head to next node in the stack.
    }
    return item;  
  }
}

/**
 * StackNode.java
 * @author Daniel Zhang
 * @since October 17, 2020
 * @version 1.0
 */
class StackNode<T> {
  // Declaration of data and pointer in the node.
  private T item; 
  private StackNode<T> next;
  
  /**
   * StackNode
   * Creates a node with data, but with no pointers to other nodes.
   * @param item The data inside the node.
   */
  public StackNode(T item) {
    this.item = item;
    this.next = null;
  }
  
  /**
   * StackNode
   * Creates a node with data and a pointer to the next node.
   * @param item The data inside the node.
   * @param next The pointer to the next node in the stack.
   */
  public StackNode(T item, StackNode<T> next) {
    this.item = item;
    this.next = next;
  }
  
  /**
   * getNext
   * Gets the next node in the stack.
   * @return The pointer to the next node in the stack.
   */
  public StackNode<T> getNext() {
    return this.next;
  }
  
  /**
   * setNext
   * Sets and changes the data for the next node in the stack.
   * @param next The pointer to the next node in the stack
   */
  public void setNext(StackNode<T> next) {
    this.next = next;
  }
  
  /**
   * getItem
   * Gets the data of the node in the stack.
   * @return The data in the node.
   */
  public T getItem() {
    return this.item;
  }
}