/**
 * this class implements a rotation given a Binary Search Tree as well tests the methods to make
 * sure it works correctly
 * @param <T> the type of data stored in the nodes of BST
 */
public class BSTRotation<T extends Comparable<T>> extends BinarySearchTree<T> {
  /**
   * constructor that initializes BSTRotation
   */
  public BSTRotation() {
    super();
  }

  /**
   * Performs the rotation operation on the provided nodes within this tree. When the provided child
   * is a left child of the provided parent, this method will perform a right rotation. When the
   * provided child is a right child of the provided parent, this method will perform a left
   * rotation. When the provided nodes are not related in one of these ways, this method will either
   * throw a NullPointerException: when either reference is null, or otherwise will throw an
   * IllegalArgumentException.
   *
   * @param child  is the node being rotated from child to parent position
   * @param parent is the node being rotated from parent to child position
   * @throws NullPointerException     when either passed argument is null
   * @throws IllegalArgumentException when the provided child and parent nodes are not initially
   *                                  (pre-rotation) related that way
   */
  protected void rotate(BSTNode<T> child, BSTNode<T> parent)
      throws NullPointerException, IllegalArgumentException {
    if (parent == null || child == null) {
      throw new NullPointerException("Neither parent nor child can be null");
    }
    if (parent.getLeft() == child) {
      rightRotate(child, parent);
    } else if (parent.getRight() == child) {
      leftRotate(child, parent);
    } else {
      throw new IllegalArgumentException("nodes are neither a parent nor a child of the other node");
    }
  }

  /**
   * This method performs a right rotation in the BST given a parent node and its left child
   * @param child is the node being rotated from child to parent position
   * @param parent is the node being rotated from parent to child position
   */
  protected void rightRotate(BSTNode<T> child, BSTNode<T> parent) {
    parent.setLeft(child.getRight());
    if (child.getRight() != null) {
      child.getRight().setUp(parent);
    }

    child.setUp(parent.getUp());

    if (parent.getUp() == null) {
      root = child;
    }
    else if (parent == parent.getUp().getLeft()) {
      parent.getUp().setLeft(child);
    }
    else {
      parent.getUp().setRight(child);
    }

    child.setRight(parent);
    parent.setUp(child);
  }

  /**
   * This method performs a left rotation in the BST given a parent node and its right child
   * @param child is the node being rotated from child to parent position
   * @param parent is the node being rotated from parent to child position
   */
  protected void leftRotate(BSTNode<T> child, BSTNode<T> parent) {
    parent.setRight(child.getLeft());
    if (child.getLeft() != null) {
      child.getLeft().setUp(parent);
    }

    child.setUp(parent.getUp());

    if (parent.getUp() == null) {
      root = child;
    }
    else if (parent == parent.getUp().getLeft()) {
      parent.getUp().setLeft(child);
    }
    else {
      parent.getUp().setRight(child);
    }

    child.setLeft(parent);
    parent.setUp(child);
  }

  /**
   * tests the rotate method by rotating the root node with a left child
   * @return true or false depending on if the cases pass or not
   */
//  public boolean test1() {
//    BSTNode<T> E = root;
//    BSTNode<T> B = root.getLeft();
//
//    rotate(B, E);
//
//    return root.getData().equals("B") &&
//        root.getLeft().getData().equals("A") &&
//        root.getRight().getData().equals("E") &&
//        root.getRight().getLeft().getData().equals("D") &&
//        root.getRight().getLeft().getLeft().getData().equals("C") &&
//        root.getRight().getRight().getData().equals("H") &&
//        root.getRight().getRight().getLeft().getData().equals("F") &&
//        root.getRight().getRight().getLeft().getRight().getData().equals("G") &&
//        root.getRight().getRight().getRight().getData().equals("J") &&
//        root.getRight().getRight().getRight().getLeft().getData().equals("I") &&
//        root.getRight().getRight().getRight().getRight().getData().equals("K");
//  }
//
//  /**
//   * tests the rotate method by rotating the right child of the root node with the right child of
//   * the right child of the root node.
//   * @return true or false depending on if the cases pass or not
//   */
//  public boolean test2() {
//    root = new BinarySearchTree<T>().root;
//    BSTNode<T> H = root.getRight();
//    BSTNode<T> J = root.getRight().getRight();
//
//    rotate(J, H);
//
//    return root.getData().equals("E") &&
//        root.getLeft().getData().equals("B") &&
//        root.getRight().getData().equals("J") &&
//        root.getRight().getLeft().getData().equals("H") &&
//        root.getRight().getLeft().getLeft().getData().equals("F") &&
//        root.getRight().getRight().getData().equals("K") &&
//        root.getLeft().getLeft().getData().equals("A") &&
//        root.getRight().getLeft().getRight().getData().equals("I") &&
//        root.getRight().getLeft().getLeft().getRight().getData().equals("G") &&
//        root.getLeft().getRight().getData().equals("D") &&
//        root.getLeft().getRight().getLeft().getData().equals("C");
//  }
//
//  /**
//   * tests the rotate method with a child that has no left or right children with its parent node.
//   * @return true or false depending on if the cases pass or not
//   */
//  public boolean test3() {
//    root = new BinarySearchTree<T>().root;
//    BSTNode<T> D = root.getLeft().getRight();
//    BSTNode<T> C = root.getLeft().getRight().getLeft();
//
//    rotate(C, D);
//
//    return root.getData().equals("E") &&
//        root.getLeft().getData().equals("B") &&
//        root.getRight().getData().equals("H") &&
//        root.getRight().getLeft().getData().equals("F") &&
//        root.getRight().getRight().getLeft().getData().equals("I") &&
//        root.getRight().getRight().getData().equals("J") &&
//        root.getLeft().getLeft().getData().equals("A") &&
//        root.getRight().getLeft().getRight().getData().equals("G") &&
//        root.getRight().getRight().getRight().getData().equals("K") &&
//        root.getLeft().getRight().getData().equals("C") &&
//        root.getLeft().getRight().getRight().getData().equals("D");
//  }
//
//  /**
//   * Main method to test the methods. Tests test1(), test2(), and test3() and prints out if passed
//   * or not
//   * @param args (unused)
//   */

}
