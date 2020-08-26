import time

from PIL import Image
from ppadb.client import Client as AdbClient
import numpy as np

client = AdbClient(host="127.0.0.1", port=5037)
devices = client.devices()

if len(devices) == 0:
    print("Connect a device")
    quit()

device = devices[0]
while True:
    image = device.screencap()
    with open("screen.png", "wb") as fp:
        fp.write(image)
    image = Image.open("screen.png")
    image = np.array(image, dtype=np.uint8)
    pixels = [list(i[:3]) for i in image[1783]]
    # print(pixels)
    transition = []
    ignore = True
    black = True
    for i, pixel in enumerate(pixels):
        r, g, b = [(int(i)) for i in pixel]

        if ignore and (r + g + b) != 0:
            continue
        ignore = False

        if black and (r + g + b) != 0:
            print(f"1st black value:{black}")
            black = not black
            print(f"2nd black value:{black}")
            transition.append(i)
            continue
        if not black and (r + g + b) == 0:
            print(f"1st if black value:{black}")
            black = not black
            print(f"2nd if black value:{black}")
            transition.append(i)
            continue
    print(transition)
    try:
        start, target1, target2 = transition
        gap = target1 - start
        target = target2 - target1
        distance = (gap + target / 2) * .965
        print(distance)
    except:
        print("Error")
        print(distance)
        time.sleep(2.8)
    print(int(distance))
    device.shell(f'input touchscreen swipe 200 200 200 200 {int(distance)}')
    time.sleep(2.6)
