public class RedBlackTree<T extends Comparable<T>> extends BSTRotation<T> {

  /**
   * This insert method overrides the method from the BinarySearchTree class. It acts similar,
   * calling insertHelper to insert new nodes, but then calls the ensureRedProperty method to ensure
   * that the RBT is kept balanced according to RBT rules. If the RBT is empty, the data is made
   * into a new RBTNode(always red to begin), and then colored black. Otherwise, the data is made
   * into a new RBTNode, inserted using the insertHelper method from BinarySearchTree class, then
   * the method calls the ensureRedProperty method to verify and correct the structure of the RBT.
   *
   * @param data the data to be used to create and insert the new RBTNode
   * @throws NullPointerException when null data is passed as an argument
   */

  @Override
  public void insert(T data) throws NullPointerException {
    if (data == null) {
      throw new NullPointerException("null value not excepted");
    }

    // root is now data
    if (root == null) {
      RBTNode<T> rbtNode = new RBTNode<>(data);
      rbtNode.flipColor(); //switches color
      root = rbtNode;
    } else {
      RBTNode<T> newNode = new RBTNode<>(data);
      insertHelper(newNode, root);
      ensureRedProperty(newNode);
      //calls ensure red property to make sure not violated
    }
    ((RBTNode<T>) this.root).isRed = false;

  }

  /**
   * Checks if a new red node in the RedBlackTree causes a red property violation by having a red
   * parent. If this is not the case, the method terminates without making any changes to the tree.
   * If a red property violation is detected, then the method repairs this violation and any
   * additional red property violations that are generated as a result of the applied repair
   * operation.
   *
   * @param newRedNode a newly inserted red node, or a node turned red by previous repair
   */
  protected void ensureRedProperty(RBTNode<T> newRedNode) {
    //return if null or black
    if (newRedNode.getUp() == null || !newRedNode.getUp().isRed) {
      return;
    }
    else {
      RBTNode<T> parent = newRedNode.getUp();
      RBTNode<T> gParent;
      RBTNode<T> sibling;
      // set grandparent node to parent's up pointer
      if (parent != null) gParent = parent.getUp();
       else {
        gParent = null;
      }


      if (gParent != null) {
        if (parent.isRightChild()) sibling = gParent.getLeft();
        else {
          sibling = gParent.getRight();
        }
      } else {
        sibling = null;
      }

      if (sibling != null && sibling.isRed) {
        parent.isRed = false;
        sibling.isRed = false;
        gParent.isRed = true;
        ensureRedProperty(gParent);
      }
      else {
        //1st case
        if (parent == gParent.left && newRedNode == parent.left) {
          rightRotate(parent, gParent);
          parent.isRed = false;
          gParent.isRed = true;
        } //2nd case
        else if (parent == gParent.right && newRedNode == parent.right) {

          leftRotate(parent, gParent);
          parent.isRed = false;
          gParent.isRed = true;
        } //3rd case
        else if (parent == gParent.right && newRedNode == parent.left) {
          rightRotate(newRedNode, parent);
          leftRotate(newRedNode, gParent);
          newRedNode.isRed = false;
          gParent.isRed = true;
        }
        //4th case
        else if (parent == gParent.left && newRedNode == parent.right) {
          leftRotate(newRedNode, parent);
          rightRotate(newRedNode, gParent);
          newRedNode.isRed = false;
          gParent.isRed = true;
        }
      }
    }
  }
}
