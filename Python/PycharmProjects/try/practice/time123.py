import time

initial = time.time()
h = 0
while h <= 1000:
    print(h)
    h += 1
print("WHIle loop took-> ", time.time() - initial, "secinds")

initial2 = time.time()
# print("initial 2 tool:-> ", initial2)
for i in range(1001):
    print(i)

print("for loop took-> ", time.time() - initial2, "seconds")
