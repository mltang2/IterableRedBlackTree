//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//public class P104SubmissionChecker {
//  /**
//   * Tests my question 3 from the quiz.
//   *
//   * @return true if cases pass, false otherwise
//   */
//  @Test
//  public void test1() {
//    RedBlackTree<String> tree1 = new RedBlackTree<>();
//
//
//    tree1.insert("N");
//    tree1.insert("E");
//    tree1.insert("U");
//    tree1.insert("C");
//    tree1.insert("G");
//    tree1.insert("Q");
//    tree1.insert("W");
//    tree1.insert("O");
//    tree1.insert("S");
//    //Sets up initial tree structure
//
//    tree1.insert("L");
//    //add new node
//
//    //returns true if L is inserted and in the right spot
//    Assertions.assertEquals("L", tree1.root.getLeft().getRight().getRight().getData(), "Failed");
//  }
//
//  /**
//   * tests my question 4 from the quiz.
//   *
//   * @return true if cases pass, false otherwise;
//   */
//  @ Test
//  public void test2() {
//    RedBlackTree<String> tree2 = new RedBlackTree<>();
//
//
//
//    tree2.insert("L");
//    tree2.insert("E");
//    tree2.insert("U");
//    tree2.insert("C");
//    tree2.insert("I");
//    tree2.insert("P");
//    tree2.insert("W");
//    tree2.insert("H");
//    tree2.insert("J");
//
//    //add new node
//    tree2.insert("K");
//
//    //return true if K is inserted into the right spot and has the correct color
//    //notice that the root has moved
//    Assertions.assertEquals
//        ("K", tree2.root.getLeft().getRight().getData(), "Failed");
//
//  }
//
//  /**
//   * tests a basic 2 level tree
//   *
//   * @return
//   */
//  @Test
//  public void test3() {
//    RedBlackTree<String> tree3 = new RedBlackTree<>();
//
//    //Sets up initial tree structure
//    tree3.insert("N");
//    tree3.insert("G");
//
//    //insert T node
//
//    tree3.insert("T");
//    Assertions.assertEquals("T", tree3.root.getRight().getData(), "Failed");
//  }
//}
