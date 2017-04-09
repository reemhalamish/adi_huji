from ship import *


def test_repr():
    ship1 = Ship((0, 0), 3, Direction.RIGHT, 5)
    assert Ship.__repr__(ship1) == "[[(0, 0), (1, 0), (2, 0)], [], '3', 5]"


def test_change_dir():
    ship1 = Ship((0, 0), 3, Direction.RIGHT, 5)
    ship1._change_direction()
    assert ship1.get_direction() == Direction.LEFT


def test_coordinates():
    ship1 = Ship((0, 0), 3, Direction.RIGHT, 5)
    assert ship1.coordinates() == [(0, 0), (1, 0), (2, 0)]


def test_move_step():
    ship1 = Ship((0, 0), 3, Direction.RIGHT, 5)
    ship1._move_one_step()
    assert ship1.coordinates() == [(1, 0), (2, 0), (3, 0)]

# print(Ship.coordinates(ship1))
# print(Ship.__contains__(ship1, (2,0)))
# Ship.hit(ship1, (1,0))
# Ship.hit(ship1, (0,0))
# Ship.hit(ship1, (2,0))
#
# print(Ship.direction(ship1))
# print(Ship.damaged_cells(ship1))
# print('********')
# print(Ship.terminated(ship1))
# Ship.change_direction(ship1)
# print(Ship.direction(ship1))
#
# print(ship1)

all_testers = [test_repr, test_change_dir, test_coordinates, test_move_step]

for tester in all_testers:
    print("Starting test " + tester.__name__)
    tester()
    print("Done! :)")

