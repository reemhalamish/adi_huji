import math
import const


class Asteroid:
    SIZE_TO_PXL_SIZE_MULTIPLIER = 10
    BIGGEST_SIZE = 3
    SMALLEST_SIZE = 1
    SPEED_COEFFICIENT = 10
    SPEED_NORMALIZATION_FACTOR = 5

    def __init__(self, pos, speed, size):
        self.__pos_x = pos[const.IDX_X_AXIS]
        self.__pos_y = pos[const.IDX_Y_AXIS]
        self.__speed_x = speed[const.IDX_X_AXIS]
        self.__speed_y = speed[const.IDX_Y_AXIS]
        self.__size = size

    # def is_a_collision(self, pos):
    #     """
    #     return a bool whether there was a collision in a pos
    #     :param pos:
    #     :return:
    #     """
    #     delta_x = self.__pos_x - pos[const.IDX_X_AXIS]
    #     delta_y = self.__pos_y - pos[const.IDX_Y_AXIS]
    #
    #     dist = math.sqrt(delta_x ** 2 + delta_y ** 2)
    #
    #     return dist <= (self.__size * Asteroid.SIZE_TO_PXL_SIZE_MULTIPLIER)

    def is_a_collision(self, obj):
        """
        returns a bool whether an object collided with an asteroid.
        :param obj:
        :return:
        """
        obj_pos = obj.get_pos()
        delta_x = self.__pos_x - obj_pos[const.IDX_X_AXIS]
        delta_y = self.__pos_y - obj_pos[const.IDX_Y_AXIS]
        dist = math.sqrt(delta_x ** 2 + delta_y ** 2)

        return dist <= (self.get_radius() + obj.get_radius())

    def get_pos(self):
        return self.__pos_x, self.__pos_y

    def get_speed(self):
        return self.__speed_x, self.__speed_y

    def get_size(self):
        return self.__size

    def get_radius(self):
        return self.__size*Asteroid.SPEED_COEFFICIENT - Asteroid.SPEED_NORMALIZATION_FACTOR

    def get_pxl_size(self):
        return self.__size * Asteroid.SIZE_TO_PXL_SIZE_MULTIPLIER

    def set_pos(self, new_pos):
        self.__pos_x, self.__pos_y = new_pos

    def is_smallest(self):
        return self.__size == Asteroid.SMALLEST_SIZE
