import math
import const
from torpedo import Torpedo

class ShipManager:
    TORPEDO_ACCELERATION_FACTOR = 2
    MAX_NUM_OF_TORPEDOES = 15

    def __init__(self, ship, calc_new_coord_func):
        self.__ship = ship
        # this function takes an object as an argument -->
        self.__calculate_new_coord_func = calc_new_coord_func
        self.__list_of_torpedoes = []
        self.__torpedoes_fired_this_turn = []
        self.__torpedoes_removed_this_turn = []

    def _ship_cur_heading_to_radians(self):
        heading = self.__ship.get_heading()
        # NOT AS MAGIC NUMBER IN PURPOSE. the number 180 is more important
        return (math.pi / 180) * heading

    def deduct_one_life(self):
        self.__ship.deduct_one_life()
        return self.__ship.get_life()

    def ship_calculate_new_speed(self):
        cur_heading_rad = self._ship_cur_heading_to_radians()
        new_speed_x = self.__ship.get_speed()[const.IDX_X_AXIS] + self.__ship.ADD_SPEED_FACTOR * math.cos(cur_heading_rad)
        new_speed_y = self.__ship.get_speed()[const.IDX_Y_AXIS] + self.__ship.ADD_SPEED_FACTOR * math.sin(cur_heading_rad)
        self.__ship.set_speed((new_speed_x, new_speed_y))

    def get_ship_params_for_draw(self):
        return self.__ship.get_params_for_draw()

    def move_ship(self, dir_pressed):
        new_pos = self.__calculate_new_coord_func(self.__ship)
        self.__ship.set_pos(new_pos)
        self.__ship.change_ship_dir(dir_pressed)

    def calculate_speed_for_torpedo(self):
        cur_heading_rad = self._ship_cur_heading_to_radians()
        speed_x = self.__ship.get_speed()[const.IDX_X_AXIS] + \
                  ShipManager.TORPEDO_ACCELERATION_FACTOR * math.cos(cur_heading_rad)
        speed_y = self.__ship.get_speed()[const.IDX_Y_AXIS] + \
                  ShipManager.TORPEDO_ACCELERATION_FACTOR * math.sin(cur_heading_rad)
        return speed_x, speed_y

    def init_turn_torpedo(self):
        self.__torpedoes_removed_this_turn = []
        self.__torpedoes_fired_this_turn = []

    def fire_torpedo_if_allowed(self):
        if len(self.__list_of_torpedoes) < ShipManager.MAX_NUM_OF_TORPEDOES:
            torpedo_speed = self.calculate_speed_for_torpedo()
            torpedo = Torpedo(self.__ship, torpedo_speed)
            self.__list_of_torpedoes.append(torpedo)
            self.__torpedoes_fired_this_turn.append(torpedo)

    def update_torpedoes(self):
        for torpedo in self.__list_of_torpedoes:
            new_pos = self.__calculate_new_coord_func(torpedo)
            torpedo.set_pos(new_pos)
            torpedo.increment_life_time()
            if torpedo.is_life_time_ended():
                self.finish_torpedo(torpedo)

    def finish_torpedo(self, torpedo):
        self.__torpedoes_removed_this_turn.append(torpedo)
        self.__list_of_torpedoes.remove(torpedo)

    def get_ship_pos(self):
        return self.__ship.get_pos()

    def get_torpedoes(self):
        return self.__list_of_torpedoes

    def get_done_torpedoes(self):
        return self.__torpedoes_removed_this_turn

    def get_new_fired_torpedoes(self):
        return self.__torpedoes_fired_this_turn

    def get_torpedoes_params_to_draw(self):
        torpedoes_info = []
        for torpedo in self.__list_of_torpedoes:
            torpedo_info = (torpedo,) + \
                           torpedo.get_pos() + \
                           (torpedo.get_heading(),)
            torpedoes_info.append(torpedo_info)
        return torpedoes_info
