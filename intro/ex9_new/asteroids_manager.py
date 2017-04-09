import math
import const
from random import randint
from asteroid import Asteroid


class AsteroidsManager:
    FAR_ENOUGH_POS_PXL = 40
    LOW_SPEED_LIM = -2
    HIGH_SPEED_LIM = 2

    def __init__(self, calc_new_coord_func):
        self.__asteroids = []
        self.__calculate_new_coord_func = calc_new_coord_func
        self.__asteroids_added_this_turn = []
        self.__asteroids_removed_this_turn = []

    def generate_asteroid(self, pos, speed, size):
        """

        :param pos:
        :param speed:
        :param size:
        :return:
        """
        new_asteroid = Asteroid(pos, speed, size)
        self.__asteroids.append(new_asteroid)
        self.__asteroids_added_this_turn.append(new_asteroid)
        return new_asteroid

    def generate_asteroid_far_from_ship(self, ship_pos, screen_limits_x, screen_limits_y):
        """

        :param ship_pos:
        :param screen_limits_x:
        :param screen_limits_y:
        :return:
        """
        start_asteroid_pos = self._get_pos_far_from(ship_pos, screen_limits_x, screen_limits_y)
        start_speed = self._generate_rand_speed()
        new_asteroid = self.generate_asteroid(start_asteroid_pos, start_speed, Asteroid.BIGGEST_SIZE)
        return new_asteroid

    def _generate_rand_speed(self):
        """
        :return: a randomly generated speed for new asteroid
        """
        rand_start_speed_x = randint(AsteroidsManager.LOW_SPEED_LIM, AsteroidsManager.HIGH_SPEED_LIM)
        rand_start_speed_y = randint(self.LOW_SPEED_LIM, self.HIGH_SPEED_LIM)
        return rand_start_speed_x, rand_start_speed_y

    @staticmethod
    def _get_pos_far_from(other_pos, screen_limits_x, screen_limits_y):
        """
        keeps generating a position until it is far enough from given position
        :param screen_limits_x: tuple (top screen limit, bottom screen limit)
        :param screen_limits_y: tuple (left screen limit, right screen limit)
        :return: the generated pos far from given pos
        """
        while True:
            start_pos_x = randint(screen_limits_x[const.IDX_SCREEN_START],
                                  screen_limits_x[const.IDX_SCREEN_END])
            start_pos_y = randint(screen_limits_y[const.IDX_SCREEN_START],
                                  screen_limits_y[const.IDX_SCREEN_END])

            delta_x = start_pos_x - other_pos[const.IDX_X_AXIS]
            delta_y = start_pos_y - other_pos[const.IDX_Y_AXIS]
            if math.sqrt(delta_x ** 2 + delta_y ** 2) >= \
                    AsteroidsManager.FAR_ENOUGH_POS_PXL:
                return start_pos_x, start_pos_y

    def init_turn(self):
        """
        Initializes a turn by emptying the lists of added and removed asteroids
        of each turn.
        """
        self.__asteroids_added_this_turn = []
        self.__asteroids_removed_this_turn = []

    def move_asteroids(self):
        """
        Moves all asteroids in cur lst of asteroids. for each calculates it's
        new pos and sets it in asteroid. Asteroids can't change speed or dir.
        """
        for asteroid in self.__asteroids:
            asteroid_new_pos = self.__calculate_new_coord_func(asteroid)
            asteroid.set_pos(asteroid_new_pos)

    def collide_with_ship(self, ship):
        """
        Checks for a collision with the ship. In case of a collision takes care
        of removing asteroid from game.
        :param ship:
        :return: True if collided, False otherwise
        """
        for asteroid in self.__asteroids:
            if asteroid.is_a_collision(ship):
                self.__asteroids_removed_this_turn.append(asteroid)
                self.__asteroids.remove(asteroid)
                return True
        return False


        # for asteroid in self.__asteroids:
        #     if asteroid.is_a_collision(other_obj):
        #         '''
        #         A collision was detected. If needed, new asteroids will be
        #         generated instead. Returns True so we know there was a collision
        #         '''
        #         if not asteroid.is_smallest():
        #             old_asteroid_pos = asteroid.get_pos()
        #             new_speed = self.calculate_speed_for_new_asteroid(torp_speed)
        #             for i in range(asteroid.get_size() - 1):
        #                 new_asteroid = self.generate_asteroid(old_asteroid_pos,
        #                                                       new_speed,
        #                                                       asteroid.get_size - 1)
        #                 self.__asteroids_added_this_turn.append(new_asteroid)
        #     self.__asteroids_removed_this_turn.append(asteroid)
        #     self.__asteroids.remove(asteroid)
        #     return True
        #
        # # reached here? no collision!
        # return False

    def collide_with_torpedoes(self, list_of_torpedoes):
        """
        Checks for any collision between all asteroids and all torpedoes on
        board.
        :param list_of_torpedoes: a list of torpedoes currently on board.
        :return: a set of all the torpedoes that hit an asteroid this turn,
        so they can be taken care of outside of asteroid_manager.
        """
        torpedoes_done = set()
        for asteroid in self.__asteroids:
            for torpedo in list_of_torpedoes:
                if torpedo in torpedoes_done:
                    continue
                if asteroid.is_a_collision(torpedo):
                    torpedoes_done.add(torpedo)
                    self._asteroid_collided_with_torpedo(asteroid,
                                                         torpedo.get_speed())
        return torpedoes_done

    def _asteroid_collided_with_torpedo(self, asteroid, torpedo_speed):
        """
        Takes care of asteroids in case of collision with torpedo.
        Generates new asteroids instead if needed,
        and remove and unregisters old asteroid.
        :param asteroid:
        :param torpedo_speed:
        :return:
        """
        if asteroid.get_size() > Asteroid.SMALLEST_SIZE:
            # first create two new asteroids instead
            asteroid_pos = asteroid.get_pos()
            asteroid_speed = asteroid.get_speed()
            new_speed_asteroid_1 = self.calculate_speed_for_new_asteroid(
                asteroid_speed, torpedo_speed)
            new_speed_asteroid_2 = self.calculate_speed_for_new_asteroid(
                asteroid_speed, torpedo_speed, flip_dir=True)
            self.generate_asteroid(asteroid_pos,
                                   new_speed_asteroid_1,
                                   asteroid.get_size() - 1)
            self.generate_asteroid(asteroid_pos,
                                   new_speed_asteroid_2,
                                   asteroid.get_size() - 1)
        self.__asteroids_removed_this_turn.append(asteroid)
        self.__asteroids.remove(asteroid)

    @staticmethod
    def calculate_speed_for_new_asteroid(asteroid_speed, torpedo_speed, flip_dir=False):
        """
        Calculates the speed for the new asteroid by the given formula.
        :param asteroid_speed:
        :param torpedo_speed: the speed of torpedo that hit th old asteroid
        :return: speed for new asteroid        """
        cur_speed_x = asteroid_speed[const.IDX_X_AXIS]
        cur_speed_y = asteroid_speed[const.IDX_Y_AXIS]
        torpedo_speed_x = torpedo_speed[const.IDX_X_AXIS]
        torpedo_speed_y = torpedo_speed[const.IDX_Y_AXIS]

        new_speed_x = (torpedo_speed_x + cur_speed_x) / \
                      math.sqrt(torpedo_speed_x ** 2 + cur_speed_x ** 2)
        new_speed_y = (torpedo_speed_y + cur_speed_y) / \
                      math.sqrt(torpedo_speed_y ** 2 + cur_speed_y ** 2)
        if flip_dir:
            return -new_speed_x, -new_speed_y
        return new_speed_x, new_speed_y

    def get_all_asteroids_ready_to_draw(self):
        lst_of_asteroids_info = []
        for asteroid in self.__asteroids:
            asteroid_info = (asteroid,) + (asteroid.get_pos())
            lst_of_asteroids_info.append(asteroid_info)
        return lst_of_asteroids_info

    def is_asteroids_left(self):
        return len(self.__asteroids)

    def get_asteroids_removed_this_turn(self):
        return self.__asteroids_removed_this_turn

    def get_asteroids_added_this_turn(self):
        return self.__asteroids_added_this_turn
