package oop.ex4.data_structures;

/**
 * Created by abrock on 5/9/17.
 */
public class AvlTree extends BinarySearchTree{

    /* constructors */

    /**
     * The default constructor.
     */
    public AvlTree(){}

    /**
     * A constructor that builds a new AVL tree containing all unique values in the input
     ∗ array.
     * @param data
     */
    public AvlTree(int[] data){ super(data);}

    /**
     * A copy constructor that creates a deep copy of the given oop.ex4.data_structures.AvlTree. The new tree
     ∗ contains all the values of the given tree, but not necessarily in the same structure.
     * @param avlTree
     */
    public AvlTree(AvlTree avlTree){ super (avlTree); }


    /* methods */

    /**
     * Add a new node with the given key to the tree.
     * @param newValue the value to search for.
     * @return true if the value to add is not already in the tree and it was successfully added,
    ∗ false otherwise.
     */
    @Override
    protected boolean performAdd(int newValue) {
        if (root == null) {
            root = new Node(newValue);
            return true;
        }
        // add to existing tree
        Node curNode = root;
        Node parentOfNewVal = null;
        while(curNode != null){
            if (curNode.getData() == newValue) { return false; }
            parentOfNewVal = curNode;
            if (newValue > curNode.getData()) { curNode = curNode.getRight(); }
            else //(newValue < curNode.getData())
                 { curNode = curNode.getLeft(); }
        }
        Node newNode = new Node(newValue, parentOfNewVal);
        if (newValue > parentOfNewVal.getData()) { parentOfNewVal.setRight(newNode); }
        else { parentOfNewVal.setLeft(newNode); }

        //check if still balanced and rotate appropriately in needed

    }
    //while (abs(curNode.getRight().getHeight() - curNode.getLeft().getHeight()) <= 1){
//            curNode.setHeight( curNode.getHeight() + 1)
//            curNode = curNode.getParent()





    /**
     * Removes the node with the given value from the tree, if it exists.
     * @param toDelete the value to remove from the tree.
     * @return true if the given value was found and deleted, false otherwise.
     */
    @Override
    protected boolean performDelete(int toDelete) {}

    /**
     * Calculates the minimum number of nodes in an AVL tree of height h.
     * @param h the height of the tree (a non−negative number) in question.
     * @return the minimum number of nodes in an AVL tree of the given height.
     */
    public static int findMinNodes(int h){}

    /**
     * Calculates the maximum number of nodes in an AVL tree of height h.
     * @param h the height of the tree (a non−negative number) in question.
     * @return the maximum number of nodes in an AVL tree of the given height.
     */
    public static int findMaxNodes(int h){}

    /**
     * performs a LL tree rotation
     * @param node the first node in tree where the balance is broken
     */
    private void rotateLL(Node node){
        Node ub = node; //first unbalanced node
        Node ubParent = ub.getParent(); // parent of first unbalanced node
        Node ubLC = ub.getLeft(); // left child of unbalanced node
        Node ubLCRightSubtree = ubLC.getRight();

        ubLC.setParent(ubParent);
        if (ubParent != null) {
            if (ubLC.getData() > ubParent.getData()) {
                ubParent.setRight(ubLC);
            } else {
                ubParent.setLeft(ubLC);
            }
        } else {
            assert root==ub;
            root = ubLC;
        }

        ub.setLeft(ubLCRightSubtree);
        if (ubLCRightSubtree != null) {
            ubLCRightSubtree.setParent(ub);
        }

        ubLC.setRight(ub);
        ub.setParent(ubLC);

    // todo move through [ub, ubLC, ubParent] and update the heights!
        Node newRoot = ubLC;
        Node newRootLeft = newRoot.getLeft();
        Node newRootRight = newRoot.getRight();


        int leftHeight = newRootLeft.getLeft().getHeight();  // todo if newRootLeft.getLeft() is null?
        int rightHeight = ;
        newRootRight.setHeight(Math.max());
}

    /**
     * performs a LR tree rotation
     * @param node the first node in tree where the balance is broken
     * @return true if rotation succeeded
     */
    private boolean rotateLR(Node node){}

    /**
     * performs a RR tree rotation
     * @param node the first node in tree where the balance is broken
     * @return true if rotation succeeded
     */
    private boolean rotateRR(Node node){}

    /**
     * performs a RL tree rotation
     * @param node the first node in tree where the balance is broken
     * @return true if rotation succeeded
     */
    private boolean rotateRL(Node node){}

}