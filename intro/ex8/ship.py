############################################################
# Helper class
############################################################


class Direction:
    """
    Class representing a direction in 2D world.
    You may not change the name of any of the constants (UP, DOWN, LEFT, RIGHT,
     NOT_MOVING, VERTICAL, HORIZONTAL, ALL_DIRECTIONS), but all other
     implementations are for you to carry out.
    """
    UP = '5'
    DOWN = '2'
    LEFT = '1'
    RIGHT = '3'

    NOT_MOVING = '0'

    VERTICAL = (UP, DOWN)
    HORIZONTAL = (LEFT, RIGHT)

    ALL_DIRECTIONS = (UP, DOWN, LEFT, RIGHT)

############################################################
# Class definition
############################################################


class Ship:
    """
    A class representing a ship in Battleship game.
    A ship is 1-dimensional object that could be laid in either horizontal or
    vertical alignment. A ship sails on its vertical\horizontal axis back and
    forth until reaching the board's boarders and then changes its direction to
    the opposite (left <--> right, up <--> down).
    If a ship is hit in one of its coordinates, it ceases its movement in all
    future turns.
    A ship that had all her coordinates hit is considered terminated.
    """
    IDX_X_POS = 0
    IDX_Y_POS = 1
    START_BOARD = 0
    IDX_LAST_CORD = -1

    def __init__(self, pos, length, direction, board_size):
        """
        A constructor for a Ship object
        :param pos: A tuple representing The ship's head's (x, y) position
        :param length: Ship's length
        :param direction: Initial direction in which the ship is sailing
        :param board_size: Board size in which the ship is sailing
        """
        self.__pos = pos
        self.__length = length
        self.__direction = direction
        self.__board_size = board_size
        self.__damaged_cells = []
        self.__is_hit = False

    def __repr__(self):
        """
        Return a string representation of the ship.
        :return: A tuple converted to string (that is, for a tuple x return
            str(x)).
        The tuple's content should be (in the exact following order):
            1. A list of all the ship's coordinates.
            2. A list of all the ship's hit coordinates.
            3. Last sailing direction.
            4. The size of the board in which the ship is located.
        """
        str_repr = str(([self.coordinates(),
                         self.__damaged_cells,
                         # ship_helper.direction_repr_str(Direction, self.__direction),
                         self.__direction,
                         self.__board_size]))
        return str_repr

    def move(self):
        """
        Make the ship move one board unit.
        Movement is in the current sailing direction, unless such movement would
        take the ship outside of the board, in which case the ship switches
        direction and sails one board unit in the new direction.
        :return: A direction object representing the current movement direction.
        """
        if self.__direction == Direction.NOT_MOVING:
            return
        if self._is_at_end_of_board():
            self._change_direction()
        # now make actual move. ship moves one step forward
        self._move_one_step()

    def hit(self, pos):
        """
        Inform the ship that a bomb hit a specific coordinate. The ship updates
         its state accordingly.
        If one of the ship's body's coordinate is hit, the ship does not move
         in future turns. If all ship's body's coordinate are hit, the ship is
         terminated and removed from the board.
        :param pos: A tuple representing the (x, y) position of the hit.
        :return: True if the bomb generated a new hit in the ship, False
         otherwise.
        """
        if Ship.__contains__(self, pos):
            if pos not in self.__damaged_cells:
                self.__damaged_cells.append(pos)
                self.__direction = Direction.NOT_MOVING
                self.__is_hit = True
                return True
            return False
        return False

    def terminated(self):
        """
        :return: True if all ship's coordinates were hit in previous turns, False
        otherwise.
        """
        if len(self.__damaged_cells) == self.__length:
            return True
        return False

    def __contains__(self, pos):
        """
        Check whether the ship is found in a specific coordinate.
        :param pos: A tuple representing the coordinate for check.
        :return: True if one of the ship's coordinates is found in the given
        (x, y) coordinate, False otherwise.
        """
        coordinates = Ship.coordinates(self)
        if pos in coordinates:
            return True
        return False

    def coordinates(self):
        """
        Return ship's current coordinates on board.
        :return: A list of (x, y) tuples representing the ship's current
        occupying coordinates.
        """
        ship_coordinates = []
        for i in range(self.__length):
            if self.__direction in Direction.VERTICAL:
                ship_coordinates.append((self.__pos[0], self.__pos[1] + i))
            elif self.__direction in Direction.HORIZONTAL:
                ship_coordinates.append((self.__pos[0] + i, self.__pos[1]))
        return ship_coordinates

    def damaged_cells(self):
        """
        Return the ship's hit positions.
        :return: A list of tuples representing the (x, y) coordinates of the
         ship which were hit in past turns (If there are no hit coordinates,
         return an empty list). There is no importance to the order of the
         values in the returned list.
        """
        return self.__damaged_cells

    def direction(self):
        """
        Return the ship's current sailing direction.
        :return: One of the constants of Direction class :
         [UP, DOWN, LEFT, RIGHT] according to current sailing direction or
         NOT_MOVING if the ship is hit and not moving.
        """
        # if damaged cells is not an empty list - ship is hit
        if Ship.damaged_cells(self):
            return Direction.NOT_MOVING
        return self.__direction

    def _change_direction(self):
        """
        Changes the ship's direction
        :return: the opposite of the ship's current direction
        """
        if self.__direction == Direction.UP:
            self.__direction = Direction.DOWN
        elif self.__direction == Direction.DOWN:
            self.__direction = Direction.UP
        elif self.__direction == Direction.RIGHT:
            self.__direction = Direction.LEFT
        elif self.__direction == Direction.LEFT:
            self.__direction = Direction.RIGHT
        return self.__direction

    def cell_status(self, pos):
        """
        Return the status of the given coordinate (hit\not hit) in current ship.
        :param pos: A tuple representing the coordinate to query.
        :return:
            if the given coordinate is not hit : False
            if the given coordinate is hit : True
            if the coordinate is not part of the ship's body : None 
        """
        ship_coordinates = Ship.coordinates(self)
        if pos in self.__damaged_cells:
            return True  # return True if coordinate is hit
        elif pos in ship_coordinates:
            return False  # return False if coordinate not hit
        return None  # return None if coordinate not in ship's body

    def _is_at_end_of_board(self):
        """
        boolean, check if is at end of board
        :return:
        """
        if self.__direction == Direction.LEFT:
            return self.__pos[Ship.IDX_X_POS] == Ship.START_BOARD
        if self.__direction == Direction.RIGHT:
            return self.coordinates()[Ship.IDX_LAST_CORD][Ship.IDX_X_POS] == self.__board_size - 1
        if self.__direction == Direction.UP:
            return self.__pos[Ship.IDX_Y_POS] == Ship.START_BOARD
        if self.__direction == Direction.DOWN:
            return self.coordinates()[Ship.IDX_LAST_CORD][Ship.IDX_Y_POS] == self.__board_size - 1

    def _move_one_step(self):
        if self.__direction == Direction.LEFT:
            self.__pos = (self.__pos[Ship.IDX_X_POS] - 1, self.__pos[Ship.IDX_Y_POS])
        elif self.__direction == Direction.RIGHT:
            self.__pos = (self.__pos[Ship.IDX_X_POS] + 1, self.__pos[Ship.IDX_Y_POS])
        elif self.__direction == Direction.UP:
            self.__pos = (self.__pos[Ship.IDX_X_POS], self.__pos[Ship.IDX_Y_POS] - 1)
        else:  # self.__direction == Direction.DOWN:
            self.__pos = (self.__pos[Ship.IDX_X_POS], self.__pos[Ship.IDX_Y_POS] + 1)

    def get_direction(self):
        return self.__direction

    def get_pos(self):
        return self.__pos

    def is_hit(self):
        return self.is_hit()