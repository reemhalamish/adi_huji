import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by reem on 03/05/17.
 */
class ClosedHashSetTest {

    ClosedHashSet closedHashSet;

    @BeforeEach
    void setUp() {
        closedHashSet = new ClosedHashSet();
    }

    @Test
    void add() {
        assertTrue(closedHashSet.add("hello"));
        assertFalse(closedHashSet.add("hello"));
        assertEquals(1,closedHashSet.size());
    }

    @Test
    void contains() {

    }

    @Test
    void delete() {
        closedHashSet.add("hi");
        closedHashSet.add("watermelon");
        assertFalse(closedHashSet.delete("hello"));
        assertEquals(2, closedHashSet.size());
        assertTrue(closedHashSet.delete("hi"));
        assertEquals(1, closedHashSet.size());
        assertFalse(closedHashSet.delete("hi"));
    }

    @Test
    void size() {
        for (int i=1; i<=1000; i++ ) {
            closedHashSet.add("" + i);
            System.out.println("" + i);
            assertEquals(i, closedHashSet.size());
        }
        for (int i = 1000; i>=1; i--){
            closedHashSet.delete("" + i);
            System.out.println("" + i);
            assertEquals(i -1, closedHashSet.size());
        }

    }

    @Test
    void capacity() {
        assertEquals(16, closedHashSet.capacity());
        for (int i = 0; i < 13; i++) {
            closedHashSet.add("" + i);
        }
        assertEquals(32, closedHashSet.capacity());
        for (int j = 0; j<= 8; j++){
            closedHashSet.delete("" + j);
        }
        assertEquals(16, closedHashSet.capacity());
    }
}