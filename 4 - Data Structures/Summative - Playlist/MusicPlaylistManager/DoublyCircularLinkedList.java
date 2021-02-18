/**
 * DoublyCircularLinkedList.java
 * Contains the doubly circular linked list class.
 * @author Daniel Zhang
 * @since December 3, 2020
 * @version 1.0
 */
class DoublyCircularLinkedList<E> {
  // Declare the head and the tail of the linked list.
  Node<E> head;
  Node<E> tail;
  
  /**
   * DoublyCircularLinkedList
   * A constructor to create an empty doubly circular linked list.
   */
  DoublyCircularLinkedList() {
    head = null;
    tail = null;
  }
  
  /**
   * addToStart
   * Adds an element to the start of the linked list as the head.
   * @param item The new data to be stored in the list.
   * @return The item added to the list
   */
  public E addToStart(E item) {
    Node<E> newNode = new Node<E>(item, null, null);
    
    // Adds the new node to the empty list.
    if (head == null) {
      head = newNode;
      tail = newNode;
      head.setPrevious(tail);
      tail.setNext(head);
    } else { // Otherwise, add the new node as the head of the non-empty list.
      newNode.setNext(head);
      newNode.setPrevious(tail);
      head.setPrevious(newNode);
      tail.setNext(newNode);
      head = newNode;
    }
    return item;
  }
  
  /**
   * addToEnd
   * Adds an element to the end of the linked list as the tail.
   * @param item The new data to be stored in the list.
   * @return The item added to the list.
   */
  public E addToEnd(E item) {
    Node<E> newNode = new Node<E>(item, null, null);
    
    // Adds the node to the empty list.
    if (head == null) { 
      head = newNode;
      tail = newNode;
      head.setPrevious(tail);
      tail.setNext(head);
    } else { // Otherwise, add the new node as the tail of the non-empty list.
      newNode.setPrevious(tail);
      newNode.setNext(head);
      head.setPrevious(newNode);
      tail.setNext(newNode);
      tail = newNode;
    }
    return item;
  }
  
  /**
   * insert
   * Inserts an item into the linked list after a given index.
   * @param item The new data to be stored in the linked list.
   * @param index The index that the new item's node needs to be located after.
   * @return The item added to the list.
   */
  public E insert(E item, int index) {
    Node<E> tempNode = head;
    Node<E> newNode = new Node<E>(item, null, null);
    
    // If the chosen index is out of range, throw an IndexOutOfBounds exception.
    if (index >= size() || index < 0) {
      throw new IndexOutOfBoundsException();
    }else{
      // If the index is the last index in the list, call the addToEnd method.
      if (index == size() - 1) { 
        addToEnd(item);
      } else {
        // Adds the new node after the one that is not the tail in the list.
        for (int i = 0; i <= index; i++) {
          tempNode = tempNode.getNext();
        }
        // Adds the new node 
        newNode.setPrevious(tempNode.getPrevious());
        newNode.setNext(tempNode);
        tempNode.getPrevious().setNext(newNode);
        tempNode.setPrevious(newNode);
      }
    }
    return item;
  }
  
  /**
   * get
   * Gets an item given a certain index.
   * @param index The index of the item.
   * @return The item at the given index.
   */
  public E get(int index) {
    Node<E> tempNode = head;
    
    // If the chosen index is out of range, throw an IndexOutOfBounds exception.
    if (index >= size() || index < 0) {
      throw new IndexOutOfBoundsException();
    } else { 
      // Finds the node at the given index
      for (int i = 0; i < index; i++) {
        tempNode = tempNode.getNext();
      }
    }
    return tempNode.getItem();
  }
  
  /**
   * delete
   * Removes an item from the linked list given a certain index.
   * @param index The index of the item to be removed
   */
  public void delete(int index) {
    Node<E> tempNode = head;
    
    // If the chosen index is out of range, throw an IndexOutOfBounds exception.
    if (index >= size() || index < 0) {
      throw new IndexOutOfBoundsException();
    }else{
      // Deletes the only node in the list
      if (size() == 1) {
        head = null;
        tail = null;
      }
      // Deletes the first node in the list
      else if (index == 0) {
        head = head.getNext();
        head.setPrevious(tail);
        tail.setNext(head);
      }
      // Deletes the last node in the list
      else if (index == size() - 1) {
        tail = tail.getPrevious();
        tail.setNext(head);
        head.setPrevious(tail);
      } else {
        // Deletes the node at the given index
        // Finds the node at the index
        for (int i = 0; i < index; i++) {
          tempNode = tempNode.getNext();
        }
        // Removes the node
        tempNode.getPrevious().setNext(tempNode.getNext());
        tempNode.getNext().setPrevious(tempNode.getPrevious());
      }
    }
  }
  
  /**
   * size
   * Gets the size of the linked list.
   * @return The size of the linked list.
   */
  public int size() {
    Node<E> tempNode = head;
    int counter = 1;
    
    // If the list is empty, return 0.
    if (head == null) {
      return 0;
    } else {
      // Iterates through the list and gets the size.
      while (tempNode != tail) {
        tempNode = tempNode.getNext();
        counter++;
      }
    }
    return counter;
  }
}
