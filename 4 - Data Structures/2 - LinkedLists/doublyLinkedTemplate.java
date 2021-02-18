import java.util.Arrays;

class doublyLinkedTemplate {
  public static void main(String[] args) {
    DoublyLinkedList<String> dll = new DoublyLinkedList<String>();
    dll.add("Andy");
    dll.add("Bob");
    dll.add("Carol");
    dll.add("Daniel");
    System.out.println(dll.toString());
  }
}

class DoublyLinkedList<E> {
  private DoubleNode<E> head;
  
  public void add(E item) {
    DoubleNode<E> tempNode = head;
    
    if (head == null) {
      head = new DoubleNode<E>(null, item, null);
      return;
    }
    
    while (tempNode.getNext() != null) {
      tempNode = tempNode.getNext();
    }
    tempNode.setNext(new DoubleNode<E>(tempNode.getPrevious(), item, null));
  }
  
  public E get(int index) {
    DoubleNode<E> tempNode = head;
    int counter = 0;
    
    for (int i = 0; i < index; i++) {
      tempNode = tempNode.getNext();
    }
    return tempNode.getItem();
  }
  
  public int indexOf(E item) {
    DoubleNode<E> tempNode = head;
    int index = 0;
    
    while (index < size()) {
      if (tempNode.getItem().equals(item)) {
        return index;
      }
    }
    return -1;
  }
  
  public void remove(int index) {
  }
  
//  public void remove(E item) {
//  }
//  
//  public void clear() {
//  }
//  
  public int size() {
    DoubleNode<E> tempNode = head;
    int counter = 0;
    
    while (tempNode != null) {
      tempNode = tempNode.getNext();
      counter++;
    }
    return counter;
  }
//  
  public String toString() {
    DoubleNode<E> tempNode = head;
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

class DoubleNode<T> {
  private T item;
  private DoubleNode<T> previous;
  private DoubleNode<T> next;
  
  public DoubleNode(T item) {
    this.item = item;
    this.previous = null;
    this.next = null;
  }
  
  public DoubleNode(DoubleNode<T> previous, T item, DoubleNode<T> next) {
    this.previous = previous;
    this.item = item;
    this.next = next;
  }
  
  public DoubleNode<T> getNext() {
    return this.next;
  }
  
  public void setNext(DoubleNode<T> next) {
    this.next = next;
  }
  
  public DoubleNode<T> getPrevious() {
    return this.previous;
  }
  
  public void setPrevious(DoubleNode<T> next) {
    this.previous = previous;
  }
  
  public T getItem() {
    return item;
  }
}
