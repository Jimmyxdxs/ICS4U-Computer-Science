import java.util.Scanner;

/**
 * Main.java
 * @author Daniel Zhang
 * @since October 23, 2020
 * @version 1.0
 */
class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner (System.in);
    SimplePriorityQueue<String> queue = new SimplePriorityQueue<String>(); // Create a new priority queue
    String[] bidInput;
    
    int numberOfBidders = in.nextInt();
    in.nextLine();
    
    for (int i = 0; i < numberOfBidders; i++) {
      bidInput = in.nextLine().split(" "); // Get input as a string array with a name and a bid integer
      queue.enqueue(bidInput[0], Integer.parseInt(bidInput[1])); 
    }
    
    for (int i = 0; i < numberOfBidders; i++) {
      System.out.println(queue.dequeue()); // Print out the names of the bidders
    }
  }
}
 
/**
 * SimplePriorityQueue.java
 * @author Daniel Zhang
 * @since October 23, 2020
 * @version 1.0
 */
class SimplePriorityQueue<E> { 
  
  // Declare the head and the tail of the priority queue
  private QueueNode<E> head; 
  private QueueNode<E> tail;
  
  /**
   * enqueue
   * Enqueues items into the priority array, based on highest to lowest bids
   * @param item The name of the bidder
   * @param priority The amount of money, as an integer, that the person bids
   */
  public void enqueue(E item, int priority) {
    QueueNode<E> currentNode = tail;
    QueueNode<E> newNode;

    // If the queue is empty, create a node that has head and tail pointers
    if (head == null) {
      head = new QueueNode<E> (null, item, null, priority);
      tail = head;
    }  
    
    else {      
      newNode = new QueueNode<E> (null, item, null, priority); // Creates a new node to be inserted into the priority queue.
      
      // Checks if the current queue has one node inside
      if (currentNode.getPrevious() == null) {
        // If the new node priority is greater than the current node priority, add it to the head of the queue.
        if (priority > currentNode.getPriority()) {
          currentNode.setPrevious(newNode);
          newNode.setNext(currentNode);
          head = newNode;
        } else { // If the new node priority is less than the current node priority, add it to the tail of the queue.
          newNode.setPrevious(currentNode);
          currentNode.setNext(newNode);
          tail = newNode;
        }
      } else {
        // Checks if the new node priority is greater than the priority of the nodes inside the queue
        while ((priority > currentNode.getPriority()) && (currentNode.getPrevious() != null)) {
          currentNode = currentNode.getPrevious(); // Iterate through the queue from tail to head
        }

        if (currentNode == head) { // If the new node reaches the head, set the new node as the head
          if (priority > currentNode.getPriority()) { // If the new node priority is greater than the current node priority, set it as the new head.
            newNode.setNext(currentNode);
            currentNode.setPrevious(newNode);
            newNode.setPrevious(null);
            head = newNode;
          } else { // If the new node priority is less than the current node priority, set it after the head.
            currentNode.getNext().setPrevious(newNode);
            newNode.setNext(currentNode.getNext());
            newNode.setPrevious(currentNode);
            currentNode.setNext(newNode);
          }
        } else { // Set the new node's next and previous pointers.
          newNode.setNext(currentNode.getNext());
          newNode.setPrevious(currentNode);
          
          if (currentNode == tail) { // If the current node is at the tail, set it as the tail.
            tail = newNode;
          } else { // If the current node is not at the tail, set the next current node as the new node pointer.
            currentNode.getNext().setPrevious(newNode);
          }
          currentNode.setNext(newNode);
        }
      }
    }
  }
  
  /**
   * dequeue
   * Dequeues a queue from the front of the queue.
   * @return The name of the dequeued item.
   */
  public E dequeue() {
    E item = head.getItem(); // Gets the item to be removed.
    if (head.getNext() == null) { // If there is one item in the queue, set the queue to null.
      head = null;
    }
    
    else { // Shift the head to the next item in the queue.
      head = head.getNext();
    }
    return item;
  }
}

/**
 * QueueNode.java
 * @author Daniel Zhang
 * @since October 23, 2020
 * @version 1.0
 */
class QueueNode<T> { 
  // Declares the item, previous items, next items, and priority of the node
  private T item;
  private QueueNode<T> previous;
  private QueueNode<T> next;
  private int priority;
  
  /**
   * QueueNode
   * A constructor that builds a node for the priority queue.
   * @param previous A pointer to the previous nodes in the queue.
   * @param item The data, specifically the name, of the bidder in the node.
   * @param next A pointer to the next nodes in the queue.
   * @param priority The amount that the bidder bids.
   */
  public QueueNode(QueueNode<T> previous, T item, QueueNode<T> next, int priority) {
    this.previous = previous;
    this.item = item;
    this.next = next;
    this.priority = priority;
  }
  
  /**
   * getPrevious
   * Returns a pointer for the previous nodes in the queue.
   * @return A pointer for the previous nodes in the queue.
   */
  public QueueNode<T> getPrevious() {
    return this.previous;
  }
  
  /**
   * setPrevious
   * Sets a pointer for the previous nodes in the queue.
   * @param previous A pointer for the previous nodes in the queue.
   */
  public void setPrevious(QueueNode<T> previous) {
    this.previous = previous;
  }
  
  /**
   * getNext
   * Returns a pointer for the next nodes in the queue.
   * @return A pointer for the next nodes in the queue.
   */
  public QueueNode<T> getNext() {
    return this.next;
  }
  
  /**
   * setNext
   * Sets a pointer for the next nodes in the queue.
   * @param next A pointer for the next nodes in the queue.
   */
  public void setNext(QueueNode<T> next) {
    this.next = next;
  }
  
  /**
   * getPriority
   * Returns the priority, which is the amount that each bidder bids.
   * @return The priority, which is the amount that each bidder bids.
   */
  public int getPriority() {
    return this.priority;
  }
 
  /**
   * getItem
   * Returns the item, or data, that is countained in the node.
   * @return The data in the node.
   */
  public T getItem() {
    return this.item;
  }
}