/**
 * Node.java
 * Contains the node class of the doubly circular linked list.
 * @author Daniel Zhang
 * @since December 3, 2020
 * @version 1.0
 */
class Node<T> { 
  // Declaration of next and previous nodes and the item.
  private Node<T> next;
  private Node<T> previous;
  private T item;
  
  /**
   * Node
   * A constructor to create a Node object
   * @param item The data contained in the node.
   * @param next The pointer to the next node.
   * @param previous The pointer to the previous node.
   */
  Node(T item, Node<T> next, Node<T> previous) { 
    this.item = item;
    this.next = next;
    this.previous = previous;
  }
  
  /**
   * getItem
   * Gets the data contained inside the node.
   * @return The item in the node.
   */
  public T getItem() { 
    return this.item;
  }
  
  /**
   * getNext
   * Gets the pointer for the next node.
   * @return The pointer for the next node.
   */
  public Node<T> getNext() { 
    return next;
  }
  
  /**
   * getPrevious
   * Gets the pointer for the previous node.
   * @return The pointer for the previous node.
   */
  public Node<T> getPrevious() { 
    return previous;
  }
  
  /**
   * setNext
   * Sets the pointer for the next node.
   * @param next The pointer to the next node.
   */
  public void setNext(Node<T> next) { 
    this.next = next;
  }
  
  /**
   * setPrevious
   * Sets the pointer for the previous node.
   * @param previous The pointer to the previous node.
   */
  public void setPrevious(Node<T> previous) { 
    this.previous = previous;
  }
}