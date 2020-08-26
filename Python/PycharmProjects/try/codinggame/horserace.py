import sys
import math

n=int(input("NO. OF horse:-> "))
horses=[]
for i in range(n):
    horses.append(int(input()))

horses.sort()

min=10000000
for i in range(n-1):

    if min>abs((horses[i]-horses[i+1])):
        min=abs(horses[i]-horses[i+1])
        if min==0:
            break

print(min)

