import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by reem on 16/03/17.
 */
class CompetitionTest {

    @Test
    void getPlayerScoreTest(){
     Competition competition = new Competition(null, null, true);
     assertEquals(0, competition.getPlayerScore(1));
     assertEquals(0, competition.getPlayerScore(2));
     assertEquals(-1, competition.getPlayerScore(598));
    }

    @Test
    void assertRandomPlayerOnlyValidMoves() {
        Board board = new Board();
        Player randomPlayer = new Player(Player.RANDOM, 1, null);
        while (board.getNumberOfUnmarkedSticks() > 0) {
            Move move = randomPlayer.produceMove(board);
            int moveResult = board.markStickSequence(move);
            assertTrue(moveResult == 0);
        }
    }

    @Test
    void assertSmartPlayerOnlyValidMoves() {
        Board board = new Board();
        Player smartPlayer = new Player(Player.SMART, 1, null);
        while (board.getNumberOfUnmarkedSticks() > 0) {
            Move move = smartPlayer.produceMove(board);
            int moveResult = board.markStickSequence(move);
            assertTrue(moveResult == 0);
        }
    }


    @Test
    void smartIsBetterThanRandom() {
        for (int i = 0; i < 20; i++) {
            Competition competition = new Competition(new Player(Player.RANDOM, 1, null), new Player(Player.SMART, 2, null), false);
            competition.playMultipleRounds(1000);
            assertTrue(competition.getPlayerScore(1) + 100 < competition.getPlayerScore(2));
        }
    }
}