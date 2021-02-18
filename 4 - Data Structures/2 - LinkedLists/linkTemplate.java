import java.util.Arrays;
//**********A template for a simple linked list ********

class linkTemplate {
  public static void main(String[] args) { //the main method
    SimpleLinkedList<String> myList = new SimpleLinkedList<String>();   //you can use your list similar to an ArrayList
    myList.add("Ann");
    myList.add("Bob");  
    myList.add("Carl");
    myList.add("Dan");
    myList.add("Ethan");
    myList.add("Fanny");
    myList.add("George");
    System.out.println(myList.size());
    System.out.println(myList.toString());
  }
}

// ********************** Simple Linked List class in the linked list *********
class SimpleLinkedList<E> { 
  private Node<E> head;
  
  public void add(E item) { 
    Node<E> tempNode = head;
    
    if (head == null) {
      head = new Node<E>(item, null);
      return;
    }
    
    while (tempNode.getNext() != null) { 
      tempNode = tempNode.getNext();
    }
    tempNode.setNext(new Node<E>(item, null));
    return;
  }
  
  public E get(int index) {
    Node<E> tempNode = head;
    int counter = 0;
    
    for (int i = 0; i < index; i++) {
      tempNode = tempNode.getNext();
    }
    return tempNode.getItem();
  }
  
  public int indexOf(E item) { 
    Node<E> tempNode = head;
    int index = 0;
   
    while (index < size()) {
      if (tempNode.getItem().equals(item)) {
        return index;
      }
      
      tempNode = tempNode.getNext();
      index++;
    }
    return -1;
  }
  
  public void remove(int index) { 
    Node<E> tempNode = head;
    Node<E> removeNode;
    Node<E> nextNode;
    
    if (index == 0) {
      head = head.getNext();
    }

    else if (index == size() - 1) {
      removeNode = tempNode.getNext();
      
      while (removeNode.getNext() != null) {
        tempNode = tempNode.getNext();
        removeNode = removeNode.getNext();
      }      
      tempNode.setNext(null);
    }
    
    else if (index > 0) {
      for (int i = 0; i < index - 1; i++) {
        tempNode = tempNode.getNext();
      }
      removeNode = tempNode.getNext();
      nextNode = removeNode.getNext();
      tempNode.setNext(nextNode);
    }
  }
  
  public boolean remove(E item) { 
    Node<E> tempNode = head;
    Node<E> removeNode;
    Node<E> nextNode;
    
    if (head == null) {
      return false;
    }
    
    for (int i = 0; i < size(); i++) {
      if (get(i).equals(item)) {
        if (i == 0) {
          head = head.getNext();
          return true;
        }
      
        if (i == size() - 1) {
          removeNode = tempNode.getNext();
          while (removeNode.getNext() != null) {
            tempNode = tempNode.getNext();
            removeNode = removeNode.getNext();
          }      
          tempNode.setNext(null);
          return true;
        }
        
        if (i > 0) {
          for (int j = 0; j < i - 1; j++) {
            tempNode = tempNode.getNext();
          }
          removeNode = tempNode.getNext();
          nextNode = removeNode.getNext();
          tempNode.setNext(nextNode);
          return true;
        }
      }
    }
    return false;
  }
  
  public void clear() { 
    head = null;
  }
  
  public int size() { 
    Node<E> tempNode = head;
    int counter = 0;
    
    while (tempNode != null) {
      tempNode = tempNode.getNext();
      counter++;
    }
    return counter;
  }
  
  public String toString() {
    Node<E> tempNode = head;
    String result = "";
    
    while (tempNode != null) {
      result += tempNode.getItem();
      if (tempNode.getNext() != null) {
        result += ", ";
      }
      tempNode = tempNode.getNext();
    }
    return "[" + result + "]";
  }
}

// ********************** A Node in the linked list *********
class Node<T> { 
  private T item;
  private Node<T> next;
  
  public Node(T item) {
    this.item = item;
    this.next = null;
  }
  
  public Node(T item, Node<T> next) {
    this.item = item;
    this.next = next;
  }
  
  public Node<T> getNext() {
    return this.next;
  }
  
  public void setNext(Node<T> next) {
    this.next = next;
  }
  
  public T getItem() {
    return this.item;
  }
}