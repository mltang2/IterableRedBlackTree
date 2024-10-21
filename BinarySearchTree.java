public class BinarySearchTree<T extends Comparable<T>> implements SortedCollection<T>{
  protected BSTNode<T> root; //reference to the root
  /**
   * Inserts a new data value into the sorted collection.
   * @param data the new value being insterted
   * @throws NullPointerException if data argument is null, we do not allow
   * null values to be stored within a SortedCollection
   */
  public void insert(T data) throws NullPointerException {
    if (data == null) {
      throw new NullPointerException();
    }
    BSTNode<T> newNode = new BSTNode<>(data);
    if (root == null) {
      root = newNode;
    }
    else {
      insertHelper(newNode, root);
    }
  }

  /**
   * Performs the naive binary search tree insert algorithm to recursively
   * insert the provided newNode (which has already been initialized with a
   * data value) into the provided tree/subtree.  When the provided subtree
   * is null, this method does nothing.
   */
  protected void insertHelper(BSTNode<T> newNode, BSTNode<T> subtree) {
    if (subtree == null) {
      return;
    }
    int compare = newNode.getData().compareTo(subtree.getData());

    if (compare <= 0) {
      if (subtree.getLeft() == null) {
        subtree.setLeft(newNode);
        newNode.setUp(subtree);
      } else {
        insertHelper(newNode, subtree.getLeft());
      }
    } else {
      if (subtree.getRight() == null) {
        subtree.setRight(newNode);
        newNode.setUp(subtree);
      } else {
        insertHelper(newNode, subtree.getRight());
      }
    }
  }

  /**
   * Check whether data is stored in the tree.
   *
   * @param data the value to check for in the collection
   * @return true if the collection contains data one or more times, and false otherwise
   * @throws NullPointerException if data argument is null
   */
  public boolean contains(Comparable<T> data) {
    if (data == null) {
      throw new NullPointerException();
    }
    else {
      return containsHelper(data, root);
    }
  }

  /**
   * Helper method for the contains method. Recursively searches for the data value stored in the
   * tree.
   * @param data the value to check for in the collection
   * @param root the root of the current subtree
   * @return true if the collection contains data one or more times, and false otherwise
   */

  protected boolean containsHelper(Comparable<T> data, BSTNode<T> root) {
    if (root == null) {
      return false;
    }
    else if (data.compareTo(root.getData()) == 0) {
      return true;
    }
    else if (data.compareTo(root.getData()) < 0) {
      return containsHelper(data, root.getLeft());
    }
    else {
      return containsHelper(data, root.getRight());
    }
  }

  /**
   * Counts the number of values in the collection, with each duplicate value being counted
   * separately within the value returned.
   *
   * @return the number of values in the collection, including duplicates
   */
  public int size() {
    return sizeHelper(root);
  }
  /**
   * Performs a recursive search to determine the number of nodes there are in the tree
   *
   * @return the number of values in the collection, including duplicates.
   */
  protected int sizeHelper(BSTNode<T> root) {
    if (root == null) {
      return 0;
    }
    else {
      return (1 + sizeHelper(root.getLeft()) + sizeHelper(root.getRight()));
    }
  }

  /**
   * Checks if the collection is empty.
   *
   * @return true if the collection contains 0 values, false otherwise
   */
  public boolean isEmpty() {
    return (root == null);
  }

  /**
   * Removes all values and duplicates from the collection.
   */
  public void clear() {
    this.root = null;
  }

  /**
   * tests insert, contains, size, clear, and isEmpty methods on a balanced bst.
   * @return true or false depending on if the cases pass or not
   */

  public boolean test1() {
    BinarySearchTree<Integer> tree1 = new BinarySearchTree<>();
    tree1.insert(10);
    tree1.insert(6);
    tree1.insert(14);
    tree1.insert(4);
    tree1.insert(8);
    tree1.insert(12);
    tree1.insert(16);

    if (tree1.size() != 7)  return false;

    if (!tree1.contains(6)) return false;

    if (!tree1.contains(4)) return false;

    if (!tree1.contains(16)) return false;

    if (tree1.contains(100)) return false;

    tree1.clear();

    if (tree1.contains(6)) return false;

    if (!tree1.isEmpty()) return false;

    return true;
  }

  /**
   * tests insert, contains, size, clear, and isEmpty methods on non balanced bst in which no nodes
   * have a left child.
   * @return true or false depending on if the cases pass or not
   */

  public boolean test2() {
    BinarySearchTree<Integer> tree2 = new BinarySearchTree<>();
    tree2.insert(1);
    tree2.insert(2);
    tree2.insert(3);
    tree2.insert(4);
    tree2.insert(4);
    tree2.insert(5);

    if (tree2.size() != 6)  return false;

    if (!tree2.contains(1)) return false;

    if (!tree2.contains(4)) return false;

    if (!tree2.contains(5)) return false;

    if (tree2.contains(6)) return false;

    tree2.clear();

    if (tree2.contains(1)) return false;

    if (!tree2.isEmpty()) return false;

    return true;
  }

  /**
   * tests insert, contains, size, clear, and isEmpty methods on non balanced bst in which no nodes
   * have a right child.
   * @return true or false depending on if the cases pass or not
   */

  public boolean test3() {
    BinarySearchTree<String> tree3 = new BinarySearchTree<>();
    tree3.insert("Zachary");
    tree3.insert("Xavier");
    tree3.insert("Michael");
    tree3.insert("David");
    tree3.insert("Alex");


    if (tree3.size() != 5)  return false;

    if (!tree3.contains("Zachary")) return false;

    if (!tree3.contains("Alex")) return false;

    if (!tree3.contains("Michael")) return false;

    if (tree3.contains("Paul")) return false;

    tree3.clear();

    if (tree3.contains("Alex")) return false;

    if (!tree3.isEmpty()) return false;

    return true;
  }
  public static void main(String[] args) {
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    BinarySearchTree<String> bst2 = new BinarySearchTree<>();


    System.out.println("Test 1: " + (bst.test1() ? "Passed" : "Failed"));
    System.out.println("Test 2: " + (bst.test2() ? "Passed" : "Failed"));
    System.out.println("Test 3: " + (bst2.test3() ? "Passed" : "Failed"));
  }
}

