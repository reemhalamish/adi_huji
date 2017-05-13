import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenHashSetTest {
    @Test
    void add() {
        OpenHashSet openHashSet = new OpenHashSet();
        assertTrue(openHashSet.add("hello"));
        assertFalse(openHashSet.add("hello"));
        assertEquals(1, openHashSet.size());
    }

    @Test
    void delete() {
        OpenHashSet openHashSet = new OpenHashSet();
        openHashSet.add("hi");
        openHashSet.add("watermelon");
        assertFalse(openHashSet.delete("hello"));
        assertEquals(2, openHashSet.size());
        assertTrue(openHashSet.delete("hi"));
        assertEquals(1, openHashSet.size());
        assertFalse(openHashSet.delete("hi"));
    }

    @Test
    void size() {
        OpenHashSet openHashSet = new OpenHashSet();
        for (int i = 1; i <= 1000; i++) {
            openHashSet.add("" + i);
            assertEquals(i, openHashSet.size());
        }
        for (int i = 1000; i >= 1; i--) {
            openHashSet.delete("" + i);
            assertEquals(i - 1, openHashSet.size());
        }
    }

    @Test
    void contains() {

    }

    @Test
    void capacity() {
        OpenHashSet openHashSet = new OpenHashSet();


    }
}