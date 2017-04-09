from screen import Screen
import sys
import ship as sf  # sf stands for Ship File
import asteroid as af
from ship_manager import ShipManager
from asteroids_manager import AsteroidsManager
import const
from random import randint

DEFAULT_ASTEROIDS_NUM = 3


class GameRunner:

    def __init__(self, asteroids_amnt):
        self._screen = Screen()

        self.screen_max_x = Screen.SCREEN_MAX_X
        self.screen_max_y = Screen.SCREEN_MAX_Y
        self.screen_min_x = Screen.SCREEN_MIN_X
        self.screen_min_y = Screen.SCREEN_MIN_Y
        self.screen_size = (self.screen_max_x - self.screen_min_x,
                            self.screen_max_y - self.screen_min_y)
        # self.screen_size is a tuple (width, height)

        width, height = self.screen_size

        def calculate_new_coord(moving_thing):
            new_coord_x = (moving_thing.get_speed()[const.IDX_X_AXIS] +
                           moving_thing.get_pos()[
                               const.IDX_X_AXIS] - Screen.SCREEN_MIN_X) % \
                          width + self.screen_min_x
            new_coord_y = (moving_thing.get_speed()[const.IDX_Y_AXIS] +
                           moving_thing.get_pos()[
                               const.IDX_Y_AXIS] - Screen.SCREEN_MIN_Y) % \
                          height + self.screen_min_y
            return new_coord_x, new_coord_y

        screen_limits_x = (self.screen_min_x, self.screen_max_x)
        screen_limits_y = (self.screen_min_y, self.screen_max_y)

        ship_start_pos_x = randint(screen_limits_x[const.IDX_SCREEN_START],
                                   screen_limits_x[const.IDX_SCREEN_END])
        ship_start_pos_y = randint(screen_limits_y[const.IDX_SCREEN_START],
                                   screen_limits_y[const.IDX_SCREEN_END])
        ship_start_pos = (ship_start_pos_x, ship_start_pos_y)

        self.__ship = sf.Ship(ship_start_pos_x, ship_start_pos_y)
        self.__sm = ShipManager(self.__ship, calculate_new_coord)
        self.__am = AsteroidsManager(calculate_new_coord)

        for i in range(asteroids_amnt):
            new_asteroid = self \
                .__am \
                .generate_asteroid_far_from_ship(ship_start_pos,
                                                 screen_limits_x,
                                                 screen_limits_y)
            self._screen.register_asteroid(new_asteroid,
                                           new_asteroid.get_size())

    def run(self):
        self._do_loop()
        self._screen.start_screen()

    def _do_loop(self):
        # You don't need to change this method!
        self._game_loop()

        # Set the timer to go off again
        self._screen.update()
        self._screen.ontimer(self._do_loop, 5)

    def _ship_new_move_dir(self):
        """
        :return: the direction the player last pressed
        """
        is_left = self._screen.is_left_pressed()
        is_right = self._screen.is_right_pressed()
        # will return nothing if player pressed both direction in same time
        # or if player hasn't pressed a direction
        if is_left and is_right or (not is_left and not is_right):
            return sf.Ship.DIR_NONE
        elif is_left:
            return sf.Ship.DIR_LEFT
        return sf.Ship.DIR_RIGHT

    def _game_loop(self):
        """
        one game loop
        """

        self.__am.init_turn()
        self.__sm.init_turn_torpedo()
        self._update_ship_total()
        self.__am.move_asteroids()
        self.__sm.update_torpedoes()

        if self.__am.collide_with_ship(self.__ship):
            self._screen.remove_life()
            lives_left = self.__sm.deduct_one_life()
            if lives_left:
                self._screen.show_message('TITLE1', 'MSG1')
            else:
                self._screen.show_message('TITLE2', 'MSG2')
                self._screen.end_game()
                sys.exit()

        torpedoes = self.__sm.get_torpedoes()
        torpedoes_collided = self.__am.collide_with_torpedoes(torpedoes)
        for torpedo in torpedoes_collided:
            self.__sm.finish_torpedo(torpedo)

        if self._screen.is_space_pressed():
            self.__sm.fire_torpedo_if_allowed()

        if not self.__am.is_asteroids_left():
            self._screen.show_message('TITLE2', 'MSG2')
            self._screen.end_game()
            sys.exit()

        for new_fired_torpedo in self.__sm.get_new_fired_torpedoes():
            self._screen.register_torpedo(new_fired_torpedo)

        for asteroid in self.__am.get_asteroids_added_this_turn():
            self._screen.register_asteroid(asteroid, asteroid.get_size())

        for asteroid in self.__am.get_asteroids_removed_this_turn():
            self._screen.unregister_asteroid(asteroid)

        for done_torpedo in self.__sm.get_done_torpedoes():
            self._screen.unregister_torpedo(done_torpedo)

        for asteroid_info in self.__am.get_all_asteroids_ready_to_draw():
            self._screen.draw_asteroid(*asteroid_info)
            # for torpedo in new torpedoes fired register torpedo
        # the --> * <-- unloads the tuple to 3 params
        self._screen.draw_ship(*self.__sm.get_ship_params_for_draw())

        for torpedo_info in self.__sm.get_torpedoes_params_to_draw():
            self._screen.draw_torpedo(*torpedo_info)



    def _update_ship_total(self):
        dir_pressed = self._ship_new_move_dir()
        self.__sm.move_ship(dir_pressed)

        if self._screen.is_up_pressed():
            self.__sm.ship_calculate_new_speed()



def main(amnt):
    runner = GameRunner(amnt)
    runner.run()


if __name__ == "__main__":
    if len(sys.argv) > 1:
        main(int(sys.argv[1]))
    else:
        main(DEFAULT_ASTEROIDS_NUM)
