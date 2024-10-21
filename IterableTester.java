import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

public class IterableTester {
  @Test
  public void testSpecifiedStartAndStop() {
    IterableRedBlackTree<Integer> tree = new IterableRedBlackTree<>();
    tree.insert(10);
    tree.insert(15);
    tree.insert(7);
    tree.insert(20);
    tree.insert(21);
    tree.insert(15);

    tree.setIteratorMax(16);
    tree.setIteratorMin(6);

    Iterator<Integer> iterator = tree.iterator();

    Assertions.assertEquals(7, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(10, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(15, iterator.next(), "Not Correct Number");
  }

  @Test
  public void string() {

    IterableRedBlackTree<String> tree = new IterableRedBlackTree<>();
    tree.insert("B");
    tree.insert("C");
    tree.insert("A");
    tree.insert("D");
    tree.insert("E");
    tree.insert("C");

    tree.setIteratorMax("C");
    tree.setIteratorMin("A");

    Iterator<String> iterator = tree.iterator();

    Assertions.assertEquals("A", iterator.next(), "Not Correct Letter");
    Assertions.assertEquals("B", iterator.next(), "Not Correct Letter");
    Assertions.assertEquals("C", iterator.next(), "Not Correct Letter");
  }

  @Test
  public void testNoSpecified() {
    IterableRedBlackTree<Integer> tree = new IterableRedBlackTree<>();
    tree.insert(10);
    tree.insert(15);
    tree.insert(7);
    tree.insert(20);
    tree.insert(21);
    tree.insert(15);

    Iterator<Integer> iterator = tree.iterator();

    Assertions.assertEquals(7, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(10, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(15, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(15, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(20, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(21, iterator.next(), "Not Correct Number");
  }

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

    tree.setIteratorMin(10);

    Iterator<Integer> iterator = tree.iterator();

    Assertions.assertEquals(10, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(12, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(15, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(20, iterator.next(), "Not Correct Number");
  }

  public void testMaxOnly() {
    IterableRedBlackTree<Integer> tree = new IterableRedBlackTree<>();
    tree.insert(10);
    tree.insert(5);
    tree.insert(15);
    tree.insert(3);
    tree.insert(7);
    tree.insert(12);
    tree.insert(20);

    tree.setIteratorMax(10);

    Iterator<Integer> iterator = tree.iterator();

    Assertions.assertEquals(3, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(5, iterator.next(), "Not Correct Number");
    Assertions.assertEquals(7, iterator.next(), "Not Correct Number");
  }
}
