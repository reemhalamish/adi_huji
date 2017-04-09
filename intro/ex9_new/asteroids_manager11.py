import const
from random import randint
import math
from asteroid import Asteroid


class AsteroidsManager:
    FAR_ENOUGH_POS_PXL = 40

    def __init__(self, calc_new_coord_func):
        self.__asteroids = []
        self.__calculate_new_coord_func = calc_new_coord_func
        self.__asteroids_removed_this_turn = []
        self.__asteroids_added_this_turn = []

    def generate_asteroid(self, other_pos, size, speed):
        new_asteroid = Asteroid(other_pos, speed, size)
        self.__asteroids.append(new_asteroid)
        return new_asteroid

    def generate_asteroid_far_from_ship(self, ship_pos, screen_limits_x, screen_limits_y):
        far_pos = AsteroidsManager._get_pos_far_from(ship_pos, screen_limits_x, screen_limits_y)
        speed = self._generate_rand_speed()
        new_asteroid = self.generate_asteroid(far_pos, Asteroid.BIGGEST_SIZE, speed)
        return new_asteroid

    @staticmethod
    def _get_pos_far_from(other_pos, screen_limits_x, screen_limits_y):
        """
        keep generating positions until reached a pos far enough from other_pos
        :param screen_limits_y: tuple (topmost, downmost)
        :param screen_limits_x: tuple (leftmost, rightmost)
        :param other_pos: the position to stay away from
        :return: a far enough position
        """
        while True:
            start_pos_x = randint(screen_limits_x[const.IDX_SCREEN_START], screen_limits_x[const.IDX_SCREEN_END])
            start_pos_y = randint(screen_limits_y[const.IDX_SCREEN_START], screen_limits_y[const.IDX_SCREEN_END])
            delta_x = start_pos_x - other_pos[const.IDX_X_AXIS]
            delta_y = start_pos_y - other_pos[const.IDX_Y_AXIS]
            if math.sqrt(delta_x * delta_x + delta_y * delta_y) >= AsteroidsManager.FAR_ENOUGH_POS_PXL:
                return start_pos_x, start_pos_y

    def init_turn(self):
        self.__asteroids_removed_this_turn = []
        self.__asteroids_added_this_turn = []

    def move_asteroids(self):
        for asteroid in self.__asteroids:
            asteroid_new_pos = self.__calculate_new_coord_func(asteroid)
            asteroid.set_pos(asteroid_new_pos)

    def collide(self, other_pos):
        """
        checks for a collision with other stuff (i.e. ship or torpedo)
        :param other_pos: the position for collision
        :return: True if collided, False otherwise
        """
        for asteroid in self.__asteroids:
            if asteroid.is_a_collision(other_pos):
                '''
                collisoion detected.
                 if needed, generate children for asteroid
                 return true so outer functoin could know a collision happened
                 '''
                if not asteroid.is_smallest():
                    # todo generate smaller asteroid
                    # todo and then add them to the self.__asteroids_added_this_turn list
                    pass

            self.__asteroids.remove(asteroid)
            self.__asteroids_removed_this_turn.append(asteroid)
            return True

        # reached here? no collision!
        return False

    def get_asteroids_added_this_turn(self):
        return self.__asteroids_added_this_turn

    def get_asteroids_removed_this_turn(self):
        return self.__asteroids_removed_this_turn

    def get_all_asteroids_ready_for_draw(self):
        """
            packs the relevant info about the asteroids so that they can be drawn
        :return: a list, in which every object is a tuple (asteroid, asteroid_x_pos, asteroid_y_pos)
        """
        # todo return whatever the docstring says
        return []

    @staticmethod
    def _generate_rand_speed():
        # todo make it actually random!
        return 0.5, 0.5
