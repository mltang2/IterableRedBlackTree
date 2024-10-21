//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Iterable Red Black Tree
// Course:   CS 400 Fall 2024
//
// Author:   Michael Tang
// Email:    mltang2@wisc.edu
// Lecturer: Gary Dahl
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         No assistance from other people
// Online Sources:
// none used besides lecture nodes
//
///////////////////////////////////////////////////////////////////////////////
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

/**
 * This class extends RedBlackTree into a tree that supports iterating over the values it
 * stores in sorted, ascending order.
 */
public class IterableRedBlackTree<T extends Comparable<T>>
    extends RedBlackTree<T> implements IterableSortedCollection<T> {

  protected Comparable<T> min;
  protected Comparable<T> max;


  /**
   * Allows setting the start (minimum) value of the iterator. When this method is called,
   * every iterator created after it will use the minimum set by this method until this method
   * is called again to set a new minimum value.
   * @param min the minimum for iterators created for this tree, or null for no minimum
   */
  public void setIteratorMin(Comparable<T> min) {
    this.min = min; //set min value
  }

  /**
   * Allows setting the stop (maximum) value of the iterator. When this method is called,
   * every iterator created after it will use the maximum set by this method until this method
   * is called again to set a new maximum value.
   * @param max the maximum for iterators created for this tree, or null for no maximum
   */
  public void setIteratorMax(Comparable<T> max) {
    this.max = max; //set max value
  }

  /**
   * Returns an iterator over the values stored in this tree. The iterator uses the
   * start (minimum) value set by a previous call to setIteratorMin, and the stop (maximum)
   * value set by a previous call to setIteratorMax. If setIteratorMin has not been called
   * before, or if it was called with a null argument, the iterator uses no minimum value
   * and starts with the lowest value that exists in the tree. If setIteratorMax has not been
   * called before, or if it was called with a null argument, the iterator uses no maximum
   * value and finishes with the highest value that exists in the tree.
   */
  public Iterator<T> iterator() { //constructor ot make a new RBTIterator
    return new RBTIterator<>(this.root, min, max); }

  /**
   * Nested class for Iterator objects created for this tree and returned by the iterator method.
   * This iterator follows an in-order traversal of the tree and returns the values in sorted,
   * ascending order.
   */
  protected static class RBTIterator<R> implements Iterator<R> {

    // stores the start point (minimum) for the iterator
    Comparable<R> min = null;
    // stores the stop point (maximum) for the iterator
    Comparable<R> max = null;
    // stores the stack that keeps track of the inorder traversal
    Stack<BSTNode<R>> stack = null;

    /**
     * Constructor for a new iterator if the tree with root as its root node, and
     * min as the start (minimum) value (or null if no start value) and max as the
     * stop (maximum) value (or null if no stop value) of the new iterator.
     * @param root root node of the tree to traverse
     * @param min the minimum value that the iterator will return
     * @param max the maximum value that the iterator will return
     */
    public RBTIterator(BSTNode<R> root, Comparable<R> min, Comparable<R> max) {
      this.min = min;
      this.max = max;
      this.stack = new Stack<>();
      buildStackHelper(root); //start the stack with the root
    }

    /**
     * Helper method for initializing and updating the stack. This method both
     * - finds the next data value stored in the tree (or subtree) that is bigger
     *   than or equal to the specified start point (maximum), and
     * - builds up the stack of ancestor nodes that contain values larger than or
     *   equal to the start point so that those nodes can be visited in the future.
     * @param node the root node of the subtree to process
     */
    private void buildStackHelper(BSTNode<R> node) {
      if (node == null) return; //return if an empty node (base case)

      if ((min == null || min.compareTo(node.data) <= 0) &&
          (this.max == null || max.compareTo(node.data) >= 0)) {
        stack.push(node); //push the node onto the stack if valid within boundaries
      }
      buildStackHelper(node.left); //traverse left
    }

    /**
     * Returns true if the iterator has another value to return, and false otherwise.
     */
    public boolean hasNext() {
      return !stack.isEmpty(); //has next is valid if the stack is not empty
    }

    /**
     * Returns the next value of the iterator.
     * @throws NoSuchElementException if the iterator has no more values to return
     */
    public R next() {
      if (!hasNext()) {
        throw new NoSuchElementException("no more values to return");
      }
      BSTNode<R> nextNode = stack.pop(); //pop after retrieving the next data
      buildStackHelper(nextNode.right); //traverse right
      return nextNode.data;


    }
  }

  /**
   * Junit Test to test a set max and min
   */

  @Test
  public void testSpecifiedStartAndStop() {
    IterableRedBlackTree<Integer> tree = new IterableRedBlackTree<>();
    tree.insert(10);
    tree.insert(15);
    tree.insert(7);
    tree.insert(20);
    tree.insert(21);
    tree.insert(15);
    //adds nodes

    tree.setIteratorMax(16);
    tree.setIteratorMin(6);
    //sets min and max

    Iterator<Integer> iterator = tree.iterator();

    Assertions.assertEquals(7, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(10, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(15, iterator.next(), "Not Correct Number");
  }

  /**
   * Junit test to test String values
   */

  @Test
  public void string() {

    IterableRedBlackTree<String> tree = new IterableRedBlackTree<>();
    tree.insert("B");
    tree.insert("C");
    tree.insert("A");
    tree.insert("D");
    tree.insert("E");
    tree.insert("C");
    //inserts nodes

    tree.setIteratorMax("C");
    tree.setIteratorMin("A");
    //sets min and max

    Iterator<String> iterator = tree.iterator();

    Assertions.assertEquals("A", iterator.next(), "Not Correct Letter");
    Assertions.assertEquals("B", iterator.next(), "Not Correct Letter");
    Assertions.assertEquals("C", iterator.next(), "Not Correct Letter");
  }

  /**
   * Junit test to test no specified min and max
   */

  @Test
  public void testNoSpecified() {
    IterableRedBlackTree<Integer> tree = new IterableRedBlackTree<>();
    tree.insert(10);
    tree.insert(15);
    tree.insert(7);
    tree.insert(20);
    tree.insert(21);
    tree.insert(15);
    // insert nodes

    Iterator<Integer> iterator = tree.iterator();

    Assertions.assertEquals(7, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(10, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(15, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(15, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(20, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(21, iterator.next(), "Not Correct Number");
  }

  /**
   * Junit Test to test setting min only
   */
  @Test
  public void testMinOnly() {
    IterableRedBlackTree<Integer> tree = new IterableRedBlackTree<>();
    tree.insert(10);
    tree.insert(5);
    tree.insert(15);
    tree.insert(3);
    tree.insert(7);
    tree.insert(12);
    tree.insert(20);
    //insert nodes

    tree.setIteratorMin(10);
    //set min

    Iterator<Integer> iterator = tree.iterator();

    Assertions.assertEquals(10, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(12, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(15, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(20, iterator.next(), "Not Correct Number");
  }

  /**
   * Junit test to test only setting max
   */
  public void testMaxOnly() {
    IterableRedBlackTree<Integer> tree = new IterableRedBlackTree<>();
    tree.insert(10);
    tree.insert(5);
    tree.insert(15);
    tree.insert(3);
    tree.insert(7);
    tree.insert(12);
    tree.insert(20);
    //insert nodes

    tree.setIteratorMax(10);
    //set max

    Iterator<Integer> iterator = tree.iterator();

    Assertions.assertEquals(3, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(5, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(7, iterator.next(), "Not Correct Number");
  }
}
