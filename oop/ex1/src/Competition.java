import java.util.Scanner;

/**
 * The Competition class represents a Nim competition between two players, consisting of a given number of
 * rounds. It also keeps track of the number of victories of each player.
 */
public class Competition {

	private static final int TURN_SUCCEEDED = 0;
	private static final int TURN_ERROR_OUT_OF_BOUNDARIES = -1;
	private static final int TURN_ERROR_OVERLAP = -2;
	private static final int GENERAL_ERROR = -1;

	private static final String MSG_INIT_TURN = "Player %d, it is now your turn!";
	private static final String MSG_INVALID_MOVE = "Invalid move. Enter another:";
	private static final String MSG_MOVE_MADE = "Player %d made the move: %s";
	private static final String MSG_START_GAME = "Welcome to the sticks game!";
	private static final String MSG_WON_GAME = "Player %d won!";
	private static final String MSG_COMP_RESULTS = "The results are %d:%d";
	private static final String MSG_COMP_INIT =
			"Starting a Nim competition of %d rounds between a %s player and a %s player.";

	private boolean shouldPrint;
	private Player player1, player2;

	private int wins1, wins2;

	/**
	 * Constructor for a competition. Initializes the wins of both players to 0 for new competition,
	 * and saves required fields.
	 * @param player1 the first player in the match
	 * @param player2 the second player in the match
	 * @param displayMessage whether verbose mode should be activated
	 */
	public Competition(Player player1, Player player2, boolean displayMessage){
		shouldPrint = displayMessage;
		this.player1 = player1;
		this.player2 = player2;
		wins1 = 0;
		wins2 = 0;
	}

	/**
	 * if in verbose mode, prints a message.
	 * @param str the msg to be printed.
	 */
	private void printIfShould(String str){
		if (shouldPrint){
			System.out.println(str);
		}
	}

	/**
	 * runs a single turn for a player.
	 * @param board the current game board.
	 * @param player the player playing the cur turn.
	 */
	private void runSingleTurn(Board board, Player player){

		String start = String.format(MSG_INIT_TURN, player.getPlayerId());
		printIfShould(start);

		while (true){
			Move move = player.produceMove(board);
			int turnResult = board.markStickSequence(move);
			switch (turnResult) {
				case TURN_SUCCEEDED:
					printIfShould(String.format(MSG_MOVE_MADE, player.getPlayerId(), move.toString()));
					return;

				case TURN_ERROR_OUT_OF_BOUNDARIES:
					printIfShould(MSG_INVALID_MOVE);
					break;

				case TURN_ERROR_OVERLAP:
					printIfShould(MSG_INVALID_MOVE);
					break;
			}
		}
	}

	/**
	 * Runs a whole round of the game until a player wins.
	 */
	private void runSingleRound(){
		Board board = new Board();
		printIfShould(MSG_START_GAME);
		Player cur_player = player1;
		while (board.getNumberOfUnmarkedSticks() > 0) {
			runSingleTurn(board, cur_player);
			if (cur_player == player1)
				cur_player = player2;
			else
				cur_player = player1;
		}
		// the winner is the player who has no more sticks to mark.
		if (cur_player == player1) {
			wins1 += 1;
			printIfShould(String.format(MSG_WON_GAME, 1));
		}
		if (cur_player == player2) {
			wins2 += 1;
			printIfShould(String.format(MSG_WON_GAME, 2));
		}
	}

	/**
	 * runs a competition with given num of rounds.
	 * @param numRounds num of rounds to be played. should be greater or equal than 0.
	 */
	public void playMultipleRounds(int numRounds){
		System.out.println(String.format(
				MSG_COMP_INIT,
				numRounds,
				player1.getTypeName(),
				player2.getTypeName()
		));
		for (int i = 0; i < numRounds; i++){
			runSingleRound();
		}
		System.out.println(String.format(MSG_COMP_RESULTS, wins1, wins2));
	}

	/**
	 * gets a players position, return its num of wins.
	 * @param playerPosition legal position is 1 or 2.
	 * @return current num of wins to given position. If position is illegal returns -1.
	 */
	public int getPlayerScore(int playerPosition){
		switch(playerPosition){
			case 1:
				return wins1;
			case 2:
				return wins2;
			default:
				return GENERAL_ERROR;
		}
	}

	/**
	 * The method runs a Nim competition between two players according to the three user-specified arguments. 
	 * (1) The type of the first player, which is a positive integer between 1 and 4: 1 for a Random computer
	 *     player, 2 for a Heuristic computer player, 3 for a Smart computer player and 4 for a human player.
	 * (2) The type of the second player, which is a positive integer between 1 and 4.
	 * (3) The number of rounds to be played in the competition.
	 * @param args an array of string representations of the three input arguments, as detailed above.
	 */
	public static void main(String[] args) {

		int p1Type = Integer.parseInt(args[0]);
		int p2Type = Integer.parseInt(args[1]);
		int numGames = Integer.parseInt(args[2]);

		Scanner scanner = new Scanner(System.in);
		Player player1 = new Player(p1Type, 1, scanner);
		Player player2 = new Player(p2Type, 2, scanner);
		boolean reemAndAdiAreGettingMarried; // WE WANTED TO SAY THAT SOMEWHERE!! WISH US GOOD LUCK AND MAZAL-TOV
		// ah, by the way, this boolean is "should i start in verbose mode"
		reemAndAdiAreGettingMarried =
				player1.getPlayerType() == Player.HUMAN
				|| player2.getPlayerType() == Player.HUMAN;
		Competition competition = new Competition(player1, player2, reemAndAdiAreGettingMarried);
		competition.playMultipleRounds(numGames);
		scanner.close();
	}

}
