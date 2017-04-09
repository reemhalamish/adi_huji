#############################################################
# FILE : ex6.py
# WRITER : Adi Brock , abrock , 321018962
# EXERCISE : intro2cs ex6 2016-2017
# DESCRIPTION:
#############################################################

import mosaic
import sys
from math import inf as INFINITY
from copy import deepcopy


IDX_RED = 0
IDX_GREEN = 1
IDX_BLUE = 2
IDX_HEIGHT = 0
IDX_WIDTH = 1
IDX_ROW = 0
IDX_COLUMN = 1
TOP_LEFT_PX = (0, 0)


def compare_pixel(pixel1, pixel2):
    """
    Gets 2 tuples that are the (R,G,B) values of 2 pixels. Returns a
    value that is the sum of the distances between the red, green, blue values
    of the 2 pixels
    """
    r1, r2 = pixel1[IDX_RED], pixel2[IDX_RED]
    g1, g2 = pixel1[IDX_GREEN], pixel2[IDX_GREEN]
    b1, b2 = pixel1[IDX_BLUE], pixel2[IDX_BLUE]
    dist_between_pixels = abs(r1 - r2) + abs(g1 - g2) + abs(b1 - b2)
    return dist_between_pixels


def compare(image1, image2):
    """
    Compares the distance between two images by summing the distances of all
    common pixels
    """
    height_img1 = len(image1)
    width_img1 = len(image1[IDX_ROW])
    height_img2 = len(image2)
    width_img2 = len(image2[IDX_ROW])

    dist_between_images = 0
    common_height_len = min(height_img1, height_img2)
    common_width_len = min(width_img1, width_img2)
    for i in range(common_height_len):
        for j in range(common_width_len):
            dist_between_images += compare_pixel(image1[i][j], image2[i][j])
    return dist_between_images


def get_piece(image, upper_left, size):
    """
    gets a piece of an image
    :param image: the whole image
    :param upper_left: a tuple represents point (x,y)
    :param size: a tuple represents size (w,h)
    :return: the piece, a list of lists
    """

    # first - take all the info
    image_height = len(image)
    image_width = len(image[IDX_ROW])
    fragment_height = size[IDX_HEIGHT]
    fragment_width = size[IDX_WIDTH]
    start_px_row = upper_left[IDX_ROW]
    start_px_column = upper_left[IDX_COLUMN]
    common_height = min(fragment_height, image_height - start_px_row)
    common_width = min(fragment_width, image_width - start_px_column)
    piece_from_img_rows_only = image[start_px_row: start_px_row + common_height]
    piece_from_img = [row[start_px_column: start_px_column + common_width]
                      for row in piece_from_img_rows_only]
    return piece_from_img


def set_piece(image, upper_left, piece):
    piece_height = len(piece)
    piece_width = len(piece[IDX_ROW])
    start_px_row = upper_left[IDX_ROW]
    start_px_column = upper_left[IDX_COLUMN]
    image_height = len(image)
    image_width = len(image[IDX_ROW])
    common_height = min(piece_height, image_height - start_px_row)
    common_width = min(piece_width, image_width - start_px_column)
    fitted_piece = get_piece(piece, TOP_LEFT_PX, (common_height, common_width))
    for i in range(start_px_row, start_px_row + common_height):
        image[i][start_px_column: start_px_column + common_width] = \
            fitted_piece[i - start_px_row]


def average(image):
    """
    This function gets an image (a list of lists) and returns a tuple
    containing the average color of all the pixels in the image
    """
    image_height = len(image)
    image_width = len(image[IDX_ROW])
    num_of_pixels = image_width*image_height
    # a list that will become a tuple of the average color of all pixels
    # in the image
    average_of_px = [0, 0, 0]
    for i in range(image_height):
        for j in range(image_width):
            # iterates through all pixels in the image and adds the
            # value of each color to the correct index in average_of_px
            average_of_px[IDX_RED] += image[i][j][IDX_RED]
            average_of_px[IDX_GREEN] += image[i][j][IDX_GREEN]
            average_of_px[IDX_BLUE] += image[i][j][IDX_BLUE]
    for i in range(len(average_of_px)):
        average_of_px[i] /= num_of_pixels
    return tuple(average_of_px)


def preprocess_tiles(tiles):
    """
    Gets a list of tiles (images) and returns a list of tuples
    that are the average color of each image in same order of
    images in given list. (Tuple in index i is the average color
    of the pixels in image in index i).
    """
    averages = [average(tile) for tile in tiles]
    return averages


def get_best_tiles(objective, tiles, averages, num_candidates):
    """

    :param objective: a piece of the image we want to replace
    :param tiles: a list of images to replace the piece
    :param averages: a list, corresponding to tiles,
                     contains the pixel average for every tile
    :param num_candidates: No. of images to be returned
    :return: a list (with len() == num_candidates) containing tiles,
             the best matches for the piece, by color.
    """
    best_tiles = []
    color_of_objective = average(objective)
    distances_between_images = [compare_pixel(a, color_of_objective)
                                for a in averages]
    for i in range(num_candidates):
        min_avrg = INFINITY
        min_avrg_idx = -1
        for j in range(len(distances_between_images)):
            cur_value = distances_between_images[j]
            if cur_value < min_avrg:
                min_avrg = cur_value
                min_avrg_idx = j
        best_tiles.append(tiles[min_avrg_idx])
        distances_between_images[min_avrg_idx] = INFINITY
    return best_tiles


def choose_tile(piece, tiles):
    dist_between_images = [compare(piece, tile) for tile in tiles]
    idx_best_tile = dist_between_images.index(min(dist_between_images))
    return tiles[idx_best_tile]


# noinspection PyShadowingNames
def make_mosaic(image, tiles, num_candidates):
    tile_height = len(tiles[0])  # find height of a tile. all tiles are same size
    tile_width = len(tiles[0][IDX_ROW])
    averages = preprocess_tiles(tiles)
    rows = len(image)
    cols = len(image[0])
    for row_index in range(int(rows/tile_height)):
        for col_index in range(int(cols/tile_width)):
            # piece_full_rows = deepcopy_image[row_index * tile_height: (row_index+1) * tile_height]
            # piece = [row[col_index * tile_width: (col_index+1) * tile_width] for row in piece_full_rows]
            piece = get_piece(image,
                              (row_index*tile_height, col_index*tile_width),
                              (tile_height, tile_width))
            best_tiles = get_best_tiles(piece, tiles, averages, num_candidates)
            mosaic_piece = choose_tile(piece, best_tiles)
            set_piece(image,
                      (row_index*tile_height, col_index*tile_width),
                      mosaic_piece)
    return image


# noinspection PyShadowingNames
def main(image_source, images_dir, tile_height, num_candidates):
    image_input = mosaic.load_image(image_source)
    tiles = mosaic.build_tile_base(images_dir, tile_height)
    image_mosaic = make_mosaic(image_input, tiles, num_candidates)
    mosaic.save(image_mosaic, output)

if __name__ == "__main__":
    if len(sys.argv) == 6:
        script_name = sys.argv[0]
        image_source = sys.argv[1]
        images_dir = sys.argv[2]
        output = sys.argv[3]
        tile_height = int(sys.argv[4])
        num_candidates = int(sys.argv[5])
        main(image_source, images_dir, tile_height, num_candidates)
    else:
        print("Wrong number of parameters. The correct usage is: \nex6.py " +
              "<image_source> <images_dir> <output_name> <tile_height> " +
              "<num_candidates>")