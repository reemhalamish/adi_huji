package oop.ex4.data_structures;

/**
 * Created by abrock on 5/9/17.
 */
public class AvlTree extends BinarySearchTree {
    private static final int HEIGHT_NO_CHILD = -1;

    /* constructors */

    /**
     * The default constructor.
     */
    public AvlTree() {
    }

    /**
     * A constructor that builds a new AVL tree containing all unique values in the input
     * ∗ array.
     *
     * @param data
     */
    public AvlTree(int[] data) {
        super(data);
    }

    /**
     * A copy constructor that creates a deep copy of the given oop.ex4.data_structures.AvlTree. The new tree
     * ∗ contains all the values of the given tree, but not necessarily in the same structure.
     *
     * @param avlTree
     */
    public AvlTree(AvlTree avlTree) {
        super(avlTree);
    }

    /* methods */

    /**
     * Add a new node with the given key to the tree.
     *
     * @param newValue the value to search for.
     * @return true if the value to add is not already in the tree and it was successfully added,
     * ∗ false otherwise.
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
        while (curNode != null) {
            if (curNode.getData() == newValue) {
                return false;
            }
            parentOfNewVal = curNode;
            if (newValue > curNode.getData()) {
                curNode = curNode.getRight();
            } else //(newValue < curNode.getData())
            {
                curNode = curNode.getLeft();
            }
        }
        Node newNode = new Node(newValue, parentOfNewVal);
        if (newValue > parentOfNewVal.getData()) {
            parentOfNewVal.setRight(newNode);
        } else {
            parentOfNewVal.setLeft(newNode);
        }

        //check if still balanced and rotate appropriately in needed
        fixTreeUpwards(newNode);
        return true;
    }

    /**
     * Removes the node with the given value from the tree, if it exists.
     *
     * @param toDelete the value to remove from the tree.
     * @return true if the given value was found and deleted, false otherwise.
     */
    @Override
    protected boolean performDelete(int toDelete) {
        if (contains(toDelete) == DATA_NOT_IN_TREE) {
            return false;
        }

        Node curNode = root;
        // find the value in tree
        while (curNode.getData() != toDelete) {
            if (curNode.getData() < toDelete) {
                curNode = curNode.getRight();
            } else {
                curNode = curNode.getLeft();
            }
        }

        // now curNode is relevant node
        /*
        divide into three different case:
        1. if node has no children: simple delete and fix tree
        2. if node has one child: wire the child and the parent of curNode, recalculate height, and fix tree
        3. if node has two children: find leftmost child of right subtree, exchange it's data with curNode,
            then delete the leftmost child (and wire its right child if exists)

            anyway, if current node is root (has no parent), instead of fixing the tree we will set "root" accordingly
         */
        Node parent = curNode.getParent();
        if (curNode.getLeft() == null && curNode.getRight() == null) {
            if (curNode == root) {
                root = null;
            } else {

                if (parent.getData() < curNode.getData()) {
                    parent.setRight(null);
                } else {
                    parent.setLeft(null);
                }

                    fixTreeUpwards(parent);
            }
            return true;

        }
        if (curNode.getRight() == null) { // we know for a fact it has a left child only
            Node leftChild = curNode.getLeft();
            leftChild.setParent(parent);

            if (curNode == root) {
                root = leftChild;
            } else {
                if (parent.getData() < curNode.getData()) {
                    parent.setRight(leftChild);
                } else {
                    parent.setLeft(leftChild);
                }
                fixTreeUpwards(parent);
            }
            return true;

        }
        if (curNode.getLeft() == null) { // well, NOW we know for a fact it has a right child only
            Node rightChild = curNode.getRight();
            rightChild.setParent(curNode.getParent());

            if (curNode == root) {
                root = rightChild;
            } else {
                if (curNode.getParent().getData() < curNode.getData()) {
                    curNode.getParent().setRight(rightChild);
                } else {
                    curNode.getParent().setLeft(rightChild);
                }
                fixTreeUpwards(parent);
            }
            return true;

        }
        // last case - node to delete has two children
        Node leftmostInRightSubTree = curNode.getRight();
        while (leftmostInRightSubTree.getLeft() != null) {
            leftmostInRightSubTree = leftmostInRightSubTree.getLeft();
        }

        int tmp = leftmostInRightSubTree.getData();
        performDelete(tmp);
        curNode.setData(tmp);
        return true;
    }

    /**
     * Calculates the minimum number of nodes in an AVL tree of height h.
     * @param h the height of the tree (a non−negative number) in question.
     * @return the minimum number of nodes in an AVL tree of the given height.
     */
    public static int findMinNodes(int h){
        if (h==0) { return 1; }
        if (h==1) { return 2; }
        return 1 + findMinNodes(h-1) + findMinNodes(h-2);
    }

    /**
     * Calculates the maximum number of nodes in an AVL tree of height h.
     *
     * when tree is full, num of nodes is  2**h - 1.
     * 2**h can be achieved with shift left h times.
     *
     * @param h the height of the tree (a non−negative number) in question.
     * @return the maximum number of nodes in an AVL tree of the given height.
     */
    public static int findMaxNodes(int h){ return (1<<h) -1 ;}

    /**
     * performs a LL tree rotation
     * @param node the first node in tree where the balance is broken
     */
    private void rotateLL(Node node){
        Node ub = node; //first unbalanced node
        Node ubParent = ub.getParent(); // parent of first unbalanced node
        Node ubLC = ub.getLeft(); // left child of unbalanced node
        Node ubLCRightSubtree = ubLC.getRight(); // right subtree of left child of ub

        // wire new root and it's parent
        ubLC.setParent(ubParent);
        if (ubParent != null) {
            if (ubLC.getData() > ubParent.getData()) {
                ubParent.setRight(ubLC);
            } else {
                ubParent.setLeft(ubLC);
            }
        } else {
            root = ubLC;
        }

        // wire new left child with left sub tree
        ub.setLeft(ubLCRightSubtree);
        if (ubLCRightSubtree != null) {
            ubLCRightSubtree.setParent(ub);
        }

        // wire new root and new right child
        ubLC.setRight(ub);
        ub.setParent(ubLC);

        // update heights
        Node newRoot = ubLC;
        Node newRootLeft = newRoot.getLeft();
        Node newRootRight = newRoot.getRight();
        recalculateNodeHeight(newRootLeft);
        recalculateNodeHeight(newRootRight);
        recalculateNodeHeight(newRoot);
    }

    /**
     * performs a LR tree rotation
     * @param node the first node in tree where the balance is broken
     * @return true if rotation succeeded
     */
    private void rotateLR(Node node){
        rotateRR(node.getLeft());
        rotateLL(node);
    }

    /**
     * performs a RR tree rotation
     * @param node the first node in tree where the balance is broken
     * @return true if rotation succeeded
     */
    private void rotateRR(Node node){
        Node ub = node; //first unbalanced node
        Node ubParent = ub.getParent(); // parent of first unbalanced node
        Node ubRC = ub.getRight(); // right child of unbalanced node
        Node ubRCLeftSubtree = ubRC.getLeft(); // left subtree of right child of ub

        // wire new root and it's parent
        ubRC.setParent(ubParent);
        if (ubParent != null) {
            if (ubRC.getData() > ubParent.getData()) {
                ubParent.setRight(ubRC);
            } else {
                ubParent.setLeft(ubRC);
            }
        } else {
            root = ubRC;
        }

        // wire new left child with left sub tree
        ub.setRight(ubRCLeftSubtree);
        if (ubRCLeftSubtree != null) {
            ubRCLeftSubtree.setParent(ub);
        }

        // wire new root and new left child
        ubRC.setLeft(ub);
        ub.setParent(ubRC);

        // update heights
        Node newRoot = ubRC;
        Node newRootLeft = newRoot.getLeft();
        Node newRootRight = newRoot.getRight();
        recalculateNodeHeight(newRootLeft);
        recalculateNodeHeight(newRootRight);
        recalculateNodeHeight(newRoot);
    }

    /**
     * performs a RL tree rotation
     * @param node the first node in tree where the balance is broken
     * @return true if rotation succeeded
     */
    private void rotateRL(Node node){
        rotateLL(node.getRight());
        rotateRR(node);
    }

    /**
     * recalculates a nodes height based on his children and sets it in node.
     * In case children are both null set 0
     * @param rootNode the node to find it's height
     */
    private void recalculateNodeHeight(Node rootNode) {
        if (rootNode == null) { return; }

        int leftHeight, rightHeight;
        if ( rootNode.getLeft() != null) { leftHeight = rootNode.getLeft().getHeight(); }
        else { leftHeight = HEIGHT_NO_CHILD; }
        if (rootNode.getRight() != null ) { rightHeight = rootNode.getRight().getHeight(); }
        else { rightHeight = HEIGHT_NO_CHILD; }
        rootNode.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }

    private void fixTreeUpwards (Node startNode) {
        Node curNode = startNode;
        while (curNode != null){
            recalculateNodeHeight(curNode);

            int leftHeight, rightHeight;

            // find heights
            if (curNode.getLeft() == null) { leftHeight = HEIGHT_NO_CHILD; }
            else { leftHeight = curNode.getLeft().getHeight(); }
            if (curNode.getRight() == null) { rightHeight = HEIGHT_NO_CHILD; }
            else { rightHeight = curNode.getRight().getHeight(); }

            if (Math.abs(leftHeight - rightHeight) >= 2) {
                if (leftHeight > rightHeight) {
                    if (isRightSubtreeHeavierThanLeft(curNode.getLeft())) {
                        rotateLR(curNode);
                    } else {
                        rotateLL(curNode);
                    }
                } else {
                    if (isRightSubtreeHeavierThanLeft(curNode.getRight())) {
                        rotateRR(curNode);
                    } else {
                        rotateRL(curNode);
                    }
                }
            }
            curNode = curNode.getParent();
        }
    }

    /**
     * checks the heights of a node's subtrees from right and left
     * @param subtreeRoot the node to check
     * @return true iff height of node's right subtree is more than height of node's left sub tree
     */
    private boolean isRightSubtreeHeavierThanLeft(Node subtreeRoot) {
        int leftHeight, rightHeight;
        if ( subtreeRoot.getLeft() != null) { leftHeight = subtreeRoot.getLeft().getHeight(); }
        else { leftHeight = HEIGHT_NO_CHILD; }
        if (subtreeRoot.getRight() != null ) { rightHeight = subtreeRoot.getRight().getHeight(); }
        else { rightHeight = HEIGHT_NO_CHILD; }
        return rightHeight > leftHeight;
    }
}