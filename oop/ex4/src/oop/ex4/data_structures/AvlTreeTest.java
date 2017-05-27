package oop.ex4.data_structures;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Re'em on 5/13/2017.
 */
public class AvlTreeTest {
    private AvlTree emptyTree;
    private AvlTree treeWith0123456789;


    @Before
    public void setUp() {
        emptyTree = new AvlTree();
        treeWith0123456789 = new AvlTree(new int[]{0,1,2,3,4,5,6,7,8,9});
    }

    @Test
    public void findMinNodes() {
        assertEquals(AvlTree.findMinNodes(6), AvlTree.findMinNodes(5) + AvlTree.findMinNodes(4) + 1);
    }

    @Test
    public void findMaxNodes(){
        assertEquals(31, AvlTree.findMaxNodes(5));
    }


    @Test
    public void copyConstructor() {
        AvlTree treeCopyCtor = new AvlTree(treeWith0123456789);

        for (int i : treeCopyCtor) {
            assertTrue(treeWith0123456789.contains(i) >= 0);
            assertTrue(treeCopyCtor.contains(i) >= 0);
        }
        for (int i : treeWith0123456789) {
            assertTrue(treeWith0123456789.contains(i) >= 0);
            assertTrue(treeCopyCtor.contains(i) >= 0);
        }

        assertEquals(treeCopyCtor.size(), treeWith0123456789.size());

        treeWith0123456789.add(666);
        assertEquals(-1, treeCopyCtor.contains(666));
        assertNotEquals(-1, treeWith0123456789.contains(666));
    }

    @Test
    public void size() {
        int maxNumToRun = 1024;
        for (int i = 0; i <= maxNumToRun; i++) {
            assertEquals(emptyTree.size(), i);
            emptyTree.add(i);
        }
        for (int i = maxNumToRun; i >= 1; i--) {
            assertEquals(i + 1, emptyTree.size());
            emptyTree.delete(i);
        }
    }

    @Test
    public void simpleAvlTree() {
        assertEquals(0, emptyTree.size());

        assertTrue(emptyTree.add(10));
        assertEquals(0, emptyTree.contains(10));

        assertTrue(emptyTree.add(20));
        assertEquals(0, emptyTree.contains(10));
        assertEquals(1, emptyTree.contains(20));


        assertTrue(emptyTree.add(15));
        assertEquals(0, emptyTree.contains(15));
        assertEquals(1, emptyTree.contains(10));
        assertEquals(1, emptyTree.contains(20));


        assertTrue(emptyTree.add(5));
        assertEquals(0, emptyTree.contains(15));
        assertEquals(1, emptyTree.contains(10));
        assertEquals(1, emptyTree.contains(20));
        assertEquals(2, emptyTree.contains(5));

        assertTrue(emptyTree.add(12));
        assertEquals(0, emptyTree.contains(15));
        assertEquals(1, emptyTree.contains(10));
        assertEquals(1, emptyTree.contains(20));
        assertEquals(2, emptyTree.contains(5));
        assertEquals(2, emptyTree.contains(12));

        assertTrue(emptyTree.delete(20));
        assertEquals(-1, emptyTree.contains(20));

        assertNotEquals(-1, emptyTree.contains(15));
        assertNotEquals(-1, emptyTree.contains(12));
        assertNotEquals(-1, emptyTree.contains(10));
        assertNotEquals(-1, emptyTree.contains(5));
        assertEquals(4, emptyTree.size());
    }

    @Test
    public void workOn128Elements() {
        int maxNum = 8;

        List<Integer> allUpTo128shuffled = new ArrayList<>();
        for (int i = 0; i < maxNum; i++) {
            allUpTo128shuffled.add(i);
        }
        shuffle(allUpTo128shuffled);

        for (int i : allUpTo128shuffled) {
            assertTrue(emptyTree.add(i));
            assertFalse(emptyTree.add(i));
        }

        assertEquals(maxNum, emptyTree.size());

        for (int i = 0; i < maxNum; i++) {
            assertNotEquals(-1, emptyTree.contains(allUpTo128shuffled.get(i)));
        }

        for (int i : allUpTo128shuffled) {
            assertTrue(emptyTree.delete(i));
            assertFalse(emptyTree.delete(i));
        }

        assertEquals(0, emptyTree.size());
    }

    @Test
    public void iterator() {
        for (int i : treeWith0123456789) {
            assertTrue(emptyTree.add(i));
        }

        boolean[] allInEmpty = new boolean[10];
        for (int i : emptyTree) {
            allInEmpty[i] = true;
        }
        for (int i=0; i<10; i++) {
            assertTrue(allInEmpty[i]);
        }
    }

    @Test
    public void onlyOddNumbers() {
        int maxNum = 1024;
        for (int i = 0; i < maxNum; i++) {
            if (i % 2 == 0) {
                assertTrue(emptyTree.add(i));
            }
        }

        for (int i = 0; i < maxNum; i++) {
            if (i % 2 == 0) {
                assertTrue(emptyTree.contains(i) >= 0);
            } else {
                assertFalse(emptyTree.contains(i) >= 0);
            }
        }

        assertEquals(maxNum/2, emptyTree.size());

        for (int i = 0; i < maxNum; i++) {
            if (i % 2 == 0) {
                assertTrue(emptyTree.delete(i));
            } else {
                assertFalse(emptyTree.delete(i));
            }
        }

        assertEquals(0, emptyTree.size());

    }


    private static void shuffle(List<Integer> input) {
        Random rnd = new Random();
        Collections.shuffle(input, rnd);
    }
}