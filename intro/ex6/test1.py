import sys
import mosaic
import ex6

BLACK_PIXEL = (0, 0, 0)
WHITE_PIXEL = (255, 255, 255)
RED_PIXEL = (255, 0, 0)

BLACK_IMG_2X2 = [[BLACK_PIXEL, BLACK_PIXEL], [BLACK_PIXEL, BLACK_PIXEL]]
BLACK_IMG_1H_X_2W = [[BLACK_PIXEL, BLACK_PIXEL]]
BLACK_IMG_1X1 = [[BLACK_PIXEL]]
WHITE_IMG_4X4 = [[WHITE_PIXEL, WHITE_PIXEL, WHITE_PIXEL, WHITE_PIXEL],
                 [WHITE_PIXEL, WHITE_PIXEL, WHITE_PIXEL, WHITE_PIXEL],
                 [WHITE_PIXEL, WHITE_PIXEL, WHITE_PIXEL, WHITE_PIXEL],
                 [WHITE_PIXEL, WHITE_PIXEL, WHITE_PIXEL, WHITE_PIXEL]]

WHITE_IMG_2X2 = [[WHITE_PIXEL, WHITE_PIXEL], [WHITE_PIXEL, WHITE_PIXEL]]
WHITE_RED_MIXED_IMG_2X2 = [[WHITE_PIXEL, RED_PIXEL], [RED_PIXEL, WHITE_PIXEL]]
WHITE_RED_MIXED_FLIPPED_IMG_2X2 = [[RED_PIXEL, WHITE_PIXEL],[WHITE_PIXEL, RED_PIXEL]]
DIFF_PIXEL_1_CHANNEL = 255
DIFF_PIXEL_3_CHANNEL = 3 * DIFF_PIXEL_1_CHANNEL
FULL_1_CHANNEL = 255
SIZE_1H_X_2W = (1, 2)
POINT_TOP_LEFT = (0, 0)


# def test1():
#     img = mosaic.load_image('im1.jpg')
#     upper_left = (0, 0)
#     size = (2000, 2000)
#     piece = ex6.get_piece(img, upper_left, size)
#     mosaic.show(piece)
#
#
# def test2():
#     img = mosaic.load_image('im1.jpg')
#     im2 = mosaic.load_image('im2_test.jpg')
#     piece = ex6.get_piece(im2, (0, 0), (100, 100))
#     ex6.set_piece(img, (500, 500), piece)
#     mosaic.show(img)


def test_compare_pixel():
    assert ex6.compare_pixel(BLACK_PIXEL, WHITE_PIXEL) == DIFF_PIXEL_3_CHANNEL
    assert ex6.compare_pixel(BLACK_PIXEL, RED_PIXEL) == DIFF_PIXEL_1_CHANNEL


def test_compare():
    assert ex6.compare(BLACK_IMG_2X2, WHITE_IMG_2X2) == 2 * 2 * DIFF_PIXEL_3_CHANNEL


def test_get_piece():
    assert ex6.get_piece(BLACK_IMG_2X2, POINT_TOP_LEFT, SIZE_1H_X_2W) == BLACK_IMG_1H_X_2W


def test_set_piece():
    white_img = ex6.deepcopy(WHITE_IMG_2X2)
    ex6.set_piece(white_img, POINT_TOP_LEFT, BLACK_IMG_1H_X_2W)

    # upper_left is in form (y, x)
    ex6.set_piece(white_img, (1, 0), BLACK_IMG_1X1)

    # it's okay to get out of borders
    ex6.set_piece(white_img, (1, 1), BLACK_IMG_1H_X_2W)

    assert white_img == BLACK_IMG_2X2


def test_average():
    img = \
        [
            [(1, 1, 1), (2, 2, 2)],
            [(3, 3, 3), (4, 4, 4)],
            [(2, 2, 2), (2, 2, 2)]
        ]
    assert ex6.average(img) == (14 / 6, 14 / 6, 14 / 6)

    img = ex6.deepcopy(WHITE_IMG_2X2)
    img[1][1] = RED_PIXEL
    assert ex6.average(img) == (FULL_1_CHANNEL, 3 / 4 * FULL_1_CHANNEL, 3 / 4 * FULL_1_CHANNEL)


# noinspection SpellCheckingInspection
def test_preprocess_tiles():
    assert ex6.preprocess_tiles([BLACK_IMG_2X2, WHITE_IMG_2X2]) == [BLACK_PIXEL, WHITE_PIXEL]
    assert ex6.preprocess_tiles([WHITE_RED_MIXED_IMG_2X2]) == [
        (FULL_1_CHANNEL,
         1 / 2 * FULL_1_CHANNEL,
         1 / 2 * FULL_1_CHANNEL)
    ]


def test_get_best_tiles():
    white_img_4x4 = ex6.deepcopy(WHITE_IMG_4X4)
    tiles = [BLACK_IMG_2X2, WHITE_IMG_2X2, WHITE_RED_MIXED_IMG_2X2]
    assert ex6.get_best_tiles(white_img_4x4, tiles, [ex6.average(tile) for tile in tiles], 1) == [WHITE_IMG_2X2]

    for i in range(4):
        white_img_4x4[0][i] = RED_PIXEL
        white_img_4x4[3][i] = RED_PIXEL
    white_img_4x4[0][0] = WHITE_PIXEL

    assert ex6.get_best_tiles(white_img_4x4,
                              tiles,
                              [ex6.average(tile) for tile in tiles],
                              1
                              ) == [WHITE_RED_MIXED_IMG_2X2]

    assert ex6.get_best_tiles(white_img_4x4,
                              tiles,
                              [ex6.average(tile) for tile in tiles],
                              2
                              ) == [WHITE_RED_MIXED_IMG_2X2, WHITE_IMG_2X2]  # match by order, closest first


def test_choose_tile():
    assert ex6.choose_tile(WHITE_RED_MIXED_IMG_2X2,
                           [WHITE_IMG_2X2, WHITE_RED_MIXED_IMG_2X2,
                            WHITE_RED_MIXED_FLIPPED_IMG_2X2
                            ]
                           ) == WHITE_RED_MIXED_IMG_2X2


def run_tests():
    for test in [
        test_compare_pixel,
        test_compare,
        test_get_piece,
        test_set_piece,
        test_average,
        test_preprocess_tiles,
        test_get_best_tiles,
        test_choose_tile,


    ]:
        print("testing {}".format(test.__name__), end='... \t')
        sys.stdout.flush()
        test()
        print("done :)")


run_tests()
