import cv2
import time
import os
from Handtracking import HandtrackingModule as htm

cap = cv2.VideoCapture(0)

folderPath = "D:\Projects\Python\PycharmProjects\Handtracking\Fingers"
myList = os.listdir(folderPath)

overlay = []

for impath in myList:
    image = cv2.imread(f'{folderPath}/{impath}')
    overlay.append(image)

ptime = 0

detector = htm.handDetector(detectionCon=0.75)

tipIds = [4, 8, 12, 16, 20]

while True:
    success, img = cap.read()
    img = detector.findHands(img)
    lmList = detector.findPosition(img, draw=False)
    if len(lmList) != 0:
        fingers = []

        if lmList[tipIds[0]][1] > lmList[tipIds[0] - 1][1]:
            fingers.append(1)
        else:
            fingers.append(0)
        for ids in range(1, 5):
            if lmList[tipIds[ids]][2] < lmList[tipIds[ids] - 2][2]:
                fingers.append(1)
            else:
                fingers.append(0)
        # print(fingers)
        totalFingers = fingers.count(1)

        h, w, c = overlay[totalFingers-1].shape
        img[0:h, 0:w] = overlay[totalFingers-1]

    ctime = time.time()
    fps = 1 / (ctime - ptime)
    ptime = ctime

    cv2.putText(img, f'Fps: {int(fps)}', (500, 30), cv2.FONT_HERSHEY_SIMPLEX,
                1, (255, 255, 255), 3)

    cv2.imshow("IMage", img)
    cv2.waitKey(1)
