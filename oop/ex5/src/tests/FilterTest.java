package tests;

import exceptions.FilterException;
import filesprocessing.FileInfo;
import filters.filters.Filter;
import filters.filters.FilterFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Re'em on 6/2/2017.
 */
class FilterTest {
    @Test
    void testEverythingOk() throws FilterException {
        Filter filter = FilterFactory.createDefaultFilter();
        assertTrue(filter.filter(new FileInfo(15, "f.txt", false, false, false, "D:/f.txt")));
        assertTrue(filter.filter(new FileInfo(68, "f.txt", false, true, false, "D:/f.txt")));
        assertTrue(filter.filter(new FileInfo(0, "f.txt", true, true, true, "D:/f.txt")));
        assertTrue(filter.filter(new FileInfo(15, "a.py", false, false, false, "D:/my_python/a.py")));
    }

    @Test
    void greater() {
        try {
            Filter greaterFilter = FilterFactory.createFilter("greater_than#5");
            assertTrue(greaterFilter.filter(new FileInfo(15000, "f.txt", false, false, false, "D:/f.txt")));
            assertFalse(greaterFilter.filter(new FileInfo(68, "f.txt", false, true, false, "D:/f.txt")));
            assertFalse(greaterFilter.filter(new FileInfo(0, "f.txt", true, true, true, "D:/f.txt")));
            assertTrue(greaterFilter.filter(new FileInfo(15975493, "a.py", false, false, false, "D:/my_python/a.py")));
        } catch (FilterException exception) {
            throw new RuntimeException();
        }
    }

    @Test
    void filterException() {
    assertThrows(FilterException.class, () -> FilterFactory.createFilter("Greaather_thak#78jh#not"));
    }
}