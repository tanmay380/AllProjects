'''
let folds = 0;
let height = T;
while(height < H) {
    height = height*2;
    folds++;
}s);
console.log(W*2**folds);'''
import fractions
# t,h,w=input().split()
# t=float(t)
# h=int(h)
# w=int(w)
#
# folds=0
# height=t
# while t<h:
#     print(t)
#     t*=2
#     folds+=1
# print(folds)
# print(w*2**folds)
import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

# FE82:1234:0:1235:1416:1A12:1B12:1C1F
import re

# d='FE82:1234:0:1235:1416:1A12:1B12:1C1F'
# FE82::1A12:1234:1A12
# d = 'FE82:0:0:0:0:1A12:1234:1A12'
d = input()
get = re.compile(r':0:')
pat = get.findall(d)
get1 = re.compile(r':0:0:0:0:')
pat1 = get1.findall(d)
get2 = re.compile(r'0000:0000')
pat2 = get2.findall(d)
get3 = re.compile(r':0000:')
pat3 = get3.findall(d)
# print(len(pat))
# print(len(pat1))
# print(len(pat2))
# print(len(pat3))
if len(pat) == 1:
    str1 = ''.join(pat)
    d = d.replace(str1, '::')
    # print(pat)
    # print(d)
elif len(pat1) == 1:
    str1 = ''.join(pat1)
    d = d.replace(str1, '::')
    # print(pat1)
    print(d)
elif len(pat2) >= 1:
    str1 = ' '.join(pat2)
    d = d.replace(str1, '')
    print(d)
elif len(pat3) >= 1:  # '2001:1234:0000:1234:1234:0000:1234:1A13'
    for i in range(len(pat3)):
        # print(pat3)
        st1 = pat3[i]
        d = d.replace(st1, '::')
    print(d)
