package oop.ex4.data_structures;

import java.util.Iterator;

/**
 * Created by abrock on 5/10/17.
 */
public class TreeIterator implements Iterator<Integer>{

    private Node curNode;

    /* constructor */
    public TreeIterator(Node curNode) {
        this.curNode = curNode;
    }

    @Override
    public boolean hasNext() {
    }

    @Override
    public Integer next() {
    }
}
