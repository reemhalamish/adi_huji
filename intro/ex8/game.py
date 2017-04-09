############################################################
# Imports
############################################################
import game_helper as gh
import ship as ship_file
############################################################
# Class definition
############################################################


class Bomb:
    """
    Represents a bomb in a game
    """

    INIT_TURNS_LEFT = 3
    TIME_STEP = 1
    TIME_ENDED = 0

    def __init__(self, pos):
        self.__pos = pos
        self.__turns_left = Bomb.INIT_TURNS_LEFT

    def get_pos(self):
        return self.__pos

    def get_turns_left(self):
        return self.__turns_left

    def countdown(self):
        self.__turns_left -= Bomb.TIME_STEP

    def is_bomb_ended(self):
        return self.__turns_left == Bomb.TIME_ENDED


class Game:
    """
    A class representing a battleship game.
    A game is composed of ships that are moving on a square board and a user
    which tries to guess the locations of the ships by guessing their
    coordinates.
    """

    def __init__(self, board_size, ships):
        """
        Initialize a new Game object.
        :param board_size: Length of the side of the game-board.
        :param ships: A list of ships (of type Ship) that participate in the
            game.
        :return: A new Game object.
        """
        self.__board_size = board_size
        self.__ships = ships
        self.__bombs = []
        self.__hit_ships_pos = set()

    def __play_one_round(self):
        """
        Note - this function is here to guide you and it is *not mandatory*
        to implement it. The logic defined by this function must be implemented
        but if you wish to do so in another function (or some other functions)
        it is ok.

        The function runs one round of the game :
            1. Get user coordinate choice for bombing.
            2. Move all game's ships.
            3. Update all ships and bombs.
            4. Report to the user the result of current round (number of hits and
             terminated ships)
        :return:
            (some constant you may want implement which represents) Game status :
            GAME_STATUS_ONGOING if there are still ships on the board or
            GAME_STATUS_ENDED otherwise.
        """
        bomb_pos = gh.get_target(self.__board_size)
        self.__bombs.append(Bomb(bomb_pos))

        hits = []
        for ship in self.__ships:
            ship.move()
            for bomb in self.__bombs:
                if ship.hit(bomb.get_pos()):
                    hits.append(bomb.get_pos())
                    self.__hit_ships_pos.add(ship.get_pos())

        not_hit_ships_pos = [ship.get_pos() for ship in self.__ships if not ship.is_hit()]
        bombs_dict = {bomb.get_pos(): bomb.get_turns_left() for bomb in self.__bombs}
        print(gh.board_to_string(self.__board_size,
                                 hits,
                                 bombs_dict,
                                 list(self.__hit_ships_pos),
                                 not_hit_ships_pos))

        self._update_bombs_one_round()

    def __repr__(self):
        """
        Return a string representation of the board's game.
        :return: A tuple converted to string (that is, for a tuple x return
            str(x)). The tuple should contain (maintain
        the following order):
            1. Board's size.
            2. A dictionary of the bombs found on the board, mapping their
                coordinates to the number of remaining turns:
                 {(pos_x, pos_y) : remaining turns}
                For example :
                 {(0, 1) : 2, (3, 2) : 1}
            3. A list of the ships found on the board (each ship should be
                represented by its __repr__ string).
        """
        pass

    def play(self):
        """
        The main driver of the Game. Manages the game until completion.
        :return: None
        """
        pass

    def _update_bombs_one_round(self):
        for bomb in self.__bombs:
            bomb.countdown()
            if bomb.is_bomb_ended():
                self.__bombs.remove(bomb)

############################################################
# An example usage of the game
############################################################
if __name__=="__main__":
    game = Game(5, gh.initialize_ship_list(4, 2))
    game.play()
