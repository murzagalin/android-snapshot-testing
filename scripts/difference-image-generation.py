#!/usr/bin/python3

# This script calculates image differences
# Example of usage:
# `python screenshot-diff-image-generation.py path/to/older/images path/to/newer/images path/to/output/directory`

# The script works only with png images.
# To calculate the differences, the script associates images based on their names.
# Example: if there are images with a name "image_to_diff.png" in both input directories, it will
# generate an image "image_to_diff.png" in the output directory with the difference

# The algorithm of diffing is based on ImageChops.difference() method.
# It calculates the difference and then creates a mask with pixel
# values greater than DIFF_PIXEL_THRESHOLD to filter irrelevant changes.

import sys
from os import listdir
from pathlib import Path
from PIL import Image, ImageChops

# We use DIFF_PIXEL_THRESHOLD instead of just 0 to ignore very small changes
# that inevitably happen in rounded corners. The value is chosen experimentally
DIFF_PIXEL_THRESHOLD = 10

def get_difference_mask(image1, image2):
    diff = ImageChops.difference(image1.convert("RGB"), image2.convert("RGB"))
    diff = diff.convert(mode='L')

    return diff.point(lambda p: p > DIFF_PIXEL_THRESHOLD and 255)

def create_dir(path):
    Path(path).mkdir(parents=True, exist_ok=True)

def save_diffs(images, old_files_dir, new_files_dir, diff_dir):
    diffs = {}
    for image in images:
        old_image = Image.open(old_files_dir + image)
        new_image = Image.open(new_files_dir + image)
        diffs[image] = get_difference_mask(old_image, new_image)

    if diffs:
        create_dir(diff_dir)

    for image, diff_image in diffs.items():
        if diff_image.getbbox():
            diff_image.save(diff_dir + image)

if __name__ == "__main__":
    old_files_dir = sys.argv[1]
    new_files_dir = sys.argv[2]
    diff_dir = sys.argv[3]
    create_dir(diff_dir)

    old_image_files = set([f for f in listdir(old_files_dir) if f.endswith(".png")])
    new_image_files = set([f for f in listdir(new_files_dir) if f.endswith(".png")])

    images_to_diff = old_image_files.intersection(new_image_files)
    save_diffs(images_to_diff, old_files_dir, new_files_dir, diff_dir)
