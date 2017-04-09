import const


class Torpedo:
    TORPEDO_RADIUS = 4
    TORPEDO_LIFE_TIME_MAX = 200

    def __init__(self, ship, speed):
        self.__pos_x = ship.get_pos()[const.IDX_X_AXIS]
        self.__pos_y = ship.get_pos()[const.IDX_Y_AXIS]
        self.__heading = ship.get_heading()
        self.__speed_x = speed[const.IDX_X_AXIS]
        self.__speed_y = speed[const.IDX_Y_AXIS]
        self.__life_time = 0

    def increment_life_time(self):
        self.__life_time += 1

    def is_life_time_ended(self):
        return self.__life_time == Torpedo.TORPEDO_LIFE_TIME_MAX

    def get_speed(self):
        return self.__speed_x, self.__speed_y

    def get_radius(self):
        return self.TORPEDO_RADIUS

    def get_heading(self):
        return self.__heading

    def get_pos(self):
        return self.__pos_x, self.__pos_y

    def set_pos(self, new_pos):
        self.__pos_x = new_pos[const.IDX_X_AXIS]
        self.__pos_y = new_pos[const.IDX_Y_AXIS]