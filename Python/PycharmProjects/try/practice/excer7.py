#healthy rogrra
from pygame import mixer
from time import time
from datetime import datetime
def musicplay(file,ending):
    mixer.init()
    mixer.music.load(file)
    mixer.music.play()
    while True:
        done=input()
        if done==ending:
            mixer.music.stop()
            break

def log_now(msg):
    with open('logger.txt', 'a') as f:
        f.write(f'{msg}:-> {datetime.now()} \n')

init_water=time()
init_eyes=time()
init_excercise=time()

water=5
eyes=10
exercise=15

while True:
    if time()-init_water>water:
        print("Water drink")
        musicplay('water.mp3', 'drank')
        init_water=time()
        log_now('Drank water at')
    if time()-init_eyes>eyes:
        print("Eyes excersice")
        musicplay('eyes.mp3', 'edone')
        init_eyes = time()
        log_now('eys excersice done at')
