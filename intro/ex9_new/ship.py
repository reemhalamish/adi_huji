import math
import const
from random import randint


class Ship:
    SHIP_ROT_DEG_LEFT = 2
    SHIP_ROT_DEG_RIGHT = - 2
    DIR_LEFT = 1
    DIR_RIGHT = 2
    DIR_NONE = 0
    SHIP_RADIUS = 1
    START_NUM_LIVES = 3
    ADD_SPEED_FACTOR = 0.05
    
    def __init__(self, start_pos_x, start_pos_y):
        self.__pos_x = start_pos_x
        self.__pos_y = start_pos_y
        self.__speed_x = 0
        self.__speed_y = 0
        self.__heading = 0  # in degrees
        self.__life = Ship.START_NUM_LIVES

    # def move_one_step(self, new_pos, dir_pressed):
    #     """
    #     Moves the ship a single step. move the ship to new pos, give it the
    #     new current speed and it's new heading.
    #     :param new_pos:
    #     :param new_speed: a tuple containing the new speed (new_speed_x, new_speed_y)
    #     :param dir_pressed: the dir - LEFT or RIGHT - the player last pressed
    #     :return:
    #     """
    #     # first, change ship's location
    #     self.__pos_x = new_pos[const.IDX_X_AXIS]
    #     self.__pos_y = new_pos[const.IDX_Y_AXIS]
    #
    #     # then change the ship's directions
    #     self.change_ship_dir(dir_pressed)

    def change_ship_dir(self, dir_pressed):
        """
        Changes the ship's heading by the dir_pressed given
        :param dir_pressed: the direction that the player pressed last
        :return:
        """
        if dir_pressed is Ship.DIR_LEFT:
            self.__heading += Ship.SHIP_ROT_DEG_LEFT
        elif dir_pressed is Ship.DIR_RIGHT:
            self.__heading += Ship.SHIP_ROT_DEG_RIGHT
            # else dir_pressed is none -  no direction were pressed, nothing changes

    def is_a_collision(self, pos):
        """
        return a bool whether there was a collision in a pos
        :param pos:
        :return:
        """
        delta_x = self.__pos_x - pos[const.IDX_X_AXIS]
        delta_y = self.__pos_y - pos[const.IDX_Y_AXIS]

        dist = math.sqrt(delta_x**2 + delta_y**2)

        return dist < Ship.SHIP_RADIUS

    def deduct_one_life(self):
        self.__life -= 1

    def get_life(self):
        return self.__life

    def get_pos(self):
        return self.__pos_x, self.__pos_y

    def get_speed(self):
        return self.__speed_x, self.__speed_y

    def get_heading(self):
        return self.__heading

    def get_params_for_draw(self):
        return self.__pos_x, self.__pos_y, self.__heading

    def get_radius(self):
        return Ship.SHIP_RADIUS

    def set_speed(self, new_speed):
        self.__speed_x = new_speed[const.IDX_X_AXIS]
        self.__speed_y = new_speed[const.IDX_Y_AXIS]

    def set_pos(self, new_pos):
        self.__pos_x = new_pos[const.IDX_X_AXIS]
        self.__pos_y = new_pos[const.IDX_Y_AXIS]



