# n=int(input())
# m=int(input())
# import math
# print(int(math.log(n)/math.log(m)));
# n=int(input())
# m=int(input())
#
# t=2
# j=0
#
# while t<n-1:
#     print('t',t)
#     j+=1
#     print('j',j)
#
#     t=pow(m,j)
# print(j)
import sys
import math

# s = input()
# t=''
# for i in s:
#     print(i)
#     if i.isnumeric():
#         print('true')
#     else:
#         t+=i
# print(t)
n=int(input())
t=0
while n!=1:
    if n%2==0:
        n=n/2
    else:
        n=n*3+1

    t += 1
r=t-1
while t!=1:
    if t%2==0:
        t=t/2
    else:
        t=t*3+1

    t += 1
    print(t,r)
print(r)