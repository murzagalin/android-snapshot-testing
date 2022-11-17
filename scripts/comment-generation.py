#!/usr/bin/python3

import sys

from os import listdir
import os

def diff(first, second):
    second = set(second)
    return [item for item in first if item not in second]

if __name__ == "__main__":
    old_files_dir = sys.argv[1]
    new_files_dir = sys.argv[2]
    diff_dir = sys.argv[3]
    commit_hash = sys.argv[4]

    old_image_files = [f for f in listdir(old_files_dir) if f.endswith(".png")]
    new_image_files = [f for f in listdir(new_files_dir) if f.endswith(".png")]
    diff_image_files = [f for f in listdir(diff_dir) if f.endswith(".png")]

    added_snapshots = diff(new_image_files, old_image_files)
    removed_snapshots = diff(old_image_files, new_image_files)
    intersection_snapshots = set(new_image_files).intersection(set(old_image_files))

    if added_snapshots:
        print("### New test cases:")
        for img in sorted(added_snapshots):
            file_name = os.path.splitext(img)[0]
            print("<details>")
            print(f'<summary>{file_name}</summary>')
            print("")
            new_image = f'https://github.com/deliveryhero/pd-android-fluid/blob/{commit_hash}/new/images/{img}?raw=true'
            print(f'![new-{img}]({new_image})')
            print("</details>")
        print("")

    if removed_snapshots:
        print("### Removed test cases:")
        for img in sorted(removed_snapshots):
            file_name = os.path.splitext(img)[0]
            print("<details>")
            print(f'<summary>{file_name}</summary>')
            print("")
            old_image = f'https://github.com/deliveryhero/pd-android-fluid/blob/{commit_hash}/old/images/{img}?raw=true'
            print(f'![old-{img}]({old_image})')
            print("</details>")
        print("")

    if diff_image_files:
        print(f'### {len(diff_image_files)} out of {len(intersection_snapshots)} test cases have visual differences:')
        for img in sorted(diff_image_files):
            file_name = os.path.splitext(img)[0]
            print("<details>")
            print(f'<summary>{file_name}</summary>')
            print("")
            print("| Old | New | Diff |")
            print("|-----|-----|-----|")
            old_image = f'https://github.com/deliveryhero/pd-android-fluid/blob/{commit_hash}/old/images/{img}?raw=true'
            new_image = f'https://github.com/deliveryhero/pd-android-fluid/blob/{commit_hash}/new/images/{img}?raw=true'
            diff_image = f'https://github.com/deliveryhero/pd-android-fluid/blob/{commit_hash}/diffs/{img}?raw=true'
            print(f'|![old-{img}]({old_image})|![new-{img}]({new_image})|![diff-{img}]({diff_image})|')
            print("</details>")

    if not diff_image_files and not removed_snapshots and not added_snapshots:
        print("No visual changes")