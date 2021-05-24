import cv2
import mediapipe as mp
import time

cap = cv2.VideoCapture(0)

mpHands = mp.solutions.hands
hands = mpHands.Hands()
mpDraw = mp.solutions.drawing_utils

pTime = 0
ctime = 0

while True:
    success, img = cap.read()
    imgRGB = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
    results = hands.process(imgRGB)

    # print(results.multi_hand_landmarks)

    if results.multi_hand_landmarks:
        for handLms in results.multi_hand_landmarks:
            for id, lm in enumerate(handLms.landmark):
               # print(id, lm)
                h, w, c= img.shape
                cx,cy = int(lm.x*w), int(lm.y*h)
                print(id,  cx, cy)


            mpDraw.draw_landmarks(img, handLms, mpHands.HAND_CONNECTIONS)

    ctime = time.time()
    fps = 1 / (ctime - pTime)
    pTime = ctime

    cv2.putText(img, str(int(fps)), (10, 70), cv2.FONT_HERSHEY_SIMPLEX, 2, (255, 0, 255), 3)

    cv2.imshow("Image", img)
    cv2.waitKey(1)
