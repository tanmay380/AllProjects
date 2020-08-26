# class Student:
#     no_of_leavs=8
#     def __init__(self, name,class1, section):
#         self.name=name
#         self.Class=class1
#         self.section=section
#     @classmethod
#     def change_leavs(cls,leaves):
#         cls.no_of_leavs=leaves
#
#
# tanmay=Student('tnamay',12,'0b')
# # tanmay.change_leavs(12)
# tanmay.no_of_leavs=10
# print(tanmay.__dict__)
# print(Student.no_of_leavs)
# import sys
# import math
# duration = input().split(':')
# int(duration[0])
# print(type(int(duration[0])))
# # print(duration[0]*60+(duration[1]))
# import sys
# import math
# g=input()
# w=int(input())
# if g=='F' or g=='M':
#     if g=='F':
#         print(w-20%w)
#     elif g=='M':
#         print(w+20%w)
# else:
#     print('UNKNOWN')
import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.
import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = int(input())
m = int(input())

for num in range(n, m + 1):
    # all prime numbers are greater than 1
    if num > 1:
        for i in range(2, num):
            if (num % i) == 0:
                break
            else:
                print(num, end=' ')
    else:
        print('-1')
