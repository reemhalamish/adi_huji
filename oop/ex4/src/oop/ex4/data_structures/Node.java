package oop.ex4.data_structures;

/**
 * Created by abrock on 5/9/17.
 */
public class Node {

    private Node parent, left, right;
    private int data;
    private int height = 0;

    /**
     * creates a new node
     * @param data data of node
     */
    public Node(int data){
        this.data = data;
    }

    /**
     * creates a new node with parent
     * @param data data of node
     * @param parent parent of node
     */
    public Node(int data, Node parent) {
        this.data = data;
        this.parent = parent;
    }

    /* getters and setters */

    public Node getParent() { return parent; }

    public void setParent(Node parent) { this.parent = parent; }

    public Node getLeft() { return left; }

    public void setLeft(Node left) { this.left = left; }

    public Node getRight() { return right; }

    public void setRight(Node right) { this.right = right; }

    public int getData() { return data; }

    public void setData(int data) { this.data = data; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    @Override
    public String toString() {
        return String.format("{%d}", data);
    }
}
