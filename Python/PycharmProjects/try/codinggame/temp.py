import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

# n = int(input())  # the number of temperatures to analyse
# temps = input()  # the n temperatures expressed as integers ranging from -273 to 5526
# result = ''
#
# if len(temps) == 0:
#     print("0")
# else:
#     temps_split = temps.split()
#     result = temps_split[0]
#     print(result)
#     for temp in temps_split:
#         print(f'the result is {result}, the temp is {temp}')
#         if abs(int(temp)) < abs(int(result)):
#             result = temp
#         elif abs(int(temp)) == abs(int(result)):
#             # print(f'the result is {result}, the temp is {temp}')
#             result = max(int(temp), int(result))
#
# print(result)

import sys
import math

n = int(input("No . temp .to compare"))
t = []
temp = input()
for i in temp:
    t.append(i)
t.sort()
if t == 1:
    print('0')

else:
    print("Go")


