//BINARY TREE TEMPLATE
//Complete the methods as specified
//Note - the extends Comparable ensures that items added to the tree should be Comparable
class Main {
  public static void main(String[] args) {
    BinaryTree<Integer> tree = new BinaryTree<Integer>();

    tree.add(41);
    tree.add(20);
    tree.add(65);
    tree.add(11);
    tree.add(29);
    tree.add(32);
    tree.add(50);
    tree.add(91);
    tree.add(72);
    tree.add(99);
    tree.remove(65);
    
    tree.displayInOrder();
  }
}

class BinaryTree<T extends Comparable<T>> { 
  private BinaryTreeNode<T> root;
  
  //************ METHODS TO BE COMPLETED BELOW  ************
  
  //Displays all the items in the tree IN ORDER to the console
  //not a typical method - but very useful for testing/debugging
  //use a recursive helper method help method
  public void displayInOrder() { 
    recursiveDisplayInOrder(root);
    System.out.println();
  }
  
  private void recursiveDisplayInOrder(BinaryTreeNode<T> currentNode) {     
    if (currentNode == null) {
      return;
    }
    
    recursiveDisplayInOrder(currentNode.getLeft());
    System.out.print(currentNode.getItem() + " ");
    recursiveDisplayInOrder(currentNode.getRight());
    
    //hint 1. recurse left, 2. display current item, 3. recurse right
    // just make sure the currentNode is not null!    
  }
  
  //adds an item to the tree in the correct place (this is a Binary Search Tree - sorted)
  public void add(T item) { 
    BinaryTreeNode<T> newNode = new BinaryTreeNode<T> (item, null, null);
    BinaryTreeNode<T> tempNode = root;
    boolean notAdded = true;
    
    if (isEmpty()) {
      root = newNode;
      return;
    }
    
    while (notAdded) {
      if (tempNode.getItem().compareTo(item) > 0) {
        if (tempNode.getLeft() == null) {
          tempNode.setLeft(newNode);
          notAdded = false;
        }
        else {
          tempNode = tempNode.getLeft();
        }
      }
      
      else if (tempNode.getItem().compareTo(item) < 0) {
        if (tempNode.getRight() == null) {
          tempNode.setRight(newNode);
          notAdded = false;
        }
        else {
          tempNode = tempNode.getRight();
        }
      }
      else {
        notAdded = false;
      }
    }
  }
  
  //traverses the tree to find an item and returns true if it exists (use CompareTo)
  //hint - use a private recursive helper method
  public boolean contains(T item) { 
    BinaryTreeNode<T> tempNode = root;
    boolean notFound = true;
    
    while (notFound) {
      if (tempNode != null) {
        if (tempNode.getItem().compareTo(item) > 0) {
          tempNode = tempNode.getLeft();
        }
        
        else if (tempNode.getItem().compareTo(item) < 0) {
          tempNode = tempNode.getRight();
        }
        
        else if (tempNode.getItem().compareTo(item) == 0) {
          notFound = false;
          return true;
        }
      }
      else {
        return false;
      }
    }
    return false;
  }
  
  public BinaryTreeNode<T> findSmallest(BinaryTreeNode<T> tempNode) {
    return (tempNode.getLeft() == null ? tempNode : findSmallest(tempNode.getLeft()));
  }
  
  
  //challenge - remove an item while keeping the tree in order
  //three possibilites:
  //1 - removing a leaf is straight forward
  //2 - remove a node with one child. The child takes the place of the one removed
  //3 - two children - find the largest item in the left subtree to take the place of the removed node
  public boolean remove(T item) { 
    BinaryTreeNode<T> tempNode = root;
    BinaryTreeNode<T> previous = tempNode;
    BinaryTreeNode<T> successor;
    boolean notFound = true;
    
    while (notFound) {
      if (tempNode != null) {
        if (tempNode.getItem().compareTo(item) > 0) {
          previous = tempNode;
          tempNode = tempNode.getLeft();
        }
        else if (tempNode.getItem().compareTo(item) < 0) {
          previous = tempNode;
          tempNode = tempNode.getRight();
        }
        else if (tempNode.getItem().compareTo(item) == 0) {
          notFound = false;
        }
      }
      else {
        return false;
      }
    }
    // Leaf
    if (tempNode.isLeaf()) {
      if (previous.getLeft() != null) {
        previous.setLeft(null);
      }
      else {
        previous.setRight(null);
      }
      return true;
    }
    
    // One child
    if (tempNode.getLeft() == null || tempNode.getRight() == null) {
      if (tempNode.getRight() != null) {
        previous.setRight(tempNode.getRight());
      }
      
      else if (tempNode.getLeft() != null) {
        previous.setLeft(tempNode.getLeft());
      }
      return true;
    }
    
    // Two children
    if (tempNode.getLeft() != null && tempNode.getRight() != null) {
      successor = tempNode;
      
      successor = successor.getRight();
      while (successor.getLeft() != null) {
        successor = successor.getLeft();
      }
      
      successor.setRight(tempNode.getRight());
      successor.setLeft(tempNode.getLeft());
      
      if (successor.getItem().compareTo(previous.getItem()) > 0) {
        previous.setRight(successor);
      }
      else {
        previous.setLeft(successor);
      }
      return true;
    }
    return false;
  } 
  
//Below are the corresponding methods to be added to BinaryTree.java
  
  /*Depth
   * Returns the depth of the tree.
   *  note - the root is at level 0, therefor the depth is the max level + 1
   * @ returns depth/number of levels of the Binary Tree
   */
  public int depth() { 
    return recursiveDepth(root);
  }
  
//recursive helper
  private int recursiveDepth(BinaryTreeNode<T> currentNode) {
    if (root == null) {
      return 0;
    }
    int leftDepth = recursiveDepth(currentNode.getLeft()) + 1;
    int rightDepth = recursiveDepth(currentNode.getRight()) + 1;
    if (leftDepth > rightDepth) {
      return leftDepth + 1;
    }
    else {
      return rightDepth + 1;
    }
  }
  
  /*numberOfLeaves
   * Returns the number of leaves of the tree.
   *  note - Leaves are nodes with no children 
   * @ returns number of leaves in the Binary Tree
   */
  public int numberOfLeaves() { 
    return recursivenumberOfLeaves(root);
  }
  
//recursive helper
  private int recursivenumberOfLeaves(BinaryTreeNode<T> currentNode) { 
    if (currentNode == null) {
      return 0;
    }
    else if (currentNode.getLeft() == null && currentNode.getRight() == null) {
      return 1;
    }
    else {
      return recursivenumberOfLeaves(currentNode.getLeft()) + recursivenumberOfLeaves(currentNode.getRight());
    }
  }
  
  
  /*displayBreadthFirst
   * Displays all the items in the tree Beadth First to the console
   * Hint - Use your Queue created earlier this unit
   * 
   */
  public void displayBreadthFirst() { 
    
    //1. Add the root to the queue to start.
    //2. Remove all the items from the queue
    //    - Output them to the screen
    //    - Add their children to the queue
    //3. Go to step 2. 
  }
  //************ METHODS TO BE COMPLETED ABOVE  ************
  
  
  /* size
   * returns the number of items in the tree
   * @return an integer representing the number of items stored in the tree
   */
  public int size() { 
    return sizeRecursive(root);
  }
  
  //recursive helper method for size()
  private int sizeRecursive(BinaryTreeNode<T> currentNode) { 
    if (currentNode == null) { 
      return 0;
    } else { 
      int numberOfChildNodes = 0;
      numberOfChildNodes += sizeRecursive(currentNode.getLeft());
      numberOfChildNodes += sizeRecursive(currentNode.getRight());
      return numberOfChildNodes + 1; //add the current node to the count
    }
  }
  
  /** isEmpty
    * Determines if the binary tree is empty, no data exists
    * @return true if the binary tree contains no data, otherwise false
    */
  public boolean isEmpty() { 
    return (root == null);
  }
  
} //end of BinaryTree



//**********************************
//    Binary Tree Node Class
//**********************************

/**
 * BinaryTreeNode
 * A Node class for a Binary Tree
 * @author Mangat
 * @version 1.0 2020
 */
class BinaryTreeNode<E> {
  private E item;
  private BinaryTreeNode<E> left;
  private BinaryTreeNode<E> right;
  
  BinaryTreeNode(E i, BinaryTreeNode<E> l, BinaryTreeNode<E> r){
    this.item=i;
    this.left=l;
    this.right=r;
  }
  
  public void setLeft(BinaryTreeNode<E> n)  {
    this.left = n;
  }
  
  public void setRight(BinaryTreeNode<E> n)  {
    this.right = n;
  }
  
  public BinaryTreeNode<E> getLeft() {
    return this.left;
  }
  
  public BinaryTreeNode<E> getRight() {
    return this.right;
  }
  
  public void setItem(E d){
    this.item = d;
  }
  
  public E getItem()  {
    return this.item;
  }     
  
  /** isLeaf
    * determines if the the current node is a leaf
    * @returns true if the current node has no children, otherwise false
    */
  public boolean isLeaf() { 
    if (this.left == null && this.right == null) { 
      return true;
    }
    return false;
  }
  
}