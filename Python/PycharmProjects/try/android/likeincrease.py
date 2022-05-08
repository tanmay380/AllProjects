from ppadb.client import Client as AdbClient
import time

client = AdbClient(host="127.0.0.1", port=5037)
devices = client.devices()

if len(devices) == 0:
    print("Connect a device")
    quit()
device = devices[0]


#2326,599
while True:
     print(f'{time.time()}: touching @ ')
     try:
        device.shell('input tap 2326 599')
        print('touching')
        time.sleep(0.1)
     except Exception as e:
         print(e)

