import time
from com.dtmilano.android.viewclient import ViewClient

device, serialno = ViewClient.connectToDeviceOrExit()

kwargs2 = {'useuiautomatorhelper': True}
vc = ViewClient(device, serialno, **kwargs2)

# taps = [(2326,599), (350, 800), (100, 100), (300, 300), (150, 150), (100, 200)]
# for tap in taps:
while True:
    print(f'{time.time()}: touching @')
    vc.touch(2326, 599)