import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by reem on 16/03/17.
 */
class MoveTest {
    @Test
    void test1() {
        Move move = new Move(3, 1, 4);
        assertEquals(move.getRow(), 3);
        assertEquals(move.getLeftBound(), 1);
        assertEquals(move.getRightBound(), 4);
        assertEquals(move.toString(), "3:1-4");
    }

}