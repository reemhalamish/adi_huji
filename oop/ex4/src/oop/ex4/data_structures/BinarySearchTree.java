package oop.ex4.data_structures;

import sun.util.locale.provider.AvailableLanguageTags;

import java.util.Iterator;

/**
 * Created by abrock on 5/10/17.
 */
public abstract class BinarySearchTree implements Iterable<Integer>{

    private static final int DATA_NOT_IN_TREE = -1;

    protected Node root;
    private int numOfNodes = 0;

    /* constructors */

    /**
     * The default constructor.
     */
    BinarySearchTree(){}

    /**
     * A constructor that builds a new binary search tree containing all unique values in the input
     ∗ array.
     * @param data
     */
    BinarySearchTree(int[] data) {
        for (int nodeData: data) { add(nodeData); }
    }

    /**
     * A copy constructor that creates a deep copy of the given oop.ex4.data_structures.AvlTree. The new tree
     ∗ contains all the values of the given tree, but not necessarily in the same structure.
     * @param binarySearchTree to deep copy
     */
    BinarySearchTree( BinarySearchTree binarySearchTree){
        copyTree(binarySearchTree.root);
    }

    /**
     * a helper recursive function that copies a subtree of a given node
     * @param parent node that is a root of a subtree to copy
     */
    private void copyTree(Node parent){
        if (parent == null) { return; }
        this.add(parent.getData());
        copyTree(parent.getLeft());
        copyTree(parent.getRight());
    }

    /* methods */

    /**
     * Add a new node with the given key to the tree and update the counter of nodes if needed.
     * @param newValue the value to search for.
     * @return true if the value to add is not already in the tree and it was successfully added,
    ∗ false otherwise.
     */
    public boolean add(int newValue){
        if (performAdd(newValue)) {
            numOfNodes++;
            return true;
        }
        return false;
    }

    /**
     * Add a new node with the given key to the tree.
     * @param newValue the value to search for.
     * @return true if the value to add is not already in the tree and it was successfully added,
    ∗ false otherwise.
     */
    protected abstract boolean performAdd(int newValue);

    /**
     * Removes the node with the given value from the tree, if it exists and updates the counter if needed.
     * @param toDelete the value to remove from the tree.
     * @return true if the given value was found and deleted, false otherwise.
     */
    public boolean delete(int toDelete){
        if (performDelete(toDelete)) {
            numOfNodes--;
            return true;
        }
        return false;
    }

    /**
     * Removes the node with the given value from the tree, if it exists.
     * @param toDelete the value to remove from the tree.
     * @return true if the given value was found and deleted, false otherwise.
     */
    protected abstract boolean performDelete(int toDelete);

    /**
     * Check whether the tree contains the given input value.
     * @param searchVal the value to search for.
     * @return the depth of the node (0 for the root) with the given value if it was found in
    ∗ the tree, −1 otherwise.
     */
    public int contains(int searchVal){
        int depth = 0;
        Node curNode = root;
        while (curNode != null){
            if (curNode.getData() == searchVal) { return depth; }
            else if(searchVal > curNode.getData()) { curNode = curNode.getRight(); }
            else //(searchVal < curNode.getData())
                { curNode = curNode.getLeft(); }
            depth++;
        }
        return DATA_NOT_IN_TREE;
    }


    /**
     *
     * @return the number of nodes in the tree.
     */
    public int size(){ return numOfNodes; }

    /**
     *
     * @return an iterator for the Avl Tree. The returned iterator iterates over the tree nodes
    ∗ in an ascending order, and does NOT implement the remove() method.
     */
    public Iterator<Integer> iterator(){ return new TreeIterator(root);}

}
